// use an integer for version numbers
version = 5

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
    description = "Add your own m3u playlists"
    authors = listOf("NivinCNC")

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

    iconUrl = "https://play-lh.googleusercontent.com/V4t4JeQV2Cu9u72hKuqOW5c0IfwcZuuVS1d9PF2uJsW3rlIq-aOMTrT5bABVGaAFTcM=w480-h960-rw"
}
