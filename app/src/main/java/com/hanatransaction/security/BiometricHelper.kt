package com.hanatransaction.security

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.hanatransaction.R
import java.util.concurrent.Executor

/**
 * Helper class for biometric authentication
 */
class BiometricHelper(private val context: Context) {

    private lateinit var executor: Executor
    private var biometricPrompt: BiometricPrompt? = null
    private var isAuthenticating = false

    /**
     * Check if biometric authentication is available on the device
     */
    fun isBiometricAvailable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            else -> false
        }
    }

    /**
     * Authenticate with biometrics
     * @param activity The activity
     * @param title Title for the biometric prompt
     * @param subtitle Subtitle for the biometric prompt
     * @param description Description for the biometric prompt
     * @param onSuccess Callback for successful authentication
     * @param onError Callback for authentication error
     * @param onFailed Callback for authentication failure
     */
    fun authenticate(
        activity: FragmentActivity,
        title: String = context.getString(R.string.biometric_prompt_title),
        subtitle: String = context.getString(R.string.biometric_prompt_subtitle),
        description: String = context.getString(R.string.biometric_prompt_description),
        onSuccess: () -> Unit,
        onError: (errorCode: Int, errString: CharSequence) -> Unit = { _, _ -> },
        onFailed: () -> Unit = {}
    ) {
        // Prevent multiple authentication attempts
        if (isAuthenticating) {
            return
        }
        
        isAuthenticating = true
        executor = ContextCompat.getMainExecutor(context)
        
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setDescription(description)
            .setNegativeButtonText(context.getString(android.R.string.cancel))
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()

        // Cancel any existing prompt before creating a new one
        biometricPrompt?.cancelAuthentication()
        
        biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    isAuthenticating = false
                    
                    // Add a small delay to ensure UI is updated properly
                    Handler(Looper.getMainLooper()).postDelayed({
                        onSuccess()
                    }, 300)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    isAuthenticating = false
                    onError(errorCode, errString)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    isAuthenticating = false
                    onFailed()
                }
            })

        biometricPrompt?.authenticate(promptInfo)
    }
    
    /**
     * Cancel ongoing authentication if any
     */
    fun cancelAuthentication() {
        biometricPrompt?.cancelAuthentication()
        isAuthenticating = false
    }
} 