package com.hanatransaction.auth

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.hanatransaction.R
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BiometricAuthManager(private val context: Context) {
    
    private val biometricManager = BiometricManager.from(context)
    
    fun canAuthenticate(): Boolean {
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) ==
                BiometricManager.BIOMETRIC_SUCCESS
    }
    
    suspend fun authenticate(activity: FragmentActivity): Boolean {
        return suspendCancellableCoroutine { continuation ->
            val executor = ContextCompat.getMainExecutor(activity)
            
            val biometricPrompt = BiometricPrompt(activity, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        continuation.resume(true)
                    }
                    
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        continuation.resumeWithException(
                            Exception(context.getString(R.string.auth_failed))
                        )
                    }
                    
                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        continuation.resumeWithException(
                            Exception(context.getString(R.string.auth_failed))
                        )
                    }
                })
            
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(context.getString(R.string.auth_required))
                .setSubtitle(context.getString(R.string.auth_required))
                .setNegativeButtonText(context.getString(R.string.cancel))
                .build()
            
            biometricPrompt.authenticate(promptInfo)
        }
    }
} 