package com.hanatransaction.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.hanatransaction.R;
import com.hanatransaction.security.BiometricHelper;
import com.hanatransaction.security.PinManager;
import com.hanatransaction.ui.MainActivity;

/**
 * Fragment for PIN entry, creation, and verification
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002J&\u0010\u001d\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0019H\u0002J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002J\u001a\u0010&\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020\u00112\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010(\u001a\u00020\u0019H\u0002J\b\u0010)\u001a\u00020\u0019H\u0002J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020\u0011H\u0002J\b\u0010+\u001a\u00020\u0019H\u0002J\b\u0010,\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/hanatransaction/ui/auth/PinFragment;", "Landroidx/fragment/app/Fragment;", "()V", "biometricHelper", "Lcom/hanatransaction/security/BiometricHelper;", "buttonBiometric", "Lcom/google/android/material/button/MaterialButton;", "confirmPin", "", "currentPin", "isPinConfirmation", "", "mode", "", "pin", "pinDots", "", "Landroid/view/View;", "pinManager", "Lcom/hanatransaction/security/PinManager;", "textViewError", "Landroid/widget/TextView;", "textViewSubtitle", "textViewTitle", "handleChangeModeInput", "", "digit", "handleCreateModeInput", "handleVerifyModeInput", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDeletePressed", "onDigitPressed", "onViewCreated", "view", "proceedToApp", "setupBiometricButton", "setupKeypad", "updatePinDots", "updateUI", "Companion", "app_debug"})
public final class PinFragment extends androidx.fragment.app.Fragment {
    public static final int MODE_CREATE = 0;
    public static final int MODE_VERIFY = 1;
    public static final int MODE_CHANGE = 2;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_MODE = "arg_mode";
    private com.hanatransaction.security.PinManager pinManager;
    private com.hanatransaction.security.BiometricHelper biometricHelper;
    private int mode = 1;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String pin = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String confirmPin = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentPin = "";
    private boolean isPinConfirmation = false;
    private android.widget.TextView textViewTitle;
    private android.widget.TextView textViewSubtitle;
    private android.widget.TextView textViewError;
    private com.google.android.material.button.MaterialButton buttonBiometric;
    private java.util.List<? extends android.view.View> pinDots;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.ui.auth.PinFragment.Companion Companion = null;
    
    public PinFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateUI() {
    }
    
    private final void updatePinDots() {
    }
    
    private final void setupKeypad(android.view.View view) {
    }
    
    private final void setupBiometricButton() {
    }
    
    private final void onDigitPressed(java.lang.String digit) {
    }
    
    private final void handleCreateModeInput(java.lang.String digit) {
    }
    
    private final void handleVerifyModeInput(java.lang.String digit) {
    }
    
    private final void handleChangeModeInput(java.lang.String digit) {
    }
    
    private final void onDeletePressed() {
    }
    
    private final void proceedToApp() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/hanatransaction/ui/auth/PinFragment$Companion;", "", "()V", "ARG_MODE", "", "MODE_CHANGE", "", "MODE_CREATE", "MODE_VERIFY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}