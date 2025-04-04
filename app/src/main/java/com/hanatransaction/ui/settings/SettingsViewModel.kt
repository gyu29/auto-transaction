package com.hanatransaction.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.data.repository.UserRepository
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    
    // User Repository
    private val userRepository: UserRepository = HanaTransactionApp.instance.userRepository
    
    // Shared Preferences
    private val sharedPreferences: SharedPreferences = HanaTransactionApp.instance.getSharedPreferences(
        "hana_transaction_prefs", 
        android.content.Context.MODE_PRIVATE
    )
    
    // Settings LiveData
    private val _useBiometric = MutableLiveData<Boolean>()
    val useBiometric: LiveData<Boolean> = _useBiometric
    
    private val _darkMode = MutableLiveData<Boolean>()
    val darkMode: LiveData<Boolean> = _darkMode
    
    private val _useSystemTheme = MutableLiveData<Boolean>()
    val useSystemTheme: LiveData<Boolean> = _useSystemTheme
    
    private val _transactionAlerts = MutableLiveData<Boolean>()
    val transactionAlerts: LiveData<Boolean> = _transactionAlerts
    
    private val _paymentReminders = MutableLiveData<Boolean>()
    val paymentReminders: LiveData<Boolean> = _paymentReminders
    
    // UI State
    private val _settingsSaved = MutableLiveData<Boolean>()
    val settingsSaved: LiveData<Boolean> = _settingsSaved
    
    init {
        loadSettings()
    }
    
    private fun loadSettings() {
        _useBiometric.value = sharedPreferences.getBoolean(KEY_USE_BIOMETRIC, false)
        _darkMode.value = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        _useSystemTheme.value = sharedPreferences.getBoolean(KEY_USE_SYSTEM_THEME, true)
        _transactionAlerts.value = sharedPreferences.getBoolean(KEY_TRANSACTION_ALERTS, true)
        _paymentReminders.value = sharedPreferences.getBoolean(KEY_PAYMENT_REMINDERS, true)
    }
    
    fun saveSettings() {
        viewModelScope.launch {
            sharedPreferences.edit().apply {
                putBoolean(KEY_USE_BIOMETRIC, _useBiometric.value ?: false)
                putBoolean(KEY_DARK_MODE, _darkMode.value ?: false)
                putBoolean(KEY_USE_SYSTEM_THEME, _useSystemTheme.value ?: true)
                putBoolean(KEY_TRANSACTION_ALERTS, _transactionAlerts.value ?: true)
                putBoolean(KEY_PAYMENT_REMINDERS, _paymentReminders.value ?: true)
                apply()
            }
            
            // Also update the user record in the database
            try {
                val currentUser = userRepository.getCurrentUserSync()
                if (currentUser != null) {
                    userRepository.updateBiometricSetting(currentUser.id, _useBiometric.value ?: false)
                }
            } catch (e: Exception) {
                // Handle error
            }
            
            _settingsSaved.value = true
        }
    }
    
    fun resetSettingsSavedFlag() {
        _settingsSaved.value = false
    }
    
    fun setUseBiometric(value: Boolean) {
        _useBiometric.value = value
    }
    
    fun setDarkMode(value: Boolean) {
        _darkMode.value = value
    }
    
    fun setUseSystemTheme(value: Boolean) {
        _useSystemTheme.value = value
    }
    
    fun setTransactionAlerts(value: Boolean) {
        _transactionAlerts.value = value
    }
    
    fun setPaymentReminders(value: Boolean) {
        _paymentReminders.value = value
    }
    
    companion object {
        private const val KEY_USE_BIOMETRIC = "use_biometric"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_USE_SYSTEM_THEME = "use_system_theme"
        private const val KEY_TRANSACTION_ALERTS = "transaction_alerts"
        private const val KEY_PAYMENT_REMINDERS = "payment_reminders"
    }
} 