package com.cncverse

import android.content.Context
import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.lagradost.cloudstream3.utils.AppUtils.parseJson
import com.lagradost.cloudstream3.utils.AppUtils.toJson
import org.jsoup.Jsoup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * AmazonProvider – Browse Amazon Prime Video using your own account cookies.
 *
 * HOW TO USE:
 * 1. Open Amazon Prime Video in a desktop browser and log in.
 * 2. Export your cookies (e.g. using "EditThisCookie" or "Cookie-Editor" browser extension).
 * 3. Paste the full cookie string in Settings → AmazonProvider.
 *
 * ⚠️ IMPORTANT: DRM-protected content (most Prime Video titles) requires a
 *    Widevine L1/L3 license. CloudStream is not a certified Amazon player,
 *    so Widevine license requests may be blocked by Amazon's servers.
 *    Non-DRM content (some free-with-ads titles) may still play.
 */
class AmazonProvider : MainAPI() {

    override var name = "Amazon Prime Video"
    override var lang = "en"
    override val hasMainPage = true
    override val hasChromecastSupport = false
    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries
    )

    // ────────────────────────────────────────────────────────────────────────────
    // Configuration helpers
    // ────────────────────────────────────────────────────────────────────────────

    companion object {
        var context: Context? = null

        /** Marketplace IDs used by Amazon for different regions */
        private val MARKETPLACE_MAP = mapOf(
            "US" to "ATVPDKIKX0DER",
            "UK" to "A1F83G8C2ARO7P",
            "DE" to "A1PA6795UKMFR9",
            "JP" to "A1VC38T7YXB528",
            "IN" to "A21TJRUUN4KGV"
        )

        private val BASE_URLS = mapOf(
            "US" to "https://www.amazon.com",
            "UK" to "https://www.amazon.co.uk",
            "DE" to "https://www.amazon.de",
            "JP" to "https://www.amazon.co.jp",
            "IN" to "https://www.amazon.in"
        )

        private val API_URLS = mapOf(
            "US" to "https://atv-ps.amazon.com",
            "UK" to "https://atv-ps-eu.amazon.com",
            "DE" to "https://atv-ps-eu.amazon.com",
            "JP" to "https://atv-ps-fe.amazon.com",
            "IN" to "https://atv-ps-eu.amazon.com"
        )

        private const val DEVICE_TYPE_ID = "A43PXU4ZN2AL1"
        private const val FIRMWARE     = "fmw:22-app:3.0.351.3955"
        private const val SW_VERSION   = "351"
        private const val USER_AGENT   = "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Settings (stored in CloudStream's key-value store via context)
    // ────────────────────────────────────────────────────────────────────────────

    override var mainUrl: String
        get() = BASE_URLS[getRegion()] ?: "https://www.amazon.com"
        set(value) {}

    private fun getApiUrl()  = API_URLS[getRegion()]  ?: "https://atv-ps.amazon.com"
    private fun getMarketId() = MARKETPLACE_MAP[getRegion()] ?: "ATVPDKIKX0DER"
    private fun getRegion()   = context?.getSharedPreferences("AmazonProviderPrefs", Context.MODE_PRIVATE)?.getString("amazon_region", "US") ?: "US"
    private fun getCookie()   = context?.getSharedPreferences("AmazonProviderPrefs", Context.MODE_PRIVATE)?.getString("amazon_cookie", "") ?: ""

    /** Build common query-string parameters for the mobile API */
    private fun defParams(): String {
        val market = getMarketId()
        return "deviceTypeID=$DEVICE_TYPE_ID" +
               "&firmware=$FIRMWARE" +
               "&softwareVersion=$SW_VERSION" +
               "&priorityLevel=2" +
               "&format=json" +
               "&featureScheme=mobile-android-features-v13-hdr" +
               "&version=1" +
               "&screenWidth=sw1600dp" +
               "&marketplaceId=$market"
    }

    /** Common headers for all requests */
    private fun headers(): Map<String, String> {
        val cookie = getCookie()
        return buildMap {
            put("User-Agent", USER_AGENT)
            put("Accept", "application/json, text/javascript, */*; q=0.01")
            put("Accept-Language", "en-US,en;q=0.9")
            put("Origin", mainUrl)
            put("Referer", "$mainUrl/gp/video/storefront/")
            if (cookie.isNotBlank()) {
                put("Cookie", cookie)
            }
        }
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Main page sections
    // ────────────────────────────────────────────────────────────────────────────

    override val mainPage = mainPageOf(
        "${getApiUrl()}/cdp/mobile/getDataByTransform/v1/dv-android/landing/initial/v1.kt" to "Featured",
        "${getApiUrl()}/cdp/mobile/getDataByTransform/v1/dv-android/browse/v2/browseInitial.js?pageType=movies&pageId=movies" to "Movies",
        "${getApiUrl()}/cdp/mobile/getDataByTransform/v1/dv-android/browse/v2/browseInitial.js?pageType=tv&pageId=tv" to "TV Shows",
        "${getApiUrl()}/cdp/mobile/getDataByTransform/v1/dv-android/watchlist/watchlistInitial/v3.js" to "Watchlist"
    )

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse? {
        if (getCookie().isBlank()) {
            // Return a hint card when no cookie is configured
            return newHomePageResponse(
                listOf(
                    HomePageList(
                        "⚙️ Setup Required",
                        listOf(
                            newMovieSearchResponse(
                                "Tap here to configure your Amazon cookie in Settings",
                                "",
                                TvType.Movie
                            ) { this.posterUrl = null }
                        ),
                        isHorizontalImages = false
                    )
                )
            )
        }

        val apiUrl  = request.data
        val params  = defParams()

        // Build paginated URL
        val startIndex = (page - 1) * 20
        val url = if (page > 1) {
            apiUrl.replace("Initial", "Next")
                  .replace("initial", "next") + "?${params}&startIndex=$startIndex"
        } else {
            "$apiUrl?$params"
        }

        val response = app.get(url, headers = headers()).parsedSafe<AmazonApiResponse>()
            ?: return null

        val items = extractSearchResults(response)
        if (items.isEmpty()) return null

        return newHomePageResponse(
            HomePageList(request.name, items, isHorizontalImages = true),
            hasNext = items.size >= 20
        )
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Search
    // ────────────────────────────────────────────────────────────────────────────

    override suspend fun search(query: String): List<SearchResponse> {
        if (getCookie().isBlank()) return emptyList()

        val apiUrl = "${getApiUrl()}/cdp/mobile/getDataByTransform/v1/dv-android/search/searchInitial/v3.js"
        val url    = "$apiUrl?${defParams()}&phrase=${query.encodeToUrl()}"

        val response = app.get(url, headers = headers()).parsedSafe<AmazonApiResponse>()
            ?: return emptyList()

        return extractSearchResults(response)
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Load content detail page
    // ────────────────────────────────────────────────────────────────────────────

    override suspend fun load(url: String): LoadResponse? {
        // url here is the ASIN (Amazon Standard Identification Number)
        val asin = url

        val apiUrl = "${getApiUrl()}/cdp/mobile/getDataByTransform/v1/dv-android/detail/v2/user/v2.5.js"
        val detailUrl = "$apiUrl?${defParams()}&itemId=$asin&capabilities="

        val response = app.get(detailUrl, headers = headers())
            .parsedSafe<AmazonDetailResponse>()

        val title    = response?.detail?.title ?: asin
        val plot     = response?.detail?.synopsis ?: ""
        val poster   = response?.detail?.image?.replace(Regex("\\._.*_."), ".")
        val year     = response?.detail?.releaseYear
        val rating   = response?.detail?.ratingValue?.toDoubleOrNull()?.let { Score.from10(it) }
        val isMovie  = response?.detail?.contentType?.lowercase() == "movie"
        val seasons  = response?.detail?.seasons

        return if (isMovie || seasons.isNullOrEmpty()) {
            newMovieLoadResponse(title, asin, TvType.Movie, asin) {
                this.posterUrl = poster
                this.plot      = plot
                this.year      = year
                this.score     = rating
            }
        } else {
            val episodeList = mutableListOf<Episode>()
            seasons.forEachIndexed { si, season ->
                season.episodes?.forEachIndexed { ei, ep ->
                    episodeList.add(
                        newEpisode(ep.asin ?: asin) {
                            this.name    = ep.title
                            this.season  = si + 1
                            this.episode = ei + 1
                            this.posterUrl = ep.image?.replace(Regex("\\._.*_."), ".")
                            this.description = ep.synopsis
                        }
                    )
                }
            }
            newTvSeriesLoadResponse(title, asin, TvType.TvSeries, episodeList) {
                this.posterUrl = poster
                this.plot      = plot
                this.year      = year
                this.score     = rating
            }
        }
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Load video links (stream extraction)
    // ────────────────────────────────────────────────────────────────────────────

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val asin = data

        // Step 1: Get playback resources from Amazon
        val playbackUrl = "${getApiUrl()}/cdp/catalog/GetPlaybackResources"
        val playbackParams = mapOf(
            "asin"               to asin,
            "consumptionType"    to "Streaming",
            "desiredResources"   to "PlaybackUrls,SubtitleUrls,ForcedNarratives,TransitionTimecodes",
            "deviceTypeID"       to DEVICE_TYPE_ID,
            "firmware"           to FIRMWARE,
            "softwareVersion"    to SW_VERSION,
            "marketplaceId"      to getMarketId(),
            "format"             to "json",
            "audioTrackId"       to "all",
            "videoMaterialType"  to "Feature",
            "operatingSystemName" to "android",
            "deviceDrmOverride"  to "CENC",
            "deviceStreamingTechnologyOverride" to "DASH",
            "deviceVideoCodecOverride"  to "H264",
            "deviceHdrFormatsOverride"  to "None",
            "deviceVideoQualityOverride" to "HD",
            "deviceBitrateAdaptationsOverride" to "CVBR,CBR",
            "subtitleFormat"     to "TTMLv2",
            "languageFeature"    to "MLFv2",
            "gdprEnabled"        to "false",
            "grantedByCustomer"  to "0"
        )

        val fullPlaybackUrl = "$playbackUrl?" + playbackParams.entries
            .joinToString("&") { "${it.key}=${it.value.encodeToUrl()}" }

        val resp = app.get(fullPlaybackUrl, headers = headers())

        if (!resp.isSuccessful) {
            // DRM / auth failure
            throw ErrorLoadingException(
                "Amazon denied the playback request (${resp.code}). " +
                "This is likely because:\n" +
                "• Your cookie has expired – please refresh it.\n" +
                "• The content is DRM-protected and CloudStream is not a certified player.\n" +
                "• You are not subscribed to Prime Video."
            )
        }

        val playbackJson = resp.parsedSafe<AmazonPlaybackResponse>()

        // Step 2: Extract stream URL
        val streamUrl = extractStreamUrl(playbackJson)
        if (streamUrl != null) {
            callback(
                newExtractorLink(
                    source  = this.name,
                    name    = this.name,
                    url     = streamUrl,
                    type    = ExtractorLinkType.DASH
                ) {
                    this.quality = Qualities.Unknown.value
                    this.referer = mainUrl
                    this.headers = headers()
                }
            )
        }

        // Step 3: Extract subtitles
        val subtitles = playbackJson?.subtitles
        subtitles?.forEach { sub ->
            if (sub.url != null && sub.languageCode != null) {
                subtitleCallback(
                    SubtitleFile(
                        lang = sub.languageCode,
                        url  = sub.url
                    )
                )
            }
        }

        return streamUrl != null
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Internal helpers
    // ────────────────────────────────────────────────────────────────────────────

    private fun extractStreamUrl(resp: AmazonPlaybackResponse?): String? {
        resp ?: return null

        // Try audioVideoUrls first (older format)
        val avSets = resp.audioVideoUrls?.avCdnUrlSets
        if (!avSets.isNullOrEmpty()) {
            for (cdn in avSets) {
                val urlInfo = cdn.avUrlInfoList?.firstOrNull()
                if (urlInfo?.url != null) return urlInfo.url
            }
        }

        // Try playbackUrls (newer format)
        val playbackUrls = resp.playbackUrls
        if (playbackUrls != null) {
            val defaultId = playbackUrls.defaultUrlSetId
            val urlSet = playbackUrls.urlSets?.get(defaultId)
                ?: playbackUrls.urlSets?.values?.firstOrNull()
            val manifestUrl = urlSet?.urls?.get("manifest")
            if (manifestUrl != null) return manifestUrl
        }

        return null
    }

    private fun extractSearchResults(response: AmazonApiResponse): List<SearchResponse> {
        val results = mutableListOf<SearchResponse>()

        // Walk through all widget collections looking for items
        response.widgets?.forEach { widget ->
            widget.items?.forEach { item ->
                val asin    = item.titleID ?: item.asin ?: return@forEach
                val title   = item.title ?: return@forEach
                val poster  = item.image?.replace(Regex("\\._.*_."), ".")
                val isMovie = item.contentType?.lowercase() == "movie"

                val searchResp = if (isMovie) {
                    newMovieSearchResponse(title, asin, TvType.Movie) {
                        this.posterUrl = poster
                    }
                } else {
                    newTvSeriesSearchResponse(title, asin, TvType.TvSeries) {
                        this.posterUrl = poster
                    }
                }
                results.add(searchResp)
            }
        }

        return results
    }

    private fun String.encodeToUrl() =
        java.net.URLEncoder.encode(this, "UTF-8")

    // ────────────────────────────────────────────────────────────────────────────
    // Data classes for JSON parsing
    // ────────────────────────────────────────────────────────────────────────────

    data class AmazonApiResponse(
        val widgets: List<Widget>?   = null,
        val rows: List<Widget>?      = null
    )

    data class Widget(
        val items: List<CatalogItem>? = null
    )

    data class CatalogItem(
        val titleID:     String? = null,
        val asin:        String? = null,
        val title:       String? = null,
        val image:       String? = null,
        val contentType: String? = null,
        val synopsis:    String? = null
    )

    data class AmazonDetailResponse(
        val detail: DetailItem? = null
    )

    data class DetailItem(
        val title:       String?      = null,
        val synopsis:    String?      = null,
        val image:       String?      = null,
        val releaseYear: Int?         = null,
        val ratingValue: String?      = null,
        val contentType: String?      = null,
        val seasons:     List<Season>? = null
    )

    data class Season(
        val episodes: List<EpisodeItem>? = null
    )

    data class EpisodeItem(
        val asin:     String? = null,
        val title:    String? = null,
        val image:    String? = null,
        val synopsis: String? = null
    )

    data class AmazonPlaybackResponse(
        val audioVideoUrls: AudioVideoUrls?  = null,
        val playbackUrls:   PlaybackUrls?    = null,
        val subtitles:      List<Subtitle>?  = null
    )

    data class AudioVideoUrls(
        val avCdnUrlSets: List<CdnUrlSet>? = null
    )

    data class CdnUrlSet(
        val avUrlInfoList: List<AvUrlInfo>? = null,
        val cdn:           String?          = null
    )

    data class AvUrlInfo(
        val url: String? = null
    )

    data class PlaybackUrls(
        val defaultUrlSetId: String?                  = null,
        val urlSets:         Map<String, UrlSetInfo>? = null
    )

    data class UrlSetInfo(
        val urls: Map<String, String>? = null
    )

    data class Subtitle(
        val url:          String? = null,
        val languageCode: String? = null,
        val label:        String? = null
    )
}
