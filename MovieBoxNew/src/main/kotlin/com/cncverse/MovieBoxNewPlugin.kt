package com.cncverse

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class MovieBoxNewPlugin : Plugin() {
    override fun load(context: Context) {
        MovieBoxNewProvider.context = context
        registerMainAPI(MovieBoxNewProvider())
    }
}
