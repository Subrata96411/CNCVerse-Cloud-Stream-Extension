package com.cncverse

import android.annotation.SuppressLint
import android.net.Uri
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lagradost.cloudstream3.Actor
import com.lagradost.cloudstream3.ActorData
import com.lagradost.cloudstream3.Episode
import com.lagradost.cloudstream3.ErrorLoadingException
import com.lagradost.cloudstream3.HomePageList
import com.lagradost.cloudstream3.HomePageResponse
import com.lagradost.cloudstream3.LoadResponse
import com.lagradost.cloudstream3.LoadResponse.Companion.addImdbId
import com.lagradost.cloudstream3.LoadResponse.Companion.addTMDbId
import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.MainPageRequest
import com.lagradost.cloudstream3.Score
import com.lagradost.cloudstream3.SearchResponse
import com.lagradost.cloudstream3.SearchResponseList
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.addDate
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.base64Encode
import com.lagradost.cloudstream3.mainPageOf
import com.lagradost.cloudstream3.mapper
import com.lagradost.cloudstream3.newEpisode
import com.lagradost.cloudstream3.newHomePageResponse
import com.lagradost.cloudstream3.newMovieLoadResponse
import com.lagradost.cloudstream3.newMovieSearchResponse
import com.lagradost.cloudstream3.newSearchResponseList
import com.lagradost.cloudstream3.newSubtitleFile
import com.lagradost.cloudstream3.newTvSeriesLoadResponse
import com.lagradost.cloudstream3.toNewSearchResponseList
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.INFER_TYPE
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.max
import kotlin.random.Random

class MovieBoxNewProvider : MainAPI() {
    companion object {
        var context: android.content.Context? = null

        // Raw secret keys as plain strings (NOT base64-encoded, matching Python reference exactly)
        private const val SECRET_KEY_DEFAULT = "76iRl07s0xSN9jqmEWAt79EBJZulIQIsV64FZr2O"
        private const val SECRET_KEY_ALT = "Xqn2nnO41/L92o1iuXhSLHTbXvY4Z5ZZ62m8mSLA"
    }

    override var mainUrl = "https://api3.aoneroom.com"
    override var name = "MovieBoxNew"
    override val hasMainPage = true
    override var lang = "hi"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    private val random = SecureRandom()
    val deviceId = generateDeviceId()

    fun generateDeviceId(): String {
        val bytes = ByteArray(16)
        random.nextBytes(bytes)
        return bytes.joinToString("") { "%02x".format(it) }
    }

    // ─── Crypto helpers ─────────────────────────────────────────────────────────

    private fun md5(input: ByteArray): String {
        return MessageDigest.getInstance("MD5").digest(input)
            .joinToString("") { "%02x".format(it) }
    }

    /**
     * Generates x-client-token: "<timestamp_ms>,<md5(reverse(timestamp_ms_string))>"
     * Matches Python: generate_x_client_token()
     */
    private fun generateXClientToken(hardcodedTimestamp: Long? = null): String {
        val timestamp = (hardcodedTimestamp ?: System.currentTimeMillis()).toString()
        val reversed = timestamp.reversed()
        val hash = md5(reversed.toByteArray(Charsets.UTF_8))
        return "$timestamp,$hash"
    }

    /**
     * Builds the canonical string for HMAC signing.
     * Matches Python: build_canonical_string()
     */
    @SuppressLint("UseKtx")
    private fun buildCanonicalString(
        method: String,
        accept: String?,
        contentType: String?,
        url: String,
        body: String?,
        timestamp: Long
    ): String {
        val parsed = Uri.parse(url)
        val path = parsed.path ?: ""

        // Build query string with sorted parameters
        val query = if (!parsed.query.isNullOrEmpty() && parsed.queryParameterNames.isNotEmpty()) {
            parsed.queryParameterNames.sorted().joinToString("&") { key ->
                parsed.getQueryParameters(key).joinToString("&") { value ->
                    "$key=$value"
                }
            }
        } else ""

        val canonicalUrl = if (query.isNotEmpty()) "$path?$query" else path

        val bodyBytes = body?.toByteArray(Charsets.UTF_8)
        val bodyHash = if (bodyBytes != null) {
            val trimmed = if (bodyBytes.size > 102400) bodyBytes.copyOfRange(0, 102400) else bodyBytes
            md5(trimmed)
        } else ""

        val bodyLength = bodyBytes?.size?.toString() ?: ""
        return "${method.uppercase()}\n" +
                "${accept ?: ""}\n" +
                "${contentType ?: ""}\n" +
                "$bodyLength\n" +
                "$timestamp\n" +
                "$bodyHash\n" +
                canonicalUrl
    }

    /**
     * Generates x-tr-signature using HMAC-MD5.
     * KEY FIX: Uses the raw secret key bytes (not double-base64-decoded).
     * Matches Python: build_signed_headers() -> crypto.sign()
     */
    private fun generateXTrSignature(
        method: String,
        accept: String?,
        contentType: String?,
        url: String,
        body: String? = null,
        useAltKey: Boolean = false,
        hardcodedTimestamp: Long? = null
    ): String {
        val timestamp = hardcodedTimestamp ?: System.currentTimeMillis()
        val canonical = buildCanonicalString(method, accept, contentType, url, body, timestamp)

        // Use raw bytes of the secret key string (Python uses str.encode() directly)
        val secretBytes = if (useAltKey) SECRET_KEY_ALT.toByteArray(Charsets.UTF_8)
                          else SECRET_KEY_DEFAULT.toByteArray(Charsets.UTF_8)

        val mac = Mac.getInstance("HmacMD5")
        mac.init(SecretKeySpec(secretBytes, "HmacMD5"))
        val signature = mac.doFinal(canonical.toByteArray(Charsets.UTF_8))
        val signatureB64 = base64Encode(signature)

        return "$timestamp|2|$signatureB64"
    }

    // ─── Device/Client Info ──────────────────────────────────────────────────────

    data class BrandModel(val brand: String, val model: String)

    private val androidVersions = listOf(
        mapOf("version" to "9",  "build" to "PQ3A.190605.030"),
        mapOf("version" to "10", "build" to "QP1A.191005.007"),
        mapOf("version" to "11", "build" to "RP1A.200720.011"),
        mapOf("version" to "12", "build" to "S1B.220414.015"),
        mapOf("version" to "13", "build" to "TQ2A.230405.003"),
    )

    private val redmiDevices = listOf(
        BrandModel("Redmi", "23078RKD5C"),
        BrandModel("Redmi", "2201117TY"),
        BrandModel("Redmi", "2201117TG"),
        BrandModel("Redmi", "22101316G"),
        BrandModel("Redmi", "21121210G"),
        BrandModel("Samsung", "SM-S918B"),
        BrandModel("Xiaomi", "2201117TI"),
        BrandModel("OnePlus", "LE2111"),
    )

    private val versionCodes = listOf(50020042, 50020043, 50020044, 50020045, 50020046)

    fun randomBrandModel(): BrandModel = redmiDevices.random()

    private fun buildClientInfo(brand: String, model: String, versionCode: Int = 50020042): String {
        val gaid = java.util.UUID.randomUUID().toString()
        return """{"package_name":"com.community.oneroom","version_name":"3.0.13.0325.03","version_code":$versionCode,"os":"android","os_version":"13","install_ch":"ps","device_id":"$deviceId","install_store":"ps","gaid":"$gaid","brand":"$brand","model":"$model","system_language":"en","net":"NETWORK_WIFI","region":"US","timezone":"Asia/Calcutta","sp_code":"","X-Play-Mode":"1","X-Idle-Data":"1","X-Family-Mode":"0","X-Content-Mode":"0"}"""
    }

    private fun buildUserAgent(versionCode: Int, androidVersion: String, brand: String): String {
        return "com.community.oneroom/$versionCode (Linux; U; Android $androidVersion; en_US; $brand; Build/TQ3A.230901.001; Cronet/145.0.7582.0)"
    }

    // ─── Main Page ───────────────────────────────────────────────────────────────

    override val mainPage = mainPageOf(
        "4516404531735022304" to "Trending",
        "5692654647815587592" to "Trending in Cinema",
        "414907768299210008"  to "Bollywood",
        "3859721901924910512" to "South Indian",
        "8019599703232971616" to "Hollywood",
        "4741626294545400336" to "Top Series This Week",
        "8434602210994128512" to "Anime",
        "1255898847918934600" to "Reality TV",
        "4903182713986896328" to "Indian Drama",
        "7878715743607948784" to "Korean Drama",
        "8788126208987989488" to "Chinese Drama",
        "3910636007619709856" to "Western TV",
        "5177200225164885656" to "Turkish Drama",
        "1|1" to "Movies",
        "1|2" to "Series",
        "1|1006" to "Anime",
        "1|1;country=India" to "Indian (Movies)",
        "1|2;country=India" to "Indian (Series)",
        "1|1;classify=Hindi dub;country=United States" to "USA (Movies)",
        "1|2;classify=Hindi dub;country=United States" to "USA (Series)",
        "1|1;country=Japan" to "Japan (Movies)",
        "1|2;country=Japan" to "Japan (Series)",
        "1|1;country=China" to "China (Movies)",
        "1|2;country=China" to "China (Series)",
        "1|1;country=Korea" to "South Korean (Movies)",
        "1|2;country=Korea" to "South Korean (Series)",
        "1|1;classify=Hindi dub;genre=Action" to "Action (Movies)",
        "1|1;classify=Hindi dub;genre=Crime" to "Crime (Movies)",
        "1|1;classify=Hindi dub;genre=Comedy" to "Comedy (Movies)",
        "1|1;classify=Hindi dub;genre=Romance" to "Romance (Movies)",
        "1|2;classify=Hindi dub;genre=Crime" to "Crime (Series)",
        "1|2;classify=Hindi dub;genre=Comedy" to "Comedy (Series)",
        "1|2;classify=Hindi dub;genre=Romance" to "Romance (Series)",
    )

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val perPage = 15
        val url = if (request.data.contains("|")) "$mainUrl/wefeed-mobile-bff/subject-api/list"
                  else "$mainUrl/wefeed-mobile-bff/tab/ranking-list?tabId=0&categoryType=${request.data}&page=$page&perPage=$perPage"

        val data1 = request.data
        val mainParts = data1.substringBefore(";").split("|")
        val pg = mainParts.getOrNull(0)?.toIntOrNull() ?: 1
        val channelId = mainParts.getOrNull(1)

        val options = mutableMapOf<String, String>()
        data1.substringAfter(";", "")
            .split(";")
            .forEach {
                val parts = it.split("=")
                val k = parts.getOrNull(0)
                val v = parts.getOrNull(1)
                if (!k.isNullOrBlank() && !v.isNullOrBlank()) options[k] = v
            }

        val classify = options["classify"] ?: "All"
        val country  = options["country"] ?: "All"
        val year     = options["year"] ?: "All"
        val genre    = options["genre"] ?: "All"
        val sort     = options["sort"] ?: "ForYou"

        val jsonBody = """{"page":$pg,"perPage":$perPage,"channelId":"$channelId","classify":"$classify","country":"$country","year":"$year","genre":"$genre","sort":"$sort"}"""

        val (brand, model) = randomBrandModel()
        val versionCode = versionCodes.random()
        val androidVer = androidVersions.random()["version"] ?: "13"
        val ua = buildUserAgent(versionCode, androidVer, "$brand $model")
        val clientInfo = buildClientInfo(brand, model, versionCode)

        val xClientToken = generateXClientToken()
        val xTrSignaturePost = generateXTrSignature("POST", "application/json", "application/json; charset=utf-8", url, jsonBody)
        val xTrSignatureGet  = generateXTrSignature("GET", "application/json", "application/json", url)

        val postHeaders = mapOf(
            "user-agent"      to ua,
            "accept"          to "application/json",
            "content-type"    to "application/json; charset=utf-8",
            "connection"      to "keep-alive",
            "x-client-token"  to xClientToken,
            "x-tr-signature"  to xTrSignaturePost,
            "x-client-info"   to clientInfo,
            "x-client-status" to "0",
        )
        val getHeaders = mapOf(
            "user-agent"      to ua,
            "accept"          to "application/json",
            "content-type"    to "application/json",
            "connection"      to "keep-alive",
            "x-client-token"  to xClientToken,
            "x-tr-signature"  to xTrSignatureGet,
            "x-client-info"   to clientInfo,
            "x-client-status" to "0",
        )

        val response = if (request.data.contains("|")) {
            val requestBody = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaType())
            app.post(url, headers = postHeaders, requestBody = requestBody)
        } else {
            app.get(url, headers = getHeaders)
        }

        val responseBody = response.body.string()
        val data = try {
            val mapper = jacksonObjectMapper()
            val root = mapper.readTree(responseBody)
            val items = root["data"]?.get("items") ?: root["data"]?.get("subjects") ?: return newHomePageResponse(emptyList())
            items.mapNotNull { item ->
                val title = item["title"]?.asText()?.substringBefore("[") ?: return@mapNotNull null
                val id    = item["subjectId"]?.asText() ?: return@mapNotNull null
                val coverImg = item["cover"]?.get("url")?.asText()
                val type = when (item["subjectType"]?.asInt() ?: 1) {
                    2    -> TvType.TvSeries
                    else -> TvType.Movie
                }
                newMovieSearchResponse(name = title, url = id, type = type) {
                    this.posterUrl = coverImg
                    this.score = Score.from10(item["imdbRatingValue"]?.asText())
                }
            }
        } catch (_: Exception) { null } ?: emptyList()

        return newHomePageResponse(listOf(HomePageList(request.name, data)))
    }

    // ─── Search ──────────────────────────────────────────────────────────────────

    override suspend fun search(query: String, page: Int): SearchResponseList {
        val url = "$mainUrl/wefeed-mobile-bff/subject-api/search/v2"
        val jsonBody = """{"page": $page, "perPage": 20, "keyword": "$query"}"""

        val (brand, model) = randomBrandModel()
        val versionCode = versionCodes.random()
        val androidVer = androidVersions.random()["version"] ?: "13"
        val ua = buildUserAgent(versionCode, androidVer, "$brand $model")
        val clientInfo = buildClientInfo(brand, model, versionCode)

        val xClientToken = generateXClientToken()
        val xTrSignature = generateXTrSignature("POST", "application/json", "application/json; charset=utf-8", url, jsonBody)

        val headers = mapOf(
            "user-agent"      to ua,
            "accept"          to "application/json",
            "content-type"    to "application/json; charset=utf-8",
            "connection"      to "keep-alive",
            "x-client-token"  to xClientToken,
            "x-tr-signature"  to xTrSignature,
            "x-client-info"   to clientInfo,
            "x-client-status" to "0",
        )

        val requestBody = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaType())
        val response = app.post(url, headers = headers, requestBody = requestBody)

        val responseBody = response.body.string()
        val mapper = jacksonObjectMapper()
        val root = mapper.readTree(responseBody)
        val results = root.get("data")?.get("results") ?: return newSearchResponseList(emptyList())
        val searchList = mutableListOf<SearchResponse>()

        for (result in results) {
            val subjects = result["subjects"] ?: continue
            for (subject in subjects) {
                val title = subject["title"]?.asText() ?: continue
                val id    = subject["subjectId"]?.asText() ?: continue
                val coverImg = subject["cover"]?.get("url")?.asText()
                val type = when (subject["subjectType"]?.asInt() ?: 1) {
                    2    -> TvType.TvSeries
                    else -> TvType.Movie
                }
                searchList.add(
                    newMovieSearchResponse(name = title, url = id, type = type) {
                        this.posterUrl = coverImg
                        this.score = Score.from10(subject["imdbRatingValue"]?.asText())
                    }
                )
            }
        }
        return searchList.toNewSearchResponseList()
    }

    // ─── Load ────────────────────────────────────────────────────────────────────

    override suspend fun load(url: String): LoadResponse {
        val id = Regex("""subjectId=([^&]+)""")
            .find(url)?.groupValues?.get(1)
            ?: url.substringAfterLast('/')

        val finalUrl = "$mainUrl/wefeed-mobile-bff/subject-api/get?subjectId=$id"

        val (brand, model) = randomBrandModel()
        val versionCode = versionCodes.random()
        val androidVer = androidVersions.random()["version"] ?: "13"
        val ua = buildUserAgent(versionCode, androidVer, "$brand $model")
        val clientInfo = buildClientInfo(brand, model, versionCode)

        val xClientToken = generateXClientToken()
        val xTrSignature = generateXTrSignature("GET", "application/json", "application/json", finalUrl)

        val headers = mapOf(
            "user-agent"      to ua,
            "accept"          to "application/json",
            "content-type"    to "application/json",
            "connection"      to "keep-alive",
            "x-client-token"  to xClientToken,
            "x-tr-signature"  to xTrSignature,
            "x-client-info"   to clientInfo,
            "x-client-status" to "0",
        )

        val response = app.get(finalUrl, headers = headers)
        if (response.code != 200) {
            throw ErrorLoadingException("Failed to load: HTTP ${response.code}")
        }

        val body = response.body.string()
        val mapper = jacksonObjectMapper()
        val root = mapper.readTree(body)
        val data = root["data"] ?: throw ErrorLoadingException("No data in response")

        val title       = data["title"]?.asText()?.substringBefore("[") ?: throw ErrorLoadingException("No title")
        val description = data["description"]?.asText()
        val releaseDate = data["releaseDate"]?.asText()
        val duration    = data["duration"]?.asText()
        val genre       = data["genre"]?.asText()
        val imdbRating  = data["imdbRatingValue"]?.asText()?.toDoubleOrNull()?.times(10)?.toInt()
        val year        = releaseDate?.take(4)?.toIntOrNull()
        val coverUrl    = data["cover"]?.get("url")?.asText()
        val subjectType = data["subjectType"]?.asInt() ?: 1

        val actors = data["staffList"]
            ?.mapNotNull { staff ->
                if (staff["staffType"]?.asInt() == 1) {
                    val actorName = staff["name"]?.asText() ?: return@mapNotNull null
                    ActorData(Actor(actorName, staff["avatarUrl"]?.asText()), roleString = staff["character"]?.asText())
                } else null
            }
            ?.distinctBy { it.actor.name } ?: emptyList()

        val tags = genre?.split(",")?.map { it.trim() } ?: emptyList()

        val durationMinutes = duration?.let { dur ->
            val m = """(\d+)h\s*(\d+)m""".toRegex().find(dur)
            if (m != null) {
                val h = m.groupValues[1].toIntOrNull() ?: 0
                val min = m.groupValues[2].toIntOrNull() ?: 0
                h * 60 + min
            } else dur.replace("m", "").toIntOrNull()
        }

        val type = when (subjectType) {
            2, 7 -> TvType.TvSeries
            else -> TvType.Movie
        }

        val (tmdbId, imdbId) = mbIdentifyID(
            title = title.substringBefore("(").substringBefore("["),
            year = releaseDate?.take(4)?.toIntOrNull(),
            imdbRatingValue = imdbRating?.toDouble(),
        )

        val logoUrl = mbFetchTmdbLogoUrl(
            tmdbAPI = "https://api.themoviedb.org/3",
            apiKey = "98ae14df2b8d8f8f8136499daf79f0e0",
            type = type,
            tmdbId = tmdbId,
            appLangCode = "en"
        )

        val meta       = if (!imdbId.isNullOrBlank()) mbFetchMetaData(imdbId, type) else null
        val metaVideos = meta?.get("videos")?.toList() ?: emptyList()
        val poster     = meta?.get("poster")?.asText() ?: coverUrl
        val background = meta?.get("background")?.asText() ?: coverUrl
        val plot       = meta?.get("overview")?.asText() ?: description
        val imdbStr    = meta?.get("imdbRating")?.asText()

        if (type == TvType.TvSeries) {
            // Gather all subject IDs (including dubs for episode coverage)
            val allSubjectIds = mutableListOf(id)
            data["dubs"]?.forEach {
                val sid = it["subjectId"]?.asText()
                if (!sid.isNullOrBlank() && sid !in allSubjectIds) allSubjectIds.add(sid)
            }

            val episodeMap = mutableMapOf<Int, MutableSet<Int>>()

            for (subjectId in allSubjectIds) {
                val seasonUrl = "$mainUrl/wefeed-mobile-bff/subject-api/season-info?subjectId=$subjectId"
                val seasonSig = generateXTrSignature("GET", "application/json", "application/json", seasonUrl)
                val seasonHeaders = headers.toMutableMap().apply { put("x-tr-signature", seasonSig) }
                val seasonResponse = app.get(seasonUrl, headers = seasonHeaders)
                if (seasonResponse.code != 200) continue

                val seasonRoot = mapper.readTree(seasonResponse.body.string())
                val seasons = seasonRoot["data"]?.get("seasons") ?: continue
                if (!seasons.isArray || seasons.size() == 0) continue

                seasons.forEach { season ->
                    val seasonNumber = season["se"]?.asInt() ?: 1
                    val maxEp = season["maxEp"]?.asInt() ?: 1
                    val epSet = episodeMap.getOrPut(seasonNumber) { mutableSetOf() }
                    for (ep in 1..maxEp) epSet.add(ep)
                }
            }

            val episodes = mutableListOf<Episode>()
            episodeMap.forEach { (seasonNumber, epSet) ->
                epSet.sorted().forEach { episodeNumber ->
                    val epMeta = metaVideos.firstOrNull {
                        it["season"]?.asInt() == seasonNumber && it["episode"]?.asInt() == episodeNumber
                    }
                    val epName  = epMeta?.get("name")?.asText()?.takeIf { it.isNotBlank() } ?: "S${seasonNumber}E${episodeNumber}"
                    val epDesc  = epMeta?.get("overview")?.asText() ?: "Season $seasonNumber Episode $episodeNumber"
                    val epThumb = epMeta?.get("thumbnail")?.asText()?.takeIf { it.isNotBlank() } ?: coverUrl
                    val runtime = epMeta?.get("runtime")?.asText()?.filter { it.isDigit() }?.toIntOrNull()
                    val aired   = epMeta?.get("released")?.asText()?.takeIf { it.isNotBlank() } ?: ""

                    episodes.add(newEpisode("$id|$seasonNumber|$episodeNumber") {
                        this.name       = epName
                        this.season     = seasonNumber
                        this.episode    = episodeNumber
                        this.posterUrl  = epThumb
                        this.description = epDesc
                        this.runTime    = runtime
                        addDate(aired)
                    })
                }
            }

            if (episodes.isEmpty()) {
                episodes.add(newEpisode("$id|1|1") {
                    this.name = "Episode 1"; this.season = 1; this.episode = 1; this.posterUrl = coverUrl
                })
            }

            return newTvSeriesLoadResponse(title, finalUrl, type, episodes) {
                this.posterUrl           = poster ?: coverUrl
                this.backgroundPosterUrl = background ?: coverUrl
                try { this.logoUrl = logoUrl } catch (_: Throwable) {}
                this.plot     = plot ?: description
                this.year     = year
                this.tags     = tags
                this.actors   = actors
                this.score    = Score.from10(imdbStr) ?: imdbRating?.let { Score.from10(it) }
                this.duration = durationMinutes
                addImdbId(imdbId)
                addTMDbId(tmdbId.toString())
            }
        }

        return newMovieLoadResponse(title, finalUrl, type, id) {
            this.posterUrl           = poster ?: coverUrl
            this.backgroundPosterUrl = background ?: coverUrl
            try { this.logoUrl = logoUrl } catch (_: Throwable) {}
            this.plot     = plot ?: description
            this.year     = year
            this.tags     = tags
            this.actors   = actors
            this.score    = Score.from10(imdbStr) ?: imdbRating?.let { Score.from10(it) }
            this.duration = durationMinutes
            addImdbId(imdbId)
            addTMDbId(tmdbId.toString())
        }
    }

    // ─── Load Links ──────────────────────────────────────────────────────────────

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val (brand, model) = randomBrandModel()
        val versionCode = versionCodes.random()
        val androidVer = androidVersions.random()["version"] ?: "13"
        val ua = buildUserAgent(versionCode, androidVer, "$brand $model")
        val clientInfo = buildClientInfo(brand, model, versionCode)

        return try {
            val parts = data.split("|")
            val originalSubjectId = when {
                parts[0].contains("get?subjectId") ->
                    Regex("""subjectId=([^&]+)""").find(parts[0])?.groupValues?.get(1) ?: parts[0].substringAfterLast('/')
                parts[0].contains("/") -> parts[0].substringAfterLast('/')
                else -> parts[0]
            }

            val season  = if (parts.size > 1) parts[1].toIntOrNull() ?: 0 else 0
            val episode = if (parts.size > 2) parts[2].toIntOrNull() ?: 0 else 0

            // ── Step 1: get subject details & extract x-user bearer token ──────
            val subjectUrl = "$mainUrl/wefeed-mobile-bff/subject-api/get?subjectId=$originalSubjectId"
            val subjectToken = generateXClientToken()
            val subjectSig   = generateXTrSignature("GET", "application/json", "application/json", subjectUrl)
            val subjectHeaders = mapOf(
                "user-agent"      to ua,
                "accept"          to "application/json",
                "content-type"    to "application/json",
                "connection"      to "keep-alive",
                "x-client-token"  to subjectToken,
                "x-tr-signature"  to subjectSig,
                "x-client-info"   to clientInfo,
                "x-client-status" to "0",
            )

            val subjectResponse = app.get(subjectUrl, headers = subjectHeaders)
            val mapper = jacksonObjectMapper()

            // Collect dub IDs
            val subjectIds = mutableListOf<Pair<String, String>>()
            var originalLanguageName = "Original"
            var bearerToken: String? = null

            if (subjectResponse.code == 200) {
                val subjectRoot = mapper.readTree(subjectResponse.body.string())
                val subjectData = subjectRoot["data"]
                val dubs = subjectData?.get("dubs")
                if (dubs != null && dubs.isArray) {
                    for (dub in dubs) {
                        val dubId   = dub["subjectId"]?.asText()
                        val lanName = dub["lanName"]?.asText()
                        if (dubId != null && lanName != null) {
                            if (dubId == originalSubjectId) originalLanguageName = lanName
                            else subjectIds.add(Pair(dubId, lanName))
                        }
                    }
                }
            }

            // Extract bearer token from x-user header
            val xUserHeader = subjectResponse.headers["x-user"]
            if (!xUserHeader.isNullOrBlank()) {
                try {
                    val xUserJson = mapper.readTree(xUserHeader)
                    bearerToken = xUserJson["token"]?.asText()
                } catch (_: Exception) {}
            }

            // If we didn't get a token from the subject call, try a direct login call
            // (some API versions return 441 when no bearer token is provided)
            if (bearerToken.isNullOrBlank()) {
                bearerToken = fetchAnonymousToken(ua, clientInfo)
            }

            subjectIds.add(0, Pair(originalSubjectId, originalLanguageName))

            // ── Step 2: fetch play-info for each subject (original + dubs) ──────
            for ((subjectId, language) in subjectIds) {
                try {
                    val playUrl = "$mainUrl/wefeed-mobile-bff/subject-api/play-info?subjectId=$subjectId&se=$season&ep=$episode"
                    val playToken = generateXClientToken()
                    val playSig   = generateXTrSignature("GET", "application/json", "application/json", playUrl)

                    val playHeaders = buildMap<String, String> {
                        put("user-agent",      ua)
                        put("accept",          "application/json")
                        put("content-type",    "application/json")
                        put("connection",      "keep-alive")
                        put("x-client-token",  playToken)
                        put("x-tr-signature",  playSig)
                        put("x-client-info",   clientInfo)
                        put("x-client-status", "0")
                        if (!bearerToken.isNullOrBlank()) {
                            put("Authorization", "Bearer $bearerToken")
                        }
                    }

                    val playResponse = app.get(playUrl, headers = playHeaders)

                    // If we got a fresh token in the response, use it for subsequent calls
                    val freshXUser = playResponse.headers["x-user"]
                    if (!freshXUser.isNullOrBlank()) {
                        try {
                            val freshJson = mapper.readTree(freshXUser)
                            val freshToken = freshJson["token"]?.asText()
                            if (!freshToken.isNullOrBlank()) bearerToken = freshToken
                        } catch (_: Exception) {}
                    }

                    if (playResponse.code == 200) {
                        val playRoot = mapper.readTree(playResponse.body.string())
                        val playData = playRoot["data"]
                        val streams  = playData?.get("streams")

                        if (streams != null && streams.isArray && streams.size() > 0) {
                            processStreams(streams, subjectId, season, episode, language, bearerToken, ua, clientInfo, mapper, callback, subtitleCallback)
                        } else {
                            // Fallback: try resourceDetectors from subject-api/get
                            fetchFallbackLinks(subjectId, language, season, episode, ua, clientInfo, bearerToken, playHeaders, callback)
                        }
                    }
                } catch (_: Exception) { continue }
            }
            true
        } catch (_: Exception) { false }
    }

    /**
     * Tries to fetch an anonymous bearer token from the API's anonymous login endpoint.
     * The Python library picks up a token from x-user in any successful API response;
     * here we do a cheap GET to the main page endpoint to harvest one.
     */
    private suspend fun fetchAnonymousToken(ua: String, clientInfo: String): String? {
        return try {
            val pingUrl = "$mainUrl/wefeed-mobile-bff/tab/ranking-list?tabId=0&categoryType=4516404531735022304&page=1&perPage=1"
            val xct = generateXClientToken()
            val sig = generateXTrSignature("GET", "application/json", "application/json", pingUrl)
            val headers = mapOf(
                "user-agent"      to ua,
                "accept"          to "application/json",
                "content-type"    to "application/json",
                "x-client-token"  to xct,
                "x-tr-signature"  to sig,
                "x-client-info"   to clientInfo,
                "x-client-status" to "0",
            )
            val resp = app.get(pingUrl, headers = headers)
            val xUser = resp.headers["x-user"]
            if (!xUser.isNullOrBlank()) {
                val json = jacksonObjectMapper().readTree(xUser)
                json["token"]?.asText()
            } else null
        } catch (_: Exception) { null }
    }

    private suspend fun processStreams(
        streams: JsonNode,
        subjectId: String,
        season: Int,
        episode: Int,
        language: String,
        bearerToken: String?,
        ua: String,
        clientInfo: String,
        mapper: com.fasterxml.jackson.databind.ObjectMapper,
        callback: (ExtractorLink) -> Unit,
        subtitleCallback: (SubtitleFile) -> Unit
    ) {
        for (stream in streams) {
            val streamUrl     = stream["url"]?.asText() ?: continue
            val format        = stream["format"]?.asText() ?: ""
            val resolutions   = stream["resolutions"]?.asText() ?: ""
            val signCookieRaw = stream["signCookie"]?.asText()
            val signCookie    = if (signCookieRaw.isNullOrEmpty()) null else signCookieRaw
            val streamId      = stream["id"]?.asText() ?: "$subjectId|$season|$episode"
            val quality       = mbGetHighestQuality(resolutions)
            val langLabel     = language.replace("dub", "Audio")

            val linkType = when {
                streamUrl.startsWith("magnet:", ignoreCase = true)                                             -> ExtractorLinkType.MAGNET
                streamUrl.contains(".mpd", ignoreCase = true)                                                  -> ExtractorLinkType.DASH
                format.equals("HLS", ignoreCase = true) || streamUrl.contains(".m3u8", ignoreCase = true)     -> ExtractorLinkType.M3U8
                streamUrl.contains(".mp4", ignoreCase = true) || streamUrl.contains(".mkv", ignoreCase = true) -> ExtractorLinkType.VIDEO
                else                                                                                           -> INFER_TYPE
            }

            val linkHeaders = buildMap<String, String> {
                put("Referer", mainUrl)
                if (!bearerToken.isNullOrBlank()) put("Authorization", "Bearer $bearerToken")
                if (signCookie != null) put("Cookie", signCookie)
            }

            val extractorLink = newExtractorLink(
                source = "MovieBoxNew $langLabel",
                name   = "MovieBoxNew ($langLabel)",
                url    = streamUrl,
                type   = linkType
            ) {
                this.headers = linkHeaders
                if (quality != null) this.quality = quality
            }
            callback.invoke(extractorLink)

            // Fetch subtitles for this stream
            fetchSubtitles(subjectId, streamId, episode, langLabel, bearerToken, ua, clientInfo, mapper, subtitleCallback)
        }
    }

    private suspend fun fetchSubtitles(
        subjectId: String,
        streamId: String,
        episode: Int,
        langLabel: String,
        bearerToken: String?,
        ua: String,
        clientInfo: String,
        mapper: com.fasterxml.jackson.databind.ObjectMapper,
        subtitleCallback: (SubtitleFile) -> Unit
    ) {
        // Endpoint 1: get-stream-captions
        try {
            val subLink = "$mainUrl/wefeed-mobile-bff/subject-api/get-stream-captions?subjectId=$subjectId&streamId=$streamId"
            val xct = generateXClientToken()
            val sig = generateXTrSignature("GET", "", "", subLink)
            val headers = buildMap<String, String> {
                put("user-agent",      ua)
                put("Accept",         "")
                put("Content-Type",   "")
                put("x-client-token", xct)
                put("x-tr-signature", sig)
                put("x-client-info",  clientInfo)
                put("X-Client-Status","0")
                if (!bearerToken.isNullOrBlank()) put("Authorization", "Bearer $bearerToken")
            }
            val resp = app.get(subLink, headers = headers)
            val root = mapper.readTree(resp.body.string())
            root["data"]?.get("extCaptions")?.forEach { caption ->
                val capUrl = caption["url"]?.asText() ?: return@forEach
                val lang   = caption["language"]?.asText() ?: caption["lanName"]?.asText() ?: caption["lan"]?.asText() ?: "Unknown"
                subtitleCallback.invoke(newSubtitleFile(url = capUrl, lang = "$lang ($langLabel)"))
            }
        } catch (_: Exception) {}

        // Endpoint 2: get-ext-captions
        try {
            val subLink2 = "$mainUrl/wefeed-mobile-bff/subject-api/get-ext-captions?subjectId=$subjectId&resourceId=$streamId&episode=$episode"
            val xct = generateXClientToken()
            val sig = generateXTrSignature("GET", "", "", subLink2)
            val headers = buildMap<String, String> {
                put("user-agent",      ua)
                put("Accept",         "")
                put("Content-Type",   "")
                put("x-client-token", xct)
                put("x-tr-signature", sig)
                put("x-client-info",  clientInfo)
                put("X-Client-Status","0")
                if (!bearerToken.isNullOrBlank()) put("Authorization", "Bearer $bearerToken")
            }
            val resp = app.get(subLink2, headers = headers)
            val root = mapper.readTree(resp.body.string())
            root["data"]?.get("extCaptions")?.forEach { caption ->
                val capUrl = caption["url"]?.asText() ?: return@forEach
                val lang   = caption["lan"]?.asText() ?: caption["lanName"]?.asText() ?: caption["language"]?.asText() ?: "Unknown"
                subtitleCallback.invoke(newSubtitleFile(url = capUrl, lang = "$lang ($langLabel)"))
            }
        } catch (_: Exception) {}
    }

    private suspend fun fetchFallbackLinks(
        subjectId: String,
        language: String,
        season: Int,
        episode: Int,
        ua: String,
        clientInfo: String,
        bearerToken: String?,
        existingHeaders: Map<String, String>,
        callback: (ExtractorLink) -> Unit
    ) {
        try {
            val fallbackUrl = "$mainUrl/wefeed-mobile-bff/subject-api/get?subjectId=$subjectId"
            val fallbackHeaders = existingHeaders.toMutableMap().apply {
                put("x-tr-signature", generateXTrSignature("GET", "application/json", "application/json", fallbackUrl))
            }
            val fbResponse = app.get(fallbackUrl, headers = fallbackHeaders)
            if (fbResponse.code != 200) return

            val fbRoot = jacksonObjectMapper().readTree(fbResponse.body.string())
            val detectors = fbRoot["data"]?.get("resourceDetectors") ?: return
            val langLabel = language.replace("dub", "Audio")

            detectors.forEach { detector ->
                detector["resolutionList"]?.forEach { video ->
                    val link    = video["resourceLink"]?.asText() ?: return@forEach
                    val quality = video["resolution"]?.asInt() ?: 0
                    val se      = video["se"]?.asInt()
                    val ep      = video["ep"]?.asInt()
                    callback.invoke(
                        newExtractorLink(
                            source = "MovieBoxNew $langLabel",
                            name   = "MovieBoxNew S${se}E${ep} ${quality}p ($langLabel)",
                            url    = link,
                            type   = ExtractorLinkType.VIDEO
                        ) {
                            this.headers = mapOf("Referer" to mainUrl)
                            this.quality = quality
                        }
                    )
                }
            }
        } catch (_: Exception) {}
    }
}

// ─── Helper functions (top-level, package scope) ─────────────────────────────

fun mbGetHighestQuality(input: String): Int? {
    val qualities = listOf(
        "2160" to Qualities.P2160.value,
        "1440" to Qualities.P1440.value,
        "1080" to Qualities.P1080.value,
        "720"  to Qualities.P720.value,
        "480"  to Qualities.P480.value,
        "360"  to Qualities.P360.value,
        "240"  to Qualities.P240.value,
    )
    for ((label, mapped) in qualities) {
        if (input.contains(label, ignoreCase = true)) return mapped
    }
    return null
}

private fun mbCleanTitle(s: String): String {
    return s.lowercase()
        .replace("[^a-z0-9 ]".toRegex(), " ")
        .replace("\\s+".toRegex(), " ")
        .trim()
}

private fun mbNormalize(s: String): String {
    return s.replace("\\[.*?]".toRegex(), " ")
        .replace("\\(.*?\\)".toRegex(), " ")
        .replace("(?i)\\b(dub|dubbed|hd|4k|hindi|tamil|telugu|dual audio)\\b".toRegex(), " ")
        .trim()
        .lowercase()
        .replace(":", " ")
        .replace("\\p{Punct}".toRegex(), " ")
        .replace("\\s+".toRegex(), " ")
}

private fun mbTokenEquals(a: String, b: String): Boolean {
    val sa = a.split("\\s+".toRegex()).filter { it.isNotBlank() }.toSet()
    val sb = b.split("\\s+".toRegex()).filter { it.isNotBlank() }.toSet()
    if (sa.isEmpty() || sb.isEmpty()) return false
    val inter = sa.intersect(sb).size
    return inter >= max(1, minOf(sa.size, sb.size) * 3 / 4)
}

private suspend fun mbIdentifyID(
    title: String,
    year: Int?,
    imdbRatingValue: Double?
): Pair<Int?, String?> {
    val normTitle = mbNormalize(title)
    return mbSearchAndPick(normTitle, year, imdbRatingValue)
}

private suspend fun mbSearchAndPick(
    normTitle: String,
    year: Int?,
    imdbRatingValue: Double?
): Pair<Int?, String?> {
    suspend fun doSearch(endpoint: String, extraParams: String = ""): org.json.JSONArray? {
        val url = buildString {
            append("https://api.themoviedb.org/3/").append(endpoint)
            append("?api_key=").append("1865f43a0549ca50d341dd9ab8b29f49")
            append(extraParams)
            append("&include_adult=false&page=1")
            append("&random=").append(Random.nextInt())
        }
        return try {
            val text = app.get(url).text
            JSONObject(text).optJSONArray("results")
        } catch (_: Exception) { null }
    }

    val queries = listOf(
        "multi" to doSearch("search/multi", "&query=${URLEncoder.encode(normTitle, "UTF-8")}" + (if (year != null) "&year=$year" else "")),
        "tv"    to doSearch("search/tv", "&query=${URLEncoder.encode(normTitle, "UTF-8")}" + (if (year != null) "&first_air_date_year=$year" else "")),
        "movie" to doSearch("search/movie", "&query=${URLEncoder.encode(normTitle, "UTF-8")}" + (if (year != null) "&year=$year" else "")),
    )

    var bestId: Int? = null
    var bestScore = -1.0
    var bestIsTv = false

    for ((sourceType, results) in queries) {
        results ?: continue
        for (i in 0 until results.length()) {
            val o = results.getJSONObject(i)
            val mediaType = when (sourceType) {
                "multi" -> o.optString("media_type", "")
                "tv"    -> "tv"
                else    -> "movie"
            }
            val candidateId = o.optInt("id", -1)
            if (candidateId == -1) continue

            val titles = listOf(
                o.optString("title"), o.optString("name"),
                o.optString("original_title"), o.optString("original_name")
            ).filter { it.isNotBlank() }

            val candDate   = if (mediaType == "tv") o.optString("first_air_date", "") else o.optString("release_date", "")
            val candYear   = candDate.take(4).toIntOrNull()
            val candRating = o.optDouble("vote_average", Double.NaN)

            var score = 0.0
            val normClean = mbCleanTitle(normTitle)
            var titleScore = 0.0
            for (t in titles) {
                val candClean = mbCleanTitle(t)
                if (mbTokenEquals(candClean, normClean)) { titleScore = 50.0; break }
                if (candClean.contains(normClean) || normClean.contains(candClean)) titleScore = maxOf(titleScore, 20.0)
            }
            score += titleScore
            if (candYear != null && year != null && candYear == year) score += 35.0
            if (imdbRatingValue != null && !candRating.isNaN()) {
                val diff = kotlin.math.abs(candRating - imdbRatingValue)
                if (diff <= 0.5) score += 10.0 else if (diff <= 1.0) score += 5.0
            }
            if (o.has("popularity")) score += (o.optDouble("popularity", 0.0) / 100.0).coerceAtMost(5.0)

            if (score > bestScore) { bestScore = score; bestId = candidateId; bestIsTv = (mediaType == "tv") }
        }
    }

    if (bestId == null || bestScore < 40.0) return Pair(null, null)

    return try {
        val kind      = if (bestIsTv) "tv" else "movie"
        val detailUrl = "https://api.themoviedb.org/3/$kind/$bestId?api_key=1865f43a0549ca50d341dd9ab8b29f49&append_to_response=external_ids&random=${Random.nextInt()}"
        val detailJson = JSONObject(app.get(detailUrl).text)
        val imdbId = detailJson.optJSONObject("external_ids")?.optString("imdb_id")
        Pair(bestId, imdbId)
    } catch (_: Exception) { Pair(bestId, null) }
}

private suspend fun mbFetchMetaData(imdbId: String?, type: TvType): JsonNode? {
    if (imdbId.isNullOrBlank()) return null
    val metaType = if (type == TvType.TvSeries) "series" else "movie"
    val url = "https://v3-cinemeta.strem.io/meta/$metaType/$imdbId.json"
    return try {
        val resp = app.get(url).text
        mapper.readTree(resp)["meta"]
    } catch (_: Exception) { null }
}

private suspend fun mbFetchTmdbLogoUrl(
    tmdbAPI: String,
    apiKey: String,
    type: TvType,
    tmdbId: Int?,
    appLangCode: String?
): String? {
    if (tmdbId == null) return null
    val kind = if (type == TvType.Movie) "movie" else "tv"
    val url = "$tmdbAPI/$kind/$tmdbId/images?api_key=$apiKey&random=${Random.nextInt()}"
    val json = runCatching { JSONObject(app.get(url).text) }.getOrNull() ?: return null
    val logos = json.optJSONArray("logos") ?: return null
    if (logos.length() == 0) return null

    val lang = appLangCode?.trim()?.lowercase()
    fun path(o: JSONObject) = o.optString("file_path")
    fun isSvg(o: JSONObject) = path(o).endsWith(".svg", true)
    fun urlOf(o: JSONObject) = "https://image.tmdb.org/t/p/w500${path(o)}"

    var svgFallback: JSONObject? = null
    for (i in 0 until logos.length()) {
        val logo = logos.optJSONObject(i) ?: continue
        if (path(logo).isBlank()) continue
        val l = logo.optString("iso_639_1").trim().lowercase()
        if (l == lang) {
            if (!isSvg(logo)) return urlOf(logo)
            if (svgFallback == null) svgFallback = logo
        }
    }
    svgFallback?.let { return urlOf(it) }

    var best: JSONObject? = null
    for (i in 0 until logos.length()) {
        val logo = logos.optJSONObject(i) ?: continue
        if (logo.optDouble("vote_average", 0.0) <= 0) continue
        if (isSvg(logo)) continue
        val cur = best
        if (cur == null || logo.optDouble("vote_average", 0.0) > cur.optDouble("vote_average", 0.0)) best = logo
    }
    best?.let { return urlOf(it) }
    return null
}
