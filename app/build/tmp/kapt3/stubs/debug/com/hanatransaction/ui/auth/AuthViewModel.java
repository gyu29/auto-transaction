package com.hanatransaction.ui.auth;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.hanatransaction.security.AuthManager;
import com.hanatransaction.security.PinManager;
import java.util.concurrent.TimeUnit;

/**
 * ViewModel responsible for authentication-related business logic
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/hanatransaction/ui/auth/AuthViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "AUTH_TIMEOUT_MINUTES", "", "authManager", "Lcom/hanatransaction/security/AuthManager;", "lastAuthTime", "pinManager", "Lcom/hanatransaction/security/PinManager;", "clearAuthTime", "", "login", "logout", "requiresAuthentication", "", "updateAuthTime", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.security.AuthManager authManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.security.PinManager pinManager = null;
    private long lastAuthTime = 0L;
    private final long AUTH_TIMEOUT_MINUTES = 10L;
    
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    /**
     * Check if the user needs to re-authenticate
     */
    public final boolean requiresAuthentication() {
        return false;
    }
    
    /**
     * Update the last authentication time
     */
    public final void updateAuthTime() {
    }
    
    /**
     * Clear the last authentication time to force re-authentication
     */
    public final void clearAuthTime() {
    }
    
    /**
     * Log the user in
     */
    public final void login() {
    }
    
    /**
     * Log the user out
     */
    public final void logout() {
    }
}