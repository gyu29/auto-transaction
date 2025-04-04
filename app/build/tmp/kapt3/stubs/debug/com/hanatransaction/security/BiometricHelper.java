package com.hanatransaction.security;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.hanatransaction.R;
import java.util.concurrent.Executor;

/**
 * Helper class for biometric authentication
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0084\u0001\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u001428\b\u0002\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0017\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u001b\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\f0\u00162\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u0014J\u0006\u0010\u001e\u001a\u00020\fJ\u0006\u0010\u001f\u001a\u00020\nR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/hanatransaction/security/BiometricHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "biometricPrompt", "Landroidx/biometric/BiometricPrompt;", "executor", "Ljava/util/concurrent/Executor;", "isAuthenticating", "", "authenticate", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "title", "", "subtitle", "description", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "errorCode", "", "errString", "onFailed", "cancelAuthentication", "isBiometricAvailable", "app_debug"})
public final class BiometricHelper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private java.util.concurrent.Executor executor;
    @org.jetbrains.annotations.Nullable()
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private boolean isAuthenticating = false;
    
    public BiometricHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Check if biometric authentication is available on the device
     */
    public final boolean isBiometricAvailable() {
        return false;
    }
    
    /**
     * Authenticate with biometrics
     * @param activity The activity
     * @param title Title for the biometric prompt
     * @param subtitle Subtitle for the biometric prompt
     * @param description Description for the biometric prompt
     * @param onSuccess Callback for successful authentication
     * @param onError Callback for authentication error
     * @param onFailed Callback for authentication failure
     */
    public final void authenticate(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.CharSequence, kotlin.Unit> onError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFailed) {
    }
    
    /**
     * Cancel ongoing authentication if any
     */
    public final void cancelAuthentication() {
    }
}