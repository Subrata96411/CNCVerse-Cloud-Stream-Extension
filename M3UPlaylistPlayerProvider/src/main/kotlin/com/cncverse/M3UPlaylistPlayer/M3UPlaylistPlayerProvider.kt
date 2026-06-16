package com.cncverse.M3UPlaylistPlayer

import android.util.Base64
import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.lagradost.cloudstream3.utils.AppUtils.parseJson
import com.lagradost.cloudstream3.utils.AppUtils.toJson
import java.io.InputStream
import java.util.UUID
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

/**
 * M3UPlaylistPlayerProvider — Universal IPTV player for CloudStream.
 *
 * Supports any M3U/M3U8 playlist URL with:
 *  - Channel grouping by group-title
 *  - Full DRM support (ClearKey & Widevine via KODIPROP)
 *  - AES-128 HLS (handled by ExoPlayer)
 *  - User-Agent, Cookie, Referer headers per-channel
 *  - Search across all channels
 */
class M3UPlaylistPlayerProvider(
    private val customName: String = "My IPTV",
    private val playlistUrl: String = ""
) : MainAPI() {

    companion object {
        const val EXT_M3U  = "#EXTM3U"
        const val EXT_INF  = "#EXTINF"
        const val EXT_VLC  = "#EXTVLCOPT"
    }

    override var lang         = "en"
    override var mainUrl      = playlistUrl.ifEmpty { "https://iptv-org.github.io/iptv/index.m3u" }
    override var name         = customName
    override val hasMainPage  = true
    override val hasChromecastSupport = true
    override val supportedTypes = setOf(TvType.Live)

    // ── HTTP ─────────────────────────────────────────────────────────────────

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val req = chain.request().newBuilder()
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; rv:91.0) Gecko/20100101 Firefox/91.0")
                    .header("Accept", "*/*")
                    .header("Cache-Control", "no-cache")
                    .build()
                chain.proceed(req)
            }
            .build()
    }

    private fun fetchUrl(url: String, headers: Map<String, String> = emptyMap()): String {
        val req = Request.Builder().url(url)
        headers.forEach { (k, v) -> req.header(k, v) }
        return httpClient.newCall(req.build()).execute().use { it.body.string() }
    }

    // ── Playlist cache ────────────────────────────────────────────────────────

    @Volatile private var cachedPlaylist: Playlist? = null

    private fun getPlaylist(): Playlist {
        return cachedPlaylist ?: run {
            val content = try { fetchUrl(mainUrl) } catch (_: Exception) { "" }
            val parsed  = M3UParser().parseM3U(content)
            cachedPlaylist = parsed
            parsed
        }
    }

    // ── MainPage ──────────────────────────────────────────────────────────────

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val playlist = getPlaylist()
        val grouped  = playlist.items.groupBy {
            it.attributes["group-title"]?.takeIf { g -> g.isNotBlank() } ?: "Uncategorized"
        }
        val sections = grouped.map { (group, channels) ->
            HomePageList(
                name              = group,
                list              = channels.map { it.toSearchResponse(name) },
                isHorizontalImages = true
            )
        }
        return newHomePageResponse(sections, hasNext = false)
    }

    // ── Search ────────────────────────────────────────────────────────────────

    override suspend fun search(query: String): List<SearchResponse> {
        val playlist = getPlaylist()
        return playlist.items
            .filter { it.title?.contains(query, ignoreCase = true) == true }
            .map { it.toSearchResponse(name) }
    }

    // ── Load ──────────────────────────────────────────────────────────────────

    override suspend fun load(url: String): LoadResponse {
        val data = parseJson<ChannelData>(url)
        return newLiveStreamLoadResponse(
            name  = data.title,
            url   = url,
            dataUrl = url
        ) {
            this.posterUrl = data.poster
            this.plot      = data.group
        }
    }

    // ── LoadLinks ─────────────────────────────────────────────────────────────

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val ch = parseJson<ChannelData>(data)

        val headers = mutableMapOf<String, String>()
        headers.putAll(ch.headers)
        if (ch.userAgent.isNotEmpty()) headers["User-Agent"] = ch.userAgent
        if (ch.cookie.isNotEmpty())    headers["Cookie"]     = ch.cookie

        val streamUrl = ch.url ?: return false

        when {
            // ── MPD / DASH ────────────────────────────────────────────────
            streamUrl.contains(".mpd", ignoreCase = true) ||
            streamUrl.contains("manifest", ignoreCase = true) -> {
                handleDashOrDrm(ch, streamUrl, headers, callback)
            }

            // ── Plain HLS / TS ────────────────────────────────────────────
            else -> {
                callback.invoke(
                    newExtractorLink(
                        source  = name,
                        name    = ch.title,
                        url     = streamUrl,
                        type    = INFER_TYPE
                    ) {
                        this.quality = Qualities.Unknown.value
                        this.referer = ""
                        if (headers.isNotEmpty()) this.headers = headers
                    }
                )
            }
        }
        return true
    }

    // ── DRM / DASH helper ─────────────────────────────────────────────────────

    private suspend fun handleDashOrDrm(
        ch: ChannelData,
        streamUrl: String,
        headers: Map<String, String>,
        callback: (ExtractorLink) -> Unit
    ) {
        val hasStaticKeys  = ch.key.isNotEmpty() && ch.keyid.isNotEmpty() &&
                             ch.key.trim() != "null" && ch.keyid.trim() != "null"
        val hasLicenseUrl  = ch.licenseUrl.isNotEmpty() && ch.licenseUrl.trim() != "null"
        val hasMultiKeys   = ch.drmKeys.isNotEmpty()

        when {
            // ClearKey — static kid:key from playlist
            hasStaticKeys || hasMultiKeys -> {
                var resolvedKey = ch.key
                var resolvedKid = ch.keyid

                // Try to resolve kid from the actual MPD when we have a multi-key map
                if (hasMultiKeys) {
                    try {
                        val mpdText = fetchUrl(streamUrl, headers)
                        val kidRegex = Regex("""cenc:default_KID=["']([0-9a-fA-F\-]{36})["']""")
                        val mpdKid   = kidRegex.find(mpdText)?.groupValues?.get(1)
                            ?.replace("-", "")?.lowercase()
                        val mapped   = ch.drmKeys[mpdKid]
                        if (!mapped.isNullOrEmpty() && !mpdKid.isNullOrEmpty()) {
                            resolvedKey = mapped
                            resolvedKid = mpdKid
                        }
                    } catch (_: Exception) {}
                }

                val playerKey = resolvedKey.hexToBase64Url() ?: resolvedKey
                val playerKid = resolvedKid.hexToBase64Url() ?: resolvedKid

                callback.invoke(
                    newDrmExtractorLink(name, ch.title, streamUrl, INFER_TYPE, CLEARKEY_UUID) {
                        this.quality = Qualities.Unknown.value
                        if (headers.isNotEmpty()) this.headers = headers
                        this.key = playerKey
                        this.kid = playerKid
                    }
                )
            }

            // Widevine / ClearKey via license server URL
            hasLicenseUrl -> {
                // Try to fetch key from a ClearKey JSON server first
                try {
                    val mpdText   = fetchUrl(streamUrl, headers)
                    val kidRegex  = Regex("""cenc:default_KID=["']([0-9a-fA-F\-]{36})["']""")
                    val rawKid    = kidRegex.find(mpdText)?.groupValues?.get(1) ?: UUID.randomUUID().toString()
                    val kidBytes  = rawKid.replace("-", "").chunked(2).map { it.toInt(16).toByte() }.toByteArray()
                    val kidB64    = Base64.encodeToString(kidBytes, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)

                    val fetchedKey = fetchClearKey(ch.licenseUrl, kidB64)
                    if (fetchedKey.isNotEmpty()) {
                        callback.invoke(
                            newDrmExtractorLink(name, ch.title, streamUrl, INFER_TYPE, CLEARKEY_UUID) {
                                this.quality = Qualities.Unknown.value
                                if (headers.isNotEmpty()) this.headers = headers
                                this.key = fetchedKey
                                this.kid = kidB64
                            }
                        )
                        return
                    }
                } catch (_: Exception) {}

                // Fall back to passing the license URL (Widevine)
                callback.invoke(
                    newDrmExtractorLink(name, ch.title, streamUrl, INFER_TYPE, CLEARKEY_UUID) {
                        this.quality = Qualities.Unknown.value
                        if (headers.isNotEmpty()) this.headers = headers
                        this.licenseUrl = ch.licenseUrl
                    }
                )
            }

            // No DRM info — try plain DASH
            else -> {
                callback.invoke(
                    newExtractorLink(name, ch.title, streamUrl, ExtractorLinkType.DASH) {
                        this.quality = Qualities.Unknown.value
                        this.referer = ""
                        if (headers.isNotEmpty()) this.headers = headers
                    }
                )
            }
        }
    }

    private fun fetchClearKey(licenseUrl: String, kid: String): String {
        return try {
            val json     = """{"kids":["$kid"],"type":"temporary"}"""
            val body     = json.toRequestBody("application/json; charset=utf-8".toMediaType())
            val response = httpClient.newCall(
                Request.Builder().url(licenseUrl).post(body).build()
            ).execute().use { it.body.string() }

            val obj  = JSONObject(response)
            val keys = obj.optJSONArray("keys") ?: return ""
            keys.optJSONObject(0)?.optString("k") ?: ""
        } catch (_: Exception) { "" }
    }

    // ── Extension helpers ─────────────────────────────────────────────────────

    private fun String.hexToBase64Url(): String? {
        val normalized = trim().replace("-", "")
        if (normalized.isEmpty() || normalized.length % 2 != 0) return null
        if (!normalized.matches(Regex("^[0-9a-fA-F]+"))) return null
        return try {
            val bytes = normalized.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
            Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
        } catch (_: Exception) { null }
    }

    private fun PlaylistItem.toSearchResponse(providerName: String): LiveSearchResponse {
        val poster   = attributes["tvg-logo"]?.takeIf { it.isNotBlank() && it != "null" }
        val group    = attributes["group-title"] ?: ""
        val loadData = ChannelData(
            url        = url ?: "",
            title      = title ?: "Unknown",
            poster     = poster ?: "",
            group      = group,
            key        = key ?: "",
            keyid      = keyid ?: "",
            userAgent  = userAgent ?: "",
            cookie     = cookie ?: "",
            licenseUrl = licenseUrl ?: "",
            drmKeys    = drmKeys,
            headers    = headers
        )
        return newLiveSearchResponse(
            name    = title ?: "Unknown",
            url     = loadData.toJson(),
            type    = TvType.Live
        ) {
            this.posterUrl = poster
        }
    }
}

// ── Data classes ──────────────────────────────────────────────────────────────

data class ChannelData(
    val url        : String,
    val title      : String,
    val poster     : String,
    val group      : String,
    val key        : String,
    val keyid      : String,
    val userAgent  : String,
    val cookie     : String,
    val licenseUrl : String,
    val drmKeys    : Map<String, String> = emptyMap(),
    val headers    : Map<String, String> = emptyMap()
)

// ── M3U Parser ────────────────────────────────────────────────────────────────

class M3UParser {

    fun parseM3U(content: String): Playlist = parseM3U(content.byteInputStream())

    fun parseM3U(input: InputStream): Playlist {
        val lines = input.bufferedReader().readLines()
        val items = mutableListOf<PlaylistItem>()
        var i     = 0

        var bufCookie     : String?              = null
        var bufUserAgent  : String?              = null
        var bufHeaders    : Map<String, String>  = emptyMap()
        var bufKey        : String?              = null
        var bufKeyId      : String?              = null
        var bufLicenseUrl : String?              = null
        var bufDrmKeys    : Map<String, String>  = emptyMap()
        var bufTitle      : String?              = null
        var bufAttributes : Map<String, String>  = emptyMap()

        while (i < lines.size) {
            val line = lines[i].trim()

            if (line.isNotEmpty()) {
                when {
                    line.startsWith(EXT_INF) -> {
                        bufTitle      = line.getTitle()
                        bufAttributes = line.getAttributes()
                        val keyAttr   = bufAttributes["key"] ?: bufAttributes["drm-key"]
                        val kidAttr   = bufAttributes["keyid"] ?: bufAttributes["drm-keyid"] ?: bufAttributes["kid"]
                        if (bufKey   == null) bufKey   = keyAttr
                        if (bufKeyId == null) bufKeyId = kidAttr
                    }

                    line.startsWith("#EXTHTTP:") -> {
                        val json = line.removePrefix("#EXTHTTP:").trim()
                        try {
                            val map = parseJson<Map<String, String>>(json)
                            map["cookie"]?.let     { bufCookie    = it }
                            map["user-agent"]?.let { bufUserAgent = it }
                        } catch (_: Exception) {}
                    }

                    line.startsWith(EXT_VLC) -> {
                        line.getTagValue("http-user-agent")?.let { bufUserAgent = it }
                        val ref = line.getTagValue("http-referrer") ?: line.getTagValue("http-referer")
                        if (ref != null) bufHeaders = bufHeaders + mapOf("Referer" to ref)
                    }

                    line.startsWith("#KODIPROP:inputstream.adaptive.license_type=") -> {
                        // license type info — not used directly but validates DRM type
                    }

                    line.startsWith("#KODIPROP:inputstream.adaptive.license_key=") -> {
                        val licKey = line.removePrefix("#KODIPROP:inputstream.adaptive.license_key=").trim()
                        when {
                            licKey.startsWith("http://") || licKey.startsWith("https://") -> {
                                bufLicenseUrl = licKey
                            }
                            licKey.startsWith("{") -> {
                                val parsed = parseClearKeyJson(licKey)
                                if (parsed.isNotEmpty()) {
                                    bufDrmKeys = parsed
                                    parsed.entries.firstOrNull()?.let { e ->
                                        if (bufKey   == null) bufKey   = e.value
                                        if (bufKeyId == null) bufKeyId = e.key
                                    }
                                }
                            }
                            else -> {
                                // kid:key or kid,key
                                val sep   = if (licKey.contains(":")) ":" else ","
                                val parts = licKey.split(sep, limit = 2)
                                if (parts.size == 2) {
                                    val kid = parts[0].trim().hexNormalize()
                                    val key = parts[1].trim().hexNormalize()
                                    if (bufKeyId == null) bufKeyId = kid
                                    if (bufKey   == null) bufKey   = key
                                }
                            }
                        }
                    }

                    !line.startsWith("#") -> {
                        // URL line — collect full multi-line URL
                        var fullLine = line
                        var j = i + 1
                        while (j < lines.size && !lines[j].trim().startsWith("#") && lines[j].trim().isNotEmpty()) {
                            fullLine += lines[j].trim()
                            j++
                        }
                        i = j - 1

                        val url        = fullLine.getUrl()
                        val urlUA      = fullLine.getUrlParam("user-agent")
                        val urlRef     = fullLine.getUrlParam("referer") ?: fullLine.getUrlParam("referrer")
                        val urlCookie  = fullLine.getUrlParam("cookie")
                        val urlOrigin  = fullLine.getUrlParam("origin")
                        val urlKey     = fullLine.getUrlParam("key")
                        val urlKeyid   = fullLine.getUrlParam("keyid")
                        val urlLicUrl  = fullLine.getUrlParam("licenseUrl")

                        var finalHeaders = bufHeaders
                        if (urlRef    != null) finalHeaders = finalHeaders + mapOf("Referer" to urlRef)
                        if (urlOrigin != null) finalHeaders = finalHeaders + mapOf("Origin" to urlOrigin)

                        items.add(PlaylistItem(
                            title      = bufTitle ?: "Unknown",
                            attributes = bufAttributes,
                            url        = url,
                            headers    = finalHeaders,
                            userAgent  = urlUA    ?: bufUserAgent,
                            cookie     = urlCookie ?: bufCookie,
                            key        = urlKey   ?: bufKey,
                            keyid      = urlKeyid ?: bufKeyId,
                            licenseUrl = urlLicUrl ?: bufLicenseUrl,
                            drmKeys    = bufDrmKeys
                        ))

                        // Reset buffers
                        bufCookie = null; bufUserAgent = null; bufHeaders = emptyMap()
                        bufKey    = null; bufKeyId     = null; bufLicenseUrl = null
                        bufDrmKeys = emptyMap(); bufTitle = null; bufAttributes = emptyMap()
                    }
                }
            }
            i++
        }
        return Playlist(items)
    }

    // ── String extensions ─────────────────────────────────────────────────────

    private fun String.replaceQuotesAndTrim() = replace("\"", "").trim()

    private fun String.getTitle(): String? {
        val afterTag = replace(Regex("(#EXTINF:.?[0-9]+)", RegexOption.IGNORE_CASE), "").trim()
        var lastComma = -1; var inQuotes = false
        afterTag.forEachIndexed { idx, c -> when (c) { '"' -> inQuotes = !inQuotes; ',' -> if (!inQuotes) lastComma = idx } }
        return if (lastComma != -1) afterTag.substring(lastComma + 1).trim().replaceQuotesAndTrim()
        else afterTag.split(",").lastOrNull()?.replaceQuotesAndTrim()
    }

    private fun String.getAttributes(): Map<String, String> {
        val afterTag = replace(Regex("(#EXTINF:.?[0-9]+)", RegexOption.IGNORE_CASE), "").trim()
        var lastComma = -1; var inQuotes = false
        afterTag.forEachIndexed { idx, c -> when (c) { '"' -> inQuotes = !inQuotes; ',' -> if (!inQuotes) lastComma = idx } }
        val attrStr = if (lastComma != -1) afterTag.substring(0, lastComma).trim() else afterTag.trim()
        val attrs   = mutableMapOf<String, String>()
        Regex("""(\w[-\w]*)\s*=\s*(?:"([^"]*)"|([^\s,]+))""", RegexOption.IGNORE_CASE)
            .findAll(attrStr)
            .forEach { m -> attrs[m.groups[1]?.value ?: ""] = (m.groups[2]?.value ?: m.groups[3]?.value ?: "").trim() }
        return attrs
    }

    private fun String.getUrl()                  = split("|").firstOrNull()?.replaceQuotesAndTrim()
    private fun String.getTagValue(key: String)  = Regex("$key=(.*)", RegexOption.IGNORE_CASE).find(this)?.groupValues?.get(1)?.replaceQuotesAndTrim()
    private fun String.getUrlParam(key: String): String? {
        val paramsStr = replace(Regex("^(.*)\\|", RegexOption.IGNORE_CASE), "").replaceQuotesAndTrim()
        return paramsStr.split("&").firstOrNull { it.split("=", limit = 2).getOrNull(0)?.trim().equals(key, ignoreCase = true) }
            ?.split("=", limit = 2)?.getOrNull(1)?.trim()?.replaceQuotesAndTrim()
    }

    private fun String.hexNormalize(): String {
        val h = trim().replace("-", "")
        return if (h.matches(Regex("^[0-9a-fA-F]+$"))) h.lowercase() else this
    }

    private fun parseClearKeyJson(json: String): Map<String, String> {
        return try {
            val obj  = JSONObject(json)
            val keys = obj.optJSONArray("keys") ?: return emptyMap()
            val map  = mutableMapOf<String, String>()
            for (idx in 0 until keys.length()) {
                val item = keys.optJSONObject(idx) ?: continue
                val kid  = item.optString("kid").trim().hexNormalize()
                val key  = item.optString("k").trim().hexNormalize()
                if (kid.isNotEmpty() && key.isNotEmpty()) map[kid] = key
            }
            map
        } catch (_: Exception) { emptyMap() }
    }

    companion object {
        private const val EXT_INF = "#EXTINF"
        private const val EXT_VLC = "#EXTVLCOPT"
    }
}

data class Playlist(val items: List<PlaylistItem> = emptyList())

data class PlaylistItem(
    val title      : String?              = null,
    val attributes : Map<String, String>  = emptyMap(),
    val headers    : Map<String, String>  = emptyMap(),
    val url        : String?              = null,
    val userAgent  : String?              = null,
    val key        : String?              = null,
    val keyid      : String?              = null,
    val cookie     : String?              = null,
    val licenseUrl : String?              = null,
    val drmKeys    : Map<String, String>  = emptyMap()
)
