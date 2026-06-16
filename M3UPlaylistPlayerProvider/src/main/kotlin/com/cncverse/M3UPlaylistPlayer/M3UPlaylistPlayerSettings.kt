package com.cncverse.M3UPlaylistPlayer

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lagradost.cloudstream3.CommonActivity.showToast

/**
 * Settings bottom sheet for M3UPlaylistPlayerProvider.
 *
 * Allows the user to:
 *  - View all saved M3U playlists
 *  - Add a new playlist (name + URL)
 *  - Edit or delete an existing playlist
 *
 * Built fully programmatically — no XML layout required.
 */
class M3UPlaylistPlayerSettings(
    private val plugin            : M3UPlaylistPlayerPlugin,
    private val prefs             : SharedPreferences,
    private val onPlaylistsChanged: () -> Unit
) : BottomSheetDialogFragment() {

    private val playlists = M3UPlaylistPlayerPlugin
        .getSavedPlaylists(prefs)
        .toMutableList()

    private lateinit var listContainer: LinearLayout

    // ── Fragment lifecycle ────────────────────────────────────────────────────

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ctx = requireActivity()
        return buildRootLayout(ctx)
    }

    // ── Build UI ──────────────────────────────────────────────────────────────

    @SuppressLint("SetTextI18n")
    private fun buildRootLayout(ctx: Context): ScrollView {
        val scroll = ScrollView(ctx).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val root = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(ctx, 16), dp(ctx, 16), dp(ctx, 16), dp(ctx, 32))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        root.addView(TextView(ctx).apply {
            text     = "📺  M3U Playlist Manager"
            textSize = 20f
            gravity  = Gravity.CENTER
            setPadding(0, 0, 0, dp(ctx, 4))
            setTypeface(typeface, android.graphics.Typeface.BOLD)
        })

        root.addView(TextView(ctx).apply {
            text     = "Add any M3U/M3U8 playlist URL to watch Live TV"
            textSize = 13f
            gravity  = Gravity.CENTER
            alpha    = 0.7f
            setPadding(0, 0, 0, dp(ctx, 16))
        })

        root.addView(Button(ctx).apply {
            text = "+ Add Playlist"
            setOnClickListener { showAddDialog() }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dp(ctx, 12) }
        })

        listContainer = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        root.addView(listContainer)
        rebuildList()

        root.addView(Button(ctx).apply {
            text = "💾  Save & Restart"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = dp(ctx, 16) }
            setOnClickListener { saveAndPromptRestart() }
        })

        scroll.addView(root)
        return scroll
    }

    // ── Playlist list ─────────────────────────────────────────────────────────

    @SuppressLint("SetTextI18n")
    private fun rebuildList() {
        val ctx = activity ?: return
        listContainer.removeAllViews()

        if (playlists.isEmpty()) {
            listContainer.addView(TextView(ctx).apply {
                text     = "No playlists added yet. Tap + Add Playlist to get started."
                textSize = 13f
                alpha    = 0.6f
                gravity  = Gravity.CENTER
                setPadding(0, dp(ctx, 12), 0, dp(ctx, 12))
            })
            return
        }

        playlists.forEachIndexed { idx, (name, url) ->
            val row = LinearLayout(ctx).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity     = Gravity.CENTER_VERTICAL
                setPadding(dp(ctx, 8), dp(ctx, 10), dp(ctx, 8), dp(ctx, 10))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { bottomMargin = dp(ctx, 6) }
            }

            row.addView(TextView(ctx).apply {
                text     = "📡"
                textSize = 20f
                setPadding(0, 0, dp(ctx, 10), 0)
            })

            val textCol = LinearLayout(ctx).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }
            textCol.addView(TextView(ctx).apply {
                text = name
                textSize = 15f
                setTypeface(typeface, android.graphics.Typeface.BOLD)
            })
            textCol.addView(TextView(ctx).apply {
                text     = if (url.length > 60) url.take(60) + "…" else url
                textSize = 11f
                alpha    = 0.6f
            })
            row.addView(textCol)

            row.addView(ImageButton(ctx).apply {
                setImageDrawable(ctx.getDrawable(android.R.drawable.ic_menu_edit))
                background = null
                setPadding(dp(ctx, 8), 0, dp(ctx, 4), 0)
                setOnClickListener { showEditDialog(idx) }
            })

            row.addView(ImageButton(ctx).apply {
                setImageDrawable(ctx.getDrawable(android.R.drawable.ic_delete))
                background = null
                setPadding(dp(ctx, 4), 0, 0, 0)
                setOnClickListener {
                    AlertDialog.Builder(ctx)
                        .setTitle("Delete Playlist")
                        .setMessage("Remove \"$name\"?")
                        .setPositiveButton("Delete") { d: DialogInterface, _: Int ->
                            d.dismiss()
                            playlists.removeAt(idx)
                            rebuildList()
                            showToast("Playlist removed")
                        }
                        .setNegativeButton("Cancel") { d: DialogInterface, _: Int -> d.dismiss() }
                        .show()
                }
            })

            listContainer.addView(row)
        }
    }

    // ── Add dialog ────────────────────────────────────────────────────────────

    private fun showAddDialog() {
        val ctx    = activity ?: return
        val layout = buildInputLayout(ctx)
        val nameEt = layout.getChildAt(1) as EditText
        val urlEt  = layout.getChildAt(3) as EditText

        AlertDialog.Builder(ctx)
            .setTitle("Add M3U Playlist")
            .setView(layout)
            .setPositiveButton("Add") { d: DialogInterface, _: Int ->
                val name = nameEt.text.toString().trim()
                val url  = urlEt.text.toString().trim()
                when {
                    name.isEmpty()          -> showToast("Please enter a name")
                    url.isEmpty()           -> showToast("Please enter a URL")
                    !url.startsWith("http") -> showToast("URL must start with http:// or https://")
                    else -> {
                        playlists.add(name to url)
                        rebuildList()
                        showToast("Playlist added! Tap Save to apply.")
                        d.dismiss()
                    }
                }
            }
            .setNegativeButton("Cancel") { d: DialogInterface, _: Int -> d.dismiss() }
            .show()
    }

    // ── Edit dialog ───────────────────────────────────────────────────────────

    private fun showEditDialog(idx: Int) {
        val ctx    = activity ?: return
        val (name, url) = playlists[idx]
        val layout = buildInputLayout(ctx)
        val nameEt = layout.getChildAt(1) as EditText
        val urlEt  = layout.getChildAt(3) as EditText
        nameEt.setText(name)
        urlEt.setText(url)

        AlertDialog.Builder(ctx)
            .setTitle("Edit Playlist")
            .setView(layout)
            .setPositiveButton("Save") { d: DialogInterface, _: Int ->
                val newName = nameEt.text.toString().trim()
                val newUrl  = urlEt.text.toString().trim()
                when {
                    newName.isEmpty()           -> showToast("Please enter a name")
                    newUrl.isEmpty()            -> showToast("Please enter a URL")
                    !newUrl.startsWith("http")  -> showToast("URL must start with http:// or https://")
                    else -> {
                        playlists[idx] = newName to newUrl
                        rebuildList()
                        showToast("Playlist updated! Tap Save to apply.")
                        d.dismiss()
                    }
                }
            }
            .setNegativeButton("Cancel") { d: DialogInterface, _: Int -> d.dismiss() }
            .show()
    }

    // ── Save & restart ────────────────────────────────────────────────────────

    private fun saveAndPromptRestart() {
        val ctx = activity ?: return
        M3UPlaylistPlayerPlugin.savePlaylists(prefs, playlists)
        onPlaylistsChanged()

        AlertDialog.Builder(ctx)
            .setTitle("Saved!")
            .setMessage("Playlists saved. Restart the app to apply changes?")
            .setPositiveButton("Restart") { d: DialogInterface, _: Int ->
                d.dismiss()
                dismissAllowingStateLoss()
                val appCtx = ctx.applicationContext
                val intent = appCtx.packageManager.getLaunchIntentForPackage(appCtx.packageName)
                val comp   = intent?.component
                if (comp != null) {
                    appCtx.startActivity(Intent.makeRestartActivityTask(comp))
                    Runtime.getRuntime().exit(0)
                }
            }
            .setNegativeButton("Later") { d: DialogInterface, _: Int ->
                d.dismiss()
                showToast("Saved. Restart the app to load new playlists.")
            }
            .show()
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun dp(ctx: Context, n: Int) = (n * ctx.resources.displayMetrics.density).toInt()

    private fun buildInputLayout(ctx: Context): LinearLayout {
        val p = dp(ctx, 16)
        val layout = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(p, dp(ctx, 8), p, 0)
        }
        layout.addView(TextView(ctx).apply { text = "Playlist Name"; textSize = 13f; alpha = 0.8f })
        layout.addView(EditText(ctx).apply {
            hint      = "e.g.  My Sports IPTV"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dp(ctx, 12) }
        })
        layout.addView(TextView(ctx).apply { text = "Playlist URL (M3U / M3U8)"; textSize = 13f; alpha = 0.8f })
        layout.addView(EditText(ctx).apply {
            hint      = "https://example.com/playlist.m3u"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
        })
        return layout
    }
}
