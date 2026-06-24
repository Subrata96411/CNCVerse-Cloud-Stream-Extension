// use an integer for version numbers
version = 7

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
    description = "M3UPlaylistPlayerProvider extension"
    authors = listOf("NivinCNC")
    requiresResources = false
}
