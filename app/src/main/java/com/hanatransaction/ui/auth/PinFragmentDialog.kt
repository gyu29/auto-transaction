package com.hanatransaction.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.hanatransaction.R
import com.hanatransaction.security.BiometricHelper
import com.hanatransaction.security.PinManager

/**
 * Dialog Fragment for PIN verification when app starts
 */
class PinFragmentDialog : DialogFragment() {
    
    companion object {
        fun newInstance(): PinFragmentDialog {
            return PinFragmentDialog()
        }
    }
    
    private lateinit var pinManager: PinManager
    private lateinit var biometricHelper: BiometricHelper
    private lateinit var authViewModel: AuthViewModel
    
    private var pin = ""
    
    // Views
    private lateinit var textViewTitle: TextView
    private lateinit var textViewError: TextView
    private lateinit var buttonBiometric: MaterialButton
    private lateinit var pinDots: List<View>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_HanaTransaction_FullScreenDialog)
    }
    
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
        
        // Initialize ViewModel
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        
        // Initialize views
        textViewTitle = view.findViewById(R.id.textViewTitle)
        textViewError = view.findViewById(R.id.textViewError)
        buttonBiometric = view.findViewById(R.id.buttonBiometric)
        
        pinDots = listOf(
            view.findViewById(R.id.pinDot1),
            view.findViewById(R.id.pinDot2),
            view.findViewById(R.id.pinDot3),
            view.findViewById(R.id.pinDot4)
        )
        
        // Set initial UI state
        textViewTitle.text = getString(R.string.pin_enter)
        buttonBiometric.visibility = if (biometricHelper.isBiometricAvailable()) View.VISIBLE else View.GONE
        
        // Setup keypad buttons
        setupKeypad(view)
        
        // Setup biometric button
        setupBiometricButton()
    }
    
    private fun updatePinDots() {
        for (i in pinDots.indices) {
            pinDots[i].isEnabled = i < pin.length
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
        
        // Cancel button - exit app
        view.findViewById<MaterialButton>(R.id.buttonCancel).setOnClickListener {
            requireActivity().finish()
        }
        
        // Delete button
        view.findViewById<MaterialButton>(R.id.buttonDelete).setOnClickListener {
            if (pin.isNotEmpty()) {
                pin = pin.dropLast(1)
                updatePinDots()
            }
        }
    }
    
    private fun setupBiometricButton() {
        buttonBiometric.setOnClickListener {
            if (biometricHelper.isBiometricAvailable()) {
                biometricHelper.authenticate(
                    requireActivity(),
                    onSuccess = {
                        // Authentication successful, proceed to app
                        authViewModel.login()
                        dismiss()
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
        
        if (pin.length < 4) {
            pin += digit
            updatePinDots()
            
            if (pin.length == 4) {
                // Verify PIN
                if (pinManager.verifyPin(pin)) {
                    // PIN is correct, proceed to app
                    authViewModel.login()
                    dismiss()
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
    
    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
    
    // Make dialog non-cancelable (user must authenticate)
    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(false)
    }
} 