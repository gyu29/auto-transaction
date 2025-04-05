package com.hanatransaction.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.hanatransaction.R
import com.hanatransaction.databinding.ActivityLoginBinding
import com.hanatransaction.security.AuthManager
import com.hanatransaction.security.BiometricHelper
import com.hanatransaction.security.PinManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authManager: AuthManager
    private lateinit var pinManager: PinManager
    private lateinit var biometricHelper: BiometricHelper
    
    private var pin = ""
    private var authenticationAttempts = 0
    
    // View references
    private lateinit var textViewTitle: TextView
    private lateinit var textViewError: TextView
    private lateinit var buttonBiometric: MaterialButton
    private lateinit var pinDots: List<View>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize managers
        authManager = AuthManager(this)
        pinManager = PinManager(this)
        biometricHelper = BiometricHelper(this)
        
        // Initialize views
        textViewTitle = binding.textViewTitle
        textViewError = binding.textViewError
        buttonBiometric = binding.buttonBiometric
        
        pinDots = listOf(
            binding.pinDot1,
            binding.pinDot2,
            binding.pinDot3,
            binding.pinDot4
        )
        
        // Check if PIN is set - if not, start SetupActivity for new users
        if (!pinManager.isPinSet()) {
            startSetupActivity()
            return
        }
        
        // User already has a PIN, proceed with PIN verification
        textViewTitle.text = getString(R.string.pin_enter)
        
        // Get the biometric setting from shared preferences
        val sharedPreferences = getSharedPreferences("hana_transaction_prefs", android.content.Context.MODE_PRIVATE)
        val biometricEnabled = sharedPreferences.getBoolean("use_biometric", false)
        
        // Set up biometric button visibility based on both device capability and user preference
        buttonBiometric.visibility = if (biometricEnabled && biometricHelper.isBiometricAvailable()) 
                                      View.VISIBLE else View.GONE
        
        // Set up biometric button
        buttonBiometric.setOnClickListener {
            // Prevent multiple rapid attempts
            if (authenticationAttempts > 0) return@setOnClickListener
            
            authenticationAttempts++
            
            biometricHelper.authenticate(
                this,
                onSuccess = {
                    authManager.login()
                    authenticationSuccessful()
                },
                onError = { errorCode, errString ->
                    // Reset counter on error
                    authenticationAttempts = 0
                    textViewError.text = getString(R.string.biometric_error, errString)
                    textViewError.visibility = View.VISIBLE
                },
                onFailed = {
                    // Reset counter on failure
                    authenticationAttempts = 0
                    textViewError.text = getString(R.string.biometric_failed)
                    textViewError.visibility = View.VISIBLE
                }
            )
        }
        
        // Set up number pad
        setupKeypad()
    }
    
    /**
     * Start the SetupActivity for new users
     */
    private fun startSetupActivity() {
        val intent = Intent(this, SetupActivity::class.java)
        startActivityForResult(intent, SETUP_REQUEST_CODE)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == SETUP_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Setup completed successfully
                authenticationSuccessful()
            } else {
                // Setup canceled or failed
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }
    
    override fun onBackPressed() {
        // User cancelled login - set result and finish
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
    
    private fun setupKeypad() {
        // Number buttons
        val numberButtons = listOf(
            binding.buttonZero,
            binding.buttonOne,
            binding.buttonTwo,
            binding.buttonThree,
            binding.buttonFour,
            binding.buttonFive,
            binding.buttonSix,
            binding.buttonSeven,
            binding.buttonEight,
            binding.buttonNine
        )
        
        for (i in numberButtons.indices) {
            numberButtons[i].setOnClickListener {
                onDigitPressed(i.toString())
            }
        }
        
        // Delete button
        binding.buttonDelete.setOnClickListener {
            handleDelete()
        }
        
        // Cancel button
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }
    
    private fun handleDelete() {
        if (pin.isNotEmpty()) {
            pin = pin.dropLast(1)
            updatePinDots()
        }
    }
    
    private fun onDigitPressed(digit: String) {
        // Hide any error message
        textViewError.visibility = View.INVISIBLE
        
        if (pin.length < 4) {
            pin += digit
            updatePinDots()
            
            if (pin.length == 4) {
                // Verify PIN
                if (pinManager.verifyPin(pin)) {
                    authManager.login()
                    authenticationSuccessful()
                } else {
                    // Show error message
                    textViewError.text = getString(R.string.pin_wrong)
                    textViewError.visibility = View.VISIBLE
                    
                    // Reset PIN entry
                    pin = ""
                    updatePinDots()
                }
            }
        }
    }
    
    private fun updatePinDots() {
        for (i in pinDots.indices) {
            pinDots[i].isEnabled = i < pin.length
        }
    }
    
    private fun authenticationSuccessful() {
        // Set result and finish
        setResult(Activity.RESULT_OK)
        finish()
    }
    
    companion object {
        private const val SETUP_REQUEST_CODE = 1002
    }
} 