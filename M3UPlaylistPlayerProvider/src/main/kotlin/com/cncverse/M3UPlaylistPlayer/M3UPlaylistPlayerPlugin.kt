package com.cncverse.M3UPlaylistPlayer

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.lagradost.cloudstream3.CommonActivity
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import com.lagradost.cloudstream3.utils.AppUtils.parseJson

@CloudstreamPlugin
class M3UPlaylistPlayerPlugin : Plugin() {
    private val sharedPref: SharedPreferences? by lazy {
        CommonActivity.activity?.getSharedPreferences("M3UPlaylistPlayerPrefs", Context.MODE_PRIVATE)
    }

    override fun load(context: Context) {
        M3UPlaylistPlayer.INSTANCE.context = context
        val playlistsJson = sharedPref?.getString("playlists", "[]") ?: "[]"
        val playlists: List<PlaylistEntry> = try {
            parseJson(playlistsJson)
        } catch (e: Exception) {
            emptyList()
        }

        for (playlist in playlists) {
            if (!playlist.name.isNullOrBlank() && !playlist.url.isNullOrBlank()) {
                registerMainAPI(M3UPlaylistPlayer(playlist.name, playlist.url))
            }
        }

        val activity = context as? AppCompatActivity
        openSettings = { ctx ->
            if (activity != null) {
                val frag = Settings(this, sharedPref, playlists)
                frag.show(activity.supportFragmentManager, "M3UPlaylistPlayerSettings")
            }
        }
    }
}
