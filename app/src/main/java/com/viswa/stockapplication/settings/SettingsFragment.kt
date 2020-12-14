package com.viswa.stockapplication.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.viswa.stockapplication.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}