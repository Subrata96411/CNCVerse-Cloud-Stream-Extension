// use an integer for version numbers
version = 1

android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.core:core:1.16.0")
    implementation("com.google.android.material:material:1.12.0")
}

cloudstream {
    language = "en"

    description = "Amazon Prime Video – Browse and stream content using your own Amazon account cookies. Note: DRM-protected content may not play on all devices."
    authors = listOf("NivinCNC")

    /**
     * Status int as the following:
     * 0: Down
     * 1: Ok
     * 2: Slow
     * 3: Beta only
     */
    status = 3 // Beta
    tvTypes = listOf(
        "Movie",
        "TvSeries"
    )

    iconUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Amazon_Prime_Video_logo.svg/1200px-Amazon_Prime_Video_logo.svg.png"
}
