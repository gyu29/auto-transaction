package com.hanatransaction.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.hanatransaction.R
import com.hanatransaction.security.BiometricHelper
import com.hanatransaction.security.PinManager
import com.hanatransaction.ui.MainActivity

/**
 * Fragment for PIN entry, creation, and verification
 */
class PinFragment : Fragment() {
    
    companion object {
        const val MODE_CREATE = 0
        const val MODE_VERIFY = 1
        const val MODE_CHANGE = 2
        
        const val ARG_MODE = "arg_mode"
    }
    
    private lateinit var pinManager: PinManager
    private lateinit var biometricHelper: BiometricHelper
    
    private var mode = MODE_VERIFY
    private var pin = ""
    private var confirmPin = ""
    private var currentPin = ""
    private var isPinConfirmation = false
    
    // Views
    private lateinit var textViewTitle: TextView
    private lateinit var textViewSubtitle: TextView
    private lateinit var textViewError: TextView
    private lateinit var buttonBiometric: MaterialButton
    private lateinit var pinDots: List<View>
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pin, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize managers
        pinManager = PinManager(requireContext())
        biometricHelper = BiometricHelper(requireContext())
        
        // Get mode from arguments
        mode = arguments?.getInt(ARG_MODE, MODE_VERIFY) ?: MODE_VERIFY
        
        // Initialize views
        textViewTitle = view.findViewById(R.id.textViewTitle)
        textViewSubtitle = view.findViewById(R.id.textViewSubtitle)
        textViewError = view.findViewById(R.id.textViewError)
        buttonBiometric = view.findViewById(R.id.buttonBiometric)
        
        pinDots = listOf(
            view.findViewById(R.id.pinDot1),
            view.findViewById(R.id.pinDot2),
            view.findViewById(R.id.pinDot3),
            view.findViewById(R.id.pinDot4)
        )
        
        // Set initial states
        updateUI()
        
        // Setup keypad buttons
        setupKeypad(view)
        
        // Setup biometric button
        setupBiometricButton()
    }
    
    private fun updateUI() {
        // Update title and subtitle based on mode
        when (mode) {
            MODE_CREATE -> {
                if (!isPinConfirmation) {
                    textViewTitle.text = getString(R.string.pin_setup)
                    textViewSubtitle.text = ""
                } else {
                    textViewTitle.text = getString(R.string.pin_confirm)
                    textViewSubtitle.text = ""
                }
                buttonBiometric.visibility = View.GONE
            }
            MODE_VERIFY -> {
                textViewTitle.text = getString(R.string.pin_enter)
                textViewSubtitle.text = ""
                buttonBiometric.visibility = if (biometricHelper.isBiometricAvailable()) View.VISIBLE else View.GONE
            }
            MODE_CHANGE -> {
                if (currentPin.isEmpty()) {
                    textViewTitle.text = getString(R.string.pin_current)
                    textViewSubtitle.text = ""
                } else if (!isPinConfirmation) {
                    textViewTitle.text = getString(R.string.pin_new)
                    textViewSubtitle.text = ""
                } else {
                    textViewTitle.text = getString(R.string.pin_confirm)
                    textViewSubtitle.text = ""
                }
                buttonBiometric.visibility = View.GONE
            }
        }
        
        // Update PIN dots
        updatePinDots()
        
        // Hide error message
        textViewError.visibility = View.INVISIBLE
    }
    
    private fun updatePinDots() {
        val currentPin = when {
            mode == MODE_CHANGE && currentPin.isNotEmpty() && !isPinConfirmation -> pin
            isPinConfirmation -> confirmPin
            else -> pin
        }
        
        for (i in pinDots.indices) {
            pinDots[i].isEnabled = i < currentPin.length
        }
    }
    
    private fun setupKeypad(view: View) {
        // Number buttons
        val numberButtons = listOf(
            view.findViewById<MaterialButton>(R.id.buttonZero),
            view.findViewById(R.id.buttonOne),
            view.findViewById(R.id.buttonTwo),
            view.findViewById(R.id.buttonThree),
            view.findViewById(R.id.buttonFour),
            view.findViewById(R.id.buttonFive),
            view.findViewById(R.id.buttonSix),
            view.findViewById(R.id.buttonSeven),
            view.findViewById(R.id.buttonEight),
            view.findViewById(R.id.buttonNine)
        )
        
        for (i in numberButtons.indices) {
            numberButtons[i].setOnClickListener {
                onDigitPressed(i.toString())
            }
        }
        
        // Cancel button
        view.findViewById<MaterialButton>(R.id.buttonCancel).setOnClickListener {
            findNavController().navigateUp()
        }
        
        // Delete button
        view.findViewById<MaterialButton>(R.id.buttonDelete).setOnClickListener {
            onDeletePressed()
        }
    }
    
    private fun setupBiometricButton() {
        buttonBiometric.setOnClickListener {
            if (biometricHelper.isBiometricAvailable()) {
                biometricHelper.authenticate(
                    requireActivity(),
                    onSuccess = {
                        // Authentication successful, proceed to main screen
                        proceedToApp()
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
            } else {
                textViewError.text = getString(R.string.biometric_not_available)
                textViewError.visibility = View.VISIBLE
            }
        }
    }
    
    private fun onDigitPressed(digit: String) {
        // Hide any error message
        textViewError.visibility = View.INVISIBLE
        
        // Handle digit input based on mode
        when (mode) {
            MODE_CREATE -> handleCreateModeInput(digit)
            MODE_VERIFY -> handleVerifyModeInput(digit)
            MODE_CHANGE -> handleChangeModeInput(digit)
        }
    }
    
    private fun handleCreateModeInput(digit: String) {
        if (!isPinConfirmation) {
            // First PIN entry
            if (pin.length < 4) {
                pin += digit
                updatePinDots()
                
                if (pin.length == 4) {
                    // Move to confirmation
                    isPinConfirmation = true
                    updateUI()
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
                        // Save PIN
                        pinManager.createPin(pin)
                        
                        // Show success message and proceed
                        textViewError.text = getString(R.string.pin_created)
                        textViewError.visibility = View.VISIBLE
                        
                        // Proceed to app after a short delay
                        view?.postDelayed({
                            proceedToApp()
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
    
    private fun handleVerifyModeInput(digit: String) {
        if (pin.length < 4) {
            pin += digit
            updatePinDots()
            
            if (pin.length == 4) {
                // Verify PIN
                if (pinManager.verifyPin(pin)) {
                    proceedToApp()
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
    
    private fun handleChangeModeInput(digit: String) {
        if (currentPin.isEmpty()) {
            // Current PIN entry
            if (pin.length < 4) {
                pin += digit
                updatePinDots()
                
                if (pin.length == 4) {
                    // Verify current PIN
                    if (pinManager.verifyPin(pin)) {
                        // Store current PIN and move to new PIN entry
                        currentPin = pin
                        pin = ""
                        updateUI()
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
        } else if (!isPinConfirmation) {
            // New PIN entry
            if (pin.length < 4) {
                pin += digit
                updatePinDots()
                
                if (pin.length == 4) {
                    // Move to confirmation
                    isPinConfirmation = true
                    updateUI()
                }
            }
        } else {
            // New PIN confirmation
            if (confirmPin.length < 4) {
                confirmPin += digit
                updatePinDots()
                
                if (confirmPin.length == 4) {
                    // Check if PINs match
                    if (pin == confirmPin) {
                        // Change PIN
                        pinManager.changePin(currentPin, pin)
                        
                        // Show success message and proceed
                        textViewError.text = getString(R.string.pin_changed)
                        textViewError.visibility = View.VISIBLE
                        
                        // Proceed to app after a short delay
                        view?.postDelayed({
                            findNavController().navigateUp()
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
    
    private fun onDeletePressed() {
        // Determine which PIN to modify
        when {
            mode == MODE_CHANGE && currentPin.isNotEmpty() && isPinConfirmation && confirmPin.isNotEmpty() -> {
                confirmPin = confirmPin.dropLast(1)
            }
            mode == MODE_CHANGE && currentPin.isNotEmpty() && pin.isNotEmpty() -> {
                pin = pin.dropLast(1)
            }
            mode == MODE_CREATE && isPinConfirmation && confirmPin.isNotEmpty() -> {
                confirmPin = confirmPin.dropLast(1)
            }
            pin.isNotEmpty() -> {
                pin = pin.dropLast(1)
            }
        }
        
        updatePinDots()
    }
    
    private fun proceedToApp() {
        // In a real app, navigate to main screen or finish activity
        // Here we just navigate back, assuming this is for testing
        activity?.let {
            val mainIntent = Intent(it, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(mainIntent)
            it.finish()
        }
    }
} 