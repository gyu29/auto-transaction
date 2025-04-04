package com.hanatransaction.ui.theme

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

/**
 * Manages application theme settings (light, dark, system)
 */
class ThemeManager(context: Context) {
    
    companion object {
        const val THEME_LIGHT = 0
        const val THEME_DARK = 1
        const val THEME_SYSTEM = 2
        
        private const val PREF_THEME = "pref_theme"
    }
    
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "hana_transaction_theme",
        Context.MODE_PRIVATE
    )
    
    /**
     * Get the current theme setting
     * @return The current theme setting (THEME_LIGHT, THEME_DARK, or THEME_SYSTEM)
     */
    fun getThemeSetting(): Int {
        return sharedPreferences.getInt(PREF_THEME, THEME_SYSTEM)
    }
    
    /**
     * Apply the current theme setting to the application
     */
    fun applyTheme() {
        when (getThemeSetting()) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
    
    /**
     * Set the theme setting
     * @param theme The theme setting to use (THEME_LIGHT, THEME_DARK, or THEME_SYSTEM)
     */
    fun setTheme(theme: Int) {
        if (theme in THEME_LIGHT..THEME_SYSTEM) {
            sharedPreferences.edit()
                .putInt(PREF_THEME, theme)
                .apply()
            applyTheme()
        }
    }
    
    /**
     * Set dark mode
     * @param enabled Whether dark mode should be enabled
     */
    fun setDarkMode(enabled: Boolean) {
        setTheme(if (enabled) THEME_DARK else THEME_LIGHT)
    }
    
    /**
     * Set whether to use system theme
     * @param useSystemTheme Whether to use the system theme
     */
    fun setUseSystemTheme(useSystemTheme: Boolean) {
        if (useSystemTheme) {
            setTheme(THEME_SYSTEM)
        }
    }
    
    /**
     * Check if dark mode is enabled
     * @return True if dark mode is enabled, false otherwise
     */
    fun isDarkModeEnabled(): Boolean {
        return getThemeSetting() == THEME_DARK
    }
    
    /**
     * Check if system theme is used
     * @return True if system theme is used, false otherwise
     */
    fun isUsingSystemTheme(): Boolean {
        return getThemeSetting() == THEME_SYSTEM
    }
} 