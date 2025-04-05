package com.hanatransaction.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.hanatransaction.R;
import com.hanatransaction.databinding.ActivitySetupBinding;
import com.hanatransaction.security.AuthManager;
import com.hanatransaction.security.BiometricHelper;
import com.hanatransaction.security.PinManager;
import com.hanatransaction.ui.MainActivity;

/**
 * Activity for first-time setup of the app
 * This guides the user through:
 * 1. Hana Bank credentials input
 * 2. PIN creation
 * 3. Biometric setup (optional)
 * 4. Other preferences
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0012\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u001e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\n2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\b\u0010#\u001a\u00020\u0019H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002J\b\u0010&\u001a\u00020\u0019H\u0002J\b\u0010\'\u001a\u00020\u0019H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002J\u0016\u0010)\u001a\u00020\u00192\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\b\u0010*\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006,"}, d2 = {"Lcom/hanatransaction/ui/auth/SetupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/hanatransaction/security/AuthManager;", "binding", "Lcom/hanatransaction/databinding/ActivitySetupBinding;", "biometricHelper", "Lcom/hanatransaction/security/BiometricHelper;", "confirmPin", "", "currentStep", "", "pin", "pinManager", "Lcom/hanatransaction/security/PinManager;", "viewModel", "Lcom/hanatransaction/ui/auth/SetupViewModel;", "getViewModel", "()Lcom/hanatransaction/ui/auth/SetupViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "createTextWatcher", "Landroid/text/TextWatcher;", "moveToNextStep", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDigitPressed", "digit", "pinDots", "", "Landroid/view/View;", "setupBiometricStep", "setupCompleteStep", "setupCredentialsStep", "setupKeypad", "setupObservers", "setupPinStep", "updatePinDots", "updateStepIndicator", "Companion", "app_debug"})
public final class SetupActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hanatransaction.databinding.ActivitySetupBinding binding;
    private com.hanatransaction.security.AuthManager authManager;
    private com.hanatransaction.security.PinManager pinManager;
    private com.hanatransaction.security.BiometricHelper biometricHelper;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private int currentStep = 1;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String pin = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String confirmPin = "";
    public static final int STEP_CREDENTIALS = 1;
    public static final int STEP_PIN = 2;
    public static final int STEP_BIOMETRIC = 3;
    public static final int STEP_COMPLETE = 4;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.ui.auth.SetupActivity.Companion Companion = null;
    
    public SetupActivity() {
        super();
    }
    
    private final com.hanatransaction.ui.auth.SetupViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupCredentialsStep() {
    }
    
    private final void setupPinStep() {
    }
    
    private final void setupBiometricStep() {
    }
    
    private final void setupCompleteStep() {
    }
    
    private final void setupKeypad() {
    }
    
    private final void onDigitPressed(java.lang.String digit, java.util.List<? extends android.view.View> pinDots) {
    }
    
    private final void updatePinDots(java.util.List<? extends android.view.View> pinDots) {
    }
    
    private final android.text.TextWatcher createTextWatcher() {
        return null;
    }
    
    private final void moveToNextStep() {
    }
    
    private final void updateStepIndicator() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/hanatransaction/ui/auth/SetupActivity$Companion;", "", "()V", "STEP_BIOMETRIC", "", "STEP_COMPLETE", "STEP_CREDENTIALS", "STEP_PIN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}