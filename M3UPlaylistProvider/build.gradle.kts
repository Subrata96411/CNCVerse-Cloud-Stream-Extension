// use an integer for version numbers
version = 1

android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
}

cloudstream {
    language = "en"
    description = "Play any M3U/M3U8 IPTV playlist with Live TV and DRM support. Add your own playlist URL in settings."
    authors = listOf("CNCVerse")

    /**
     * Status int as the following:
     * 0: Down
     * 1: Ok
     * 2: Slow
     * 3: Beta only
     * */
    status = 1
    tvTypes = listOf(
        "Live",
    )
    requiresResources = false

    iconUrl = "https://www.google.com/s2/favicons?domain=iptv.org&sz=%size%"
}
