// use an integer for version numbers
version = 1

android {
    buildFeatures {
        buildConfig = true
    }
}

cloudstream {
    language = "hi"
    description = "MovieBox New - Multi Language Movies and Series Provider (Fixed Auth)"
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
        "Movie",
        "TvSeries"
    )

    iconUrl = "https://github.com/NivinCNC/CNCVerse-Cloud-Stream-Extension/raw/refs/heads/master/MovieBoxProvider/icon.png"
}
