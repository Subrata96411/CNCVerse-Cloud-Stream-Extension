package com.cncverse

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lagradost.cloudstream3.CommonActivity.showToast

class AmazonSettings(
    private val plugin: AmazonProviderPlugin,
    private val sharedPref: SharedPreferences?
) : BottomSheetDialogFragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            val padding = (16 * context.resources.displayMetrics.density).toInt()
            setPadding(padding, padding, padding, padding)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val titleView = TextView(context).apply {
            text = "Amazon Provider Settings"
            textSize = 20f
            setTypeface(null, android.graphics.Typeface.BOLD)
            val margin = (12 * context.resources.displayMetrics.density).toInt()
            setPadding(0, 0, 0, margin)
        }
        layout.addView(titleView)

        val warningView = TextView(context).apply {
            text = "⚠️ DRM Warning: Most Amazon Prime Video titles are DRM-protected. Playback will fail if Widevine L3/L1 keys cannot be obtained."
            textSize = 14f
            setTextColor(android.graphics.Color.YELLOW)
            val margin = (12 * context.resources.displayMetrics.density).toInt()
            setPadding(0, 0, 0, margin)
        }
        layout.addView(warningView)

        val regionLabel = TextView(context).apply {
            text = "Select Amazon Region:"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        layout.addView(regionLabel)

        val currentRegion = sharedPref?.getString("amazon_region", "US") ?: "US"
        val radioGroup = RadioGroup(context).apply {
            orientation = RadioGroup.HORIZONTAL
            val margin = (8 * context.resources.displayMetrics.density).toInt()
            setPadding(0, 0, 0, margin)
        }
        val regions = listOf("US", "UK", "DE", "JP", "IN")
        regions.forEach { region ->
            val radioButton = RadioButton(context).apply {
                text = region
                id = View.generateViewId()
                if (region == currentRegion) {
                    isChecked = true
                }
            }
            radioGroup.addView(radioButton)
        }
        layout.addView(radioGroup)

        val cookieLabel = TextView(context).apply {
            text = "Account Cookie String:"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        layout.addView(cookieLabel)

        val currentCookie = sharedPref?.getString("amazon_cookie", "") ?: ""
        val cookieInput = EditText(context).apply {
            setText(currentCookie)
            hint = "Paste your cookie here..."
            maxLines = 5
            setSingleLine(false)
            val margin = (16 * context.resources.displayMetrics.density).toInt()
            setPadding(0, 0, 0, margin)
        }
        layout.addView(cookieInput)

        val saveButton = Button(context).apply {
            text = "Save Settings"
            setOnClickListener {
                val selectedId = radioGroup.checkedRadioButtonId
                val selectedRadioButton = radioGroup.findViewById<RadioButton>(selectedId)
                val selectedRegion = selectedRadioButton?.text?.toString() ?: "US"
                val cookie = cookieInput.text.toString().trim()

                sharedPref?.edit()?.apply {
                    putString("amazon_region", selectedRegion)
                    putString("amazon_cookie", cookie)
                    apply()
                }

                showToast("Settings saved successfully!")
                dismiss()
            }
        }
        layout.addView(saveButton)

        return layout
    }
}
