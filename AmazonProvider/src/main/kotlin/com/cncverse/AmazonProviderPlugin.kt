package com.cncverse

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class AmazonProviderPlugin : Plugin() {
    override fun load(context: Context) {
        AmazonProvider.context = context
        registerMainAPI(AmazonProvider())

        val activity = context as? AppCompatActivity
        if (activity != null) {
            val sharedPref = context.getSharedPreferences("AmazonProviderPrefs", Context.MODE_PRIVATE)
            openSettings = {
                val frag = AmazonSettings(this, sharedPref)
                frag.show(activity.supportFragmentManager, "AmazonSettings")
            }
        }
    }
}
