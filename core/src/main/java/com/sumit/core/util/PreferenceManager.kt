package com.sumit.core.util

import android.content.Context
import com.sumit.core.di.qualifier.ApplicationContext
import com.sumit.core.di.scopes.PerApplication
import javax.inject.Inject

@PerApplication
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    // region VARIABLES

    /**
     * Lazy variable to create encrypted preferences
     * */
    private val sharedPreferences by lazy {
        val preferences =
            context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        preferences
    }

    // region COMPANION OBJECT
    companion object {
        private const val PREFERENCES_FILE = "prefs_file"

        private const val DARK_THEME = "dark_theme"
    }
    // endregion

    // regionDARK THEME

    /**
     * Store Dark theme status to preferences
     * @param darkTheme dark theme boolean
     * */
    fun enableDarkTheme(darkTheme: Boolean) {
        setBooleanPolicy(DARK_THEME, darkTheme)
    }

    /**
     * Get dark theme status , true if enabled
     * @return true if enabled, default true
     * */
    fun darkThemeEnabled(): Boolean {
        return getBooleanPolicy(DARK_THEME, true)
    }
    // endregion


    // region BOOLEAN RULES

    private fun setBooleanPolicy(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun getBooleanPolicy(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // endregion

}