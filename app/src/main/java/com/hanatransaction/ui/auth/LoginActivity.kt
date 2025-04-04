package com.hanatransaction.ui.auth

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
import com.hanatransaction.ui.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authManager: AuthManager
    private lateinit var pinManager: PinManager
    private lateinit var biometricHelper: BiometricHelper
    
    private var pin = ""
    private var isPinCreation = false
    private var isPinConfirmation = false
    private var confirmPin = ""
    
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
        
        // Check if PIN is set - if not, prompt for creation
        isPinCreation = !pinManager.isPinSet()
        if (isPinCreation) {
            textViewTitle.text = getString(R.string.pin_setup)
        } else {
            textViewTitle.text = getString(R.string.pin_enter)
        }
        
        // Set up biometric button visibility
        buttonBiometric.visibility = if (!isPinCreation && biometricHelper.isBiometricAvailable()) 
                                      View.VISIBLE else View.GONE
        
        // Set up biometric button
        buttonBiometric.setOnClickListener {
            biometricHelper.authenticate(
                this,
                onSuccess = {
                    authManager.login()
                    startMainActivity()
                },
                onError = { _, errString ->
                    textViewError.text = getString(R.string.biometric_error, errString)
                    textViewError.visibility = View.VISIBLE
                },
                onFailed = {
                    textViewError.text = getString(R.string.biometric_failed)
                    textViewError.visibility = View.VISIBLE
                }
            )
        }
        
        // Set up number pad
        setupKeypad()
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
        if (isPinCreation && isPinConfirmation && confirmPin.isNotEmpty()) {
            confirmPin = confirmPin.dropLast(1)
        } else if (pin.isNotEmpty()) {
            pin = pin.dropLast(1)
        }
        updatePinDots()
    }
    
    private fun onDigitPressed(digit: String) {
        // Hide any error message
        textViewError.visibility = View.INVISIBLE
        
        if (isPinCreation) {
            handlePinCreation(digit)
        } else {
            handlePinVerification(digit)
        }
    }
    
    private fun handlePinCreation(digit: String) {
        if (!isPinConfirmation) {
            // First PIN entry
            if (pin.length < 4) {
                pin += digit
                updatePinDots()
                
                if (pin.length == 4) {
                    // Move to confirmation
                    isPinConfirmation = true
                    textViewTitle.text = getString(R.string.pin_confirm)
                }
            }
        } else {
            // PIN confirmation entry
            if (confirmPin.length < 4) {
                confirmPin += digit
                updatePinDots()
                
                if (confirmPin.length == 4) {
                    // Check if PINs match
                    if (pin == confirmPin) {
                        // Save PIN and proceed
                        pinManager.createPin(pin)
                        authManager.login()
                        
                        // Show success message and proceed
                        textViewError.text = getString(R.string.pin_created)
                        textViewError.visibility = View.VISIBLE
                        
                        // Proceed to app after a short delay
                        binding.root.postDelayed({
                            startMainActivity()
                        }, 1000)
                    } else {
                        // Show error message
                        textViewError.text = getString(R.string.pin_not_match)
                        textViewError.visibility = View.VISIBLE
                        
                        // Reset confirmation
                        confirmPin = ""
                        updatePinDots()
                    }
                }
            }
        }
    }
    
    private fun handlePinVerification(digit: String) {
        if (pin.length < 4) {
            pin += digit
            updatePinDots()
            
            if (pin.length == 4) {
                // Verify PIN
                if (pinManager.verifyPin(pin)) {
                    authManager.login()
                    startMainActivity()
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
        val currentPin = if (isPinCreation && isPinConfirmation) confirmPin else pin
        
        for (i in pinDots.indices) {
            pinDots[i].isEnabled = i < currentPin.length
        }
    }
    
    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
} 