package com.cncverse

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.lagradost.cloudstream3.plugins.Plugin
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin

/**
 * Plugin entry-point for the M3U Playlist IPTV provider.
 *
 * On load:
 *  1. Reads saved playlist URLs/names from SharedPreferences.
 *  2. Registers one [M3UPlaylistProvider] per saved playlist.
 *  3. Opens [M3UPlaylistSettings] when the user taps the plugin settings icon.
 */
@CloudstreamPlugin
class M3UPlaylistPlugin : Plugin() {

    private lateinit var prefs: SharedPreferences

    override fun load(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // Register each saved playlist
        getSavedPlaylists(prefs).forEach { (name, url) ->
            registerMainAPI(M3UPlaylistProvider(customName = name, playlistUrl = url))
        }

        // Show settings UI when user opens settings for this plugin
        val act = context as AppCompatActivity
        openSettings = {
            M3UPlaylistSettings(
                plugin = this,
                prefs  = prefs,
                onPlaylistsChanged = { refreshProviders(context) }
            ).show(act.supportFragmentManager, "M3UPlaylistSettings")
        }
    }

    /** Re-registers all providers after the user edits their playlist list. */
    fun refreshProviders(context: Context) {
        // Unregister all and re-add — CloudStream re-loads on next navigation
        getSavedPlaylists(prefs).forEach { (name, url) ->
            registerMainAPI(M3UPlaylistProvider(customName = name, playlistUrl = url))
        }
    }

    companion object {
        const val PREF_NAME      = "M3UPlaylistProvider"
        const val PREF_COUNT_KEY = "playlist_count"

        /** Reads all saved (name, url) pairs from shared prefs. */
        fun getSavedPlaylists(prefs: SharedPreferences): List<Pair<String, String>> {
            val count = prefs.getInt(PREF_COUNT_KEY, 0)
            return (0 until count).mapNotNull { i ->
                val n = prefs.getString("playlist_name_$i", null) ?: return@mapNotNull null
                val u = prefs.getString("playlist_url_$i",  null) ?: return@mapNotNull null
                n to u
            }
        }

        /** Persists the given list of (name, url) pairs. */
        fun savePlaylists(prefs: SharedPreferences, playlists: List<Pair<String, String>>) {
            prefs.edit().apply {
                // Clear old entries
                val oldCount = prefs.getInt(PREF_COUNT_KEY, 0)
                for (i in 0 until oldCount) {
                    remove("playlist_name_$i")
                    remove("playlist_url_$i")
                }
                putInt(PREF_COUNT_KEY, playlists.size)
                playlists.forEachIndexed { i, (n, u) ->
                    putString("playlist_name_$i", n)
                    putString("playlist_url_$i",  u)
                }
                apply()
            }
        }
    }
}
