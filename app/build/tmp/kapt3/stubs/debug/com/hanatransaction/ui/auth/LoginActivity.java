package com.hanatransaction.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.hanatransaction.R;
import com.hanatransaction.databinding.ActivityLoginBinding;
import com.hanatransaction.security.AuthManager;
import com.hanatransaction.security.BiometricHelper;
import com.hanatransaction.security.PinManager;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\"\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0018H\u0016J\u0012\u0010 \u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u000eH\u0002J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002J\b\u0010\'\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/hanatransaction/ui/auth/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/hanatransaction/security/AuthManager;", "authenticationAttempts", "", "binding", "Lcom/hanatransaction/databinding/ActivityLoginBinding;", "biometricHelper", "Lcom/hanatransaction/security/BiometricHelper;", "buttonBiometric", "Lcom/google/android/material/button/MaterialButton;", "pin", "", "pinDots", "", "Landroid/view/View;", "pinManager", "Lcom/hanatransaction/security/PinManager;", "textViewError", "Landroid/widget/TextView;", "textViewTitle", "authenticationSuccessful", "", "handleDelete", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDigitPressed", "digit", "setupKeypad", "startSetupActivity", "updatePinDots", "Companion", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hanatransaction.databinding.ActivityLoginBinding binding;
    private com.hanatransaction.security.AuthManager authManager;
    private com.hanatransaction.security.PinManager pinManager;
    private com.hanatransaction.security.BiometricHelper biometricHelper;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String pin = "";
    private int authenticationAttempts = 0;
    private android.widget.TextView textViewTitle;
    private android.widget.TextView textViewError;
    private com.google.android.material.button.MaterialButton buttonBiometric;
    private java.util.List<? extends android.view.View> pinDots;
    private static final int SETUP_REQUEST_CODE = 1002;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.ui.auth.LoginActivity.Companion Companion = null;
    
    public LoginActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Start the SetupActivity for new users
     */
    private final void startSetupActivity() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void setupKeypad() {
    }
    
    private final void handleDelete() {
    }
    
    private final void onDigitPressed(java.lang.String digit) {
    }
    
    private final void updatePinDots() {
    }
    
    private final void authenticationSuccessful() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hanatransaction/ui/auth/LoginActivity$Companion;", "", "()V", "SETUP_REQUEST_CODE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}