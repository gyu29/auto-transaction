package com.hanatransaction.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hanatransaction.HanaTransactionApp
import com.hanatransaction.auth.BiometricAuthManager
import com.hanatransaction.data.repository.UserRepository
import kotlinx.coroutines.launch

abstract class AuthenticatedFragment : Fragment() {
    
    private lateinit var biometricAuthManager: BiometricAuthManager
    private lateinit var userRepository: UserRepository
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        biometricAuthManager = BiometricAuthManager(requireContext())
        userRepository = (requireActivity().application as HanaTransactionApp).userRepository
    }
    
    protected suspend fun requireAuthentication(): Boolean {
        return try {
            // Check if biometric auth is enabled for the user in shared preferences
            val sharedPreferences = requireContext().getSharedPreferences(
                "hana_transaction_prefs", 
                Context.MODE_PRIVATE
            )
            val biometricEnabled = sharedPreferences.getBoolean("use_biometric", false)
            
            // If biometric is disabled in settings, no need to authenticate
            if (!biometricEnabled) {
                return true
            }
            
            // Check if device supports biometric auth
            if (!biometricAuthManager.canAuthenticate()) {
                Toast.makeText(
                    requireContext(),
                    "Biometric authentication is not available on this device",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
            
            // Authenticate
            biometricAuthManager.authenticate(requireActivity())
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            false
        }
    }
} 