package com.hanatransaction.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hanatransaction.R
import com.hanatransaction.databinding.ActivitySetupBinding
import com.hanatransaction.security.AuthManager
import com.hanatransaction.security.BiometricHelper
import com.hanatransaction.security.PinManager
import com.hanatransaction.ui.MainActivity
import kotlinx.coroutines.launch

/**
 * Activity for first-time setup of the app
 * This guides the user through:
 * 1. Hana Bank credentials input
 * 2. PIN creation
 * 3. Biometric setup (optional)
 * 4. Other preferences
 */
class SetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetupBinding
    private lateinit var authManager: AuthManager
    private lateinit var pinManager: PinManager
    private lateinit var biometricHelper: BiometricHelper
    
    private val viewModel: SetupViewModel by viewModels()
    
    // Setup flow state
    private var currentStep = STEP_CREDENTIALS
    private var pin = ""
    private var confirmPin = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize managers
        authManager = AuthManager(this)
        pinManager = PinManager(this)
        biometricHelper = BiometricHelper(this)
        
        setupObservers()
        setupCredentialsStep()
        updateStepIndicator()
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.isLoading.observe(this@SetupActivity) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
        
        lifecycleScope.launch {
            viewModel.error.observe(this@SetupActivity) { error ->
                error?.let {
                    binding.textViewError.text = it
                    binding.textViewError.visibility = View.VISIBLE
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.accountVerified.observe(this@SetupActivity) { verified ->
                if (verified) {
                    moveToNextStep()
                }
            }
        }
    }
    
    private fun setupCredentialsStep() {
        // Show credentials layout
        binding.layoutCredentials.visibility = View.VISIBLE
        binding.layoutPin.visibility = View.GONE
        binding.layoutBiometric.visibility = View.GONE
        binding.layoutComplete.visibility = View.GONE
        
        // Set title
        binding.textViewTitle.text = getString(R.string.setup_credentials_title)
        binding.textViewSubtitle.text = getString(R.string.setup_credentials_subtitle)
        
        // Setup input fields
        binding.editTextUsername.addTextChangedListener(createTextWatcher())
        binding.editTextPassword.addTextChangedListener(createTextWatcher())
        
        // Setup next button
        binding.buttonNext.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            
            if (username.isBlank() || password.isBlank()) {
                binding.textViewError.text = getString(R.string.error_fields_required)
                binding.textViewError.visibility = View.VISIBLE
                return@setOnClickListener
            }
            
            // Verify credentials with Hana Bank (mock for now)
            viewModel.verifyAccount(username, password)
        }
    }
    
    private fun setupPinStep() {
        // Show PIN layout
        binding.layoutCredentials.visibility = View.GONE
        binding.layoutPin.visibility = View.VISIBLE
        binding.layoutBiometric.visibility = View.GONE
        binding.layoutComplete.visibility = View.GONE
        
        // Set title
        binding.textViewTitle.text = getString(R.string.pin_setup)
        binding.textViewSubtitle.text = getString(R.string.setup_pin_subtitle)
        
        // Setup keypad
        setupKeypad()
    }
    
    private fun setupBiometricStep() {
        // Show biometric layout
        binding.layoutCredentials.visibility = View.GONE
        binding.layoutPin.visibility = View.GONE
        binding.layoutBiometric.visibility = View.VISIBLE
        binding.layoutComplete.visibility = View.GONE
        
        // Set title
        binding.textViewTitle.text = getString(R.string.setup_biometric_title)
        binding.textViewSubtitle.text = getString(R.string.setup_biometric_subtitle)
        
        // Check if biometric is available
        val isBiometricAvailable = biometricHelper.isBiometricAvailable()
        
        if (isBiometricAvailable) {
            binding.buttonEnableBiometric.setOnClickListener {
                biometricHelper.authenticate(
                    this,
                    onSuccess = {
                        // Save biometric preference
                        val sharedPreferences = getSharedPreferences(
                            "hana_transaction_prefs", 
                            MODE_PRIVATE
                        )
                        sharedPreferences.edit()
                            .putBoolean("use_biometric", true)
                            .apply()
                            
                        moveToNextStep()
                    },
                    onError = { _, errString ->
                        binding.textViewError.text = getString(R.string.biometric_error, errString)
                        binding.textViewError.visibility = View.VISIBLE
                    },
                    onFailed = {
                        binding.textViewError.text = getString(R.string.biometric_failed)
                        binding.textViewError.visibility = View.VISIBLE
                    }
                )
            }
            
            binding.buttonSkipBiometric.setOnClickListener {
                // Don't enable biometric
                val sharedPreferences = getSharedPreferences(
                    "hana_transaction_prefs", 
                    MODE_PRIVATE
                )
                sharedPreferences.edit()
                    .putBoolean("use_biometric", false)
                    .apply()
                    
                moveToNextStep()
            }
        } else {
            // Biometric not available on this device
            binding.textViewBiometricNotAvailable.visibility = View.VISIBLE
            binding.buttonEnableBiometric.visibility = View.GONE
            
            binding.buttonSkipBiometric.text = getString(R.string.continue_setup)
            binding.buttonSkipBiometric.setOnClickListener {
                moveToNextStep()
            }
        }
    }
    
    private fun setupCompleteStep() {
        // Show complete layout
        binding.layoutCredentials.visibility = View.GONE
        binding.layoutPin.visibility = View.GONE
        binding.layoutBiometric.visibility = View.GONE
        binding.layoutComplete.visibility = View.VISIBLE
        
        // Set title
        binding.textViewTitle.text = getString(R.string.setup_complete_title)
        binding.textViewSubtitle.text = getString(R.string.setup_complete_subtitle)
        
        // Setup finish button
        binding.buttonFinish.setOnClickListener {
            // Log the user in
            authManager.login()
            
            // Return success result
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
    
    private fun setupKeypad() {
        // Initialize PIN dots
        val pinDots = listOf(
            binding.pinDot1,
            binding.pinDot2,
            binding.pinDot3,
            binding.pinDot4
        )
        
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
                onDigitPressed(i.toString(), pinDots)
            }
        }
        
        // Delete button
        binding.buttonDelete.setOnClickListener {
            if (confirmPin.isNotEmpty()) {
                confirmPin = confirmPin.dropLast(1)
            } else if (pin.isNotEmpty()) {
                pin = pin.dropLast(1)
            }
            updatePinDots(pinDots)
        }
    }
    
    private fun onDigitPressed(digit: String, pinDots: List<View>) {
        // Hide any error message
        binding.textViewError.visibility = View.INVISIBLE
        
        if (pin.length < 4 && confirmPin.isEmpty()) {
            // First PIN entry
            pin += digit
            updatePinDots(pinDots)
            
            if (pin.length == 4) {
                // Move to confirmation
                binding.textViewSubtitle.text = getString(R.string.pin_confirm)
            }
        } else if (confirmPin.length < 4) {
            // PIN confirmation entry
            confirmPin += digit
            updatePinDots(pinDots)
            
            if (confirmPin.length == 4) {
                // Check if PINs match
                if (pin == confirmPin) {
                    // Save PIN and proceed
                    pinManager.createPin(pin)
                    
                    // Move to next step
                    moveToNextStep()
                } else {
                    // Show error message
                    binding.textViewError.text = getString(R.string.pin_not_match)
                    binding.textViewError.visibility = View.VISIBLE
                    
                    // Reset confirmation
                    confirmPin = ""
                    updatePinDots(pinDots)
                }
            }
        }
    }
    
    private fun updatePinDots(pinDots: List<View>) {
        val currentPin = if (confirmPin.isNotEmpty()) confirmPin else pin
        
        for (i in pinDots.indices) {
            pinDots[i].isEnabled = i < currentPin.length
        }
    }
    
    private fun createTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Hide error when user types
                binding.textViewError.visibility = View.INVISIBLE
            }
            
            override fun afterTextChanged(s: Editable?) {}
        }
    }
    
    private fun moveToNextStep() {
        when (currentStep) {
            STEP_CREDENTIALS -> {
                currentStep = STEP_PIN
                setupPinStep()
            }
            STEP_PIN -> {
                currentStep = STEP_BIOMETRIC
                setupBiometricStep()
            }
            STEP_BIOMETRIC -> {
                currentStep = STEP_COMPLETE
                setupCompleteStep()
            }
        }
        updateStepIndicator()
    }
    
    private fun updateStepIndicator() {
        val stepIndicators = listOf(
            binding.stepIndicator1,
            binding.stepIndicator2,
            binding.stepIndicator3,
            binding.stepIndicator4
        )
        
        for (i in stepIndicators.indices) {
            stepIndicators[i].isSelected = i <= currentStep - 1
        }
    }
    
    override fun onBackPressed() {
        if (currentStep > STEP_CREDENTIALS) {
            // Go back to previous step
            currentStep--
            when (currentStep) {
                STEP_CREDENTIALS -> setupCredentialsStep()
                STEP_PIN -> setupPinStep()
                STEP_BIOMETRIC -> setupBiometricStep()
            }
            updateStepIndicator()
        } else {
            // Exit setup
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
    
    companion object {
        const val STEP_CREDENTIALS = 1
        const val STEP_PIN = 2
        const val STEP_BIOMETRIC = 3
        const val STEP_COMPLETE = 4
    }
} 