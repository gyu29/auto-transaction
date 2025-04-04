package com.hanatransaction.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.MessageDigest

/**
 * Manages PIN creation, verification, and storage using encrypted shared preferences
 */
class PinManager(context: Context) {
    
    companion object {
        private const val ENCRYPTED_PREFS_FILENAME = "secured_hana_prefs"
        private const val KEY_PIN_HASH = "pin_hash"
        private const val PIN_LENGTH = 4
    }
    
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    
    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        ENCRYPTED_PREFS_FILENAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    
    /**
     * Check if a PIN exists
     */
    fun isPinSet(): Boolean {
        return encryptedSharedPreferences.contains(KEY_PIN_HASH)
    }
    
    /**
     * Create a new PIN
     * @param pin The PIN to set
     * @return True if PIN was set successfully, false otherwise
     */
    fun createPin(pin: String): Boolean {
        if (!isValidPin(pin)) return false
        
        val pinHash = hashPin(pin)
        return encryptedSharedPreferences.edit()
            .putString(KEY_PIN_HASH, pinHash)
            .commit()
    }
    
    /**
     * Verify a PIN
     * @param pin The PIN to verify
     * @return True if PIN is correct, false otherwise
     */
    fun verifyPin(pin: String): Boolean {
        if (!isPinSet()) return false
        if (!isValidPin(pin)) return false
        
        val storedHash = encryptedSharedPreferences.getString(KEY_PIN_HASH, "") ?: ""
        val inputHash = hashPin(pin)
        return storedHash == inputHash
    }
    
    /**
     * Change the PIN
     * @param currentPin The current PIN
     * @param newPin The new PIN
     * @return True if PIN was changed successfully, false otherwise
     */
    fun changePin(currentPin: String, newPin: String): Boolean {
        if (!verifyPin(currentPin)) return false
        if (!isValidPin(newPin)) return false
        
        return createPin(newPin)
    }
    
    /**
     * Clear the PIN
     */
    fun clearPin() {
        encryptedSharedPreferences.edit()
            .remove(KEY_PIN_HASH)
            .apply()
    }
    
    /**
     * Check if a PIN is valid (only digits and correct length)
     */
    private fun isValidPin(pin: String): Boolean {
        if (pin.length != PIN_LENGTH) return false
        return pin.all { it.isDigit() }
    }
    
    /**
     * Hash a PIN using SHA-256
     */
    private fun hashPin(pin: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(pin.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
} 