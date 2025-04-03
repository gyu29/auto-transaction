package com.hanatransaction.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user of the application with associated bank account information
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    
    // Personal information
    val name: String,
    val email: String?,
    val phoneNumber: String?,
    
    // Banking information
    val accountNumber: String,
    val bankCode: String = "081", // Default Hana Bank code
    
    // Settings
    val useBiometricAuth: Boolean = true,
    val enableNotifications: Boolean = true,
    val darkModeEnabled: Boolean = false,
    
    // Authentication
    val authToken: String? = null,
    val tokenExpiry: Long? = null
) 