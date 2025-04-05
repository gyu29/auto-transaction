package com.hanatransaction.ui.settings;

import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.hanatransaction.HanaTransactionApp;
import com.hanatransaction.data.repository.UserRepository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0005J\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0005J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0005J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0005J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000eR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/hanatransaction/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_darkMode", "Landroidx/lifecycle/MutableLiveData;", "", "_paymentReminders", "_settingsSaved", "_transactionAlerts", "_useBiometric", "_useSystemTheme", "darkMode", "Landroidx/lifecycle/LiveData;", "getDarkMode", "()Landroidx/lifecycle/LiveData;", "paymentReminders", "getPaymentReminders", "settingsSaved", "getSettingsSaved", "sharedPreferences", "Landroid/content/SharedPreferences;", "transactionAlerts", "getTransactionAlerts", "useBiometric", "getUseBiometric", "useSystemTheme", "getUseSystemTheme", "userRepository", "Lcom/hanatransaction/data/repository/UserRepository;", "loadSettings", "", "resetSettingsSavedFlag", "saveSettings", "setDarkMode", "value", "setPaymentReminders", "setTransactionAlerts", "setUseBiometric", "setUseSystemTheme", "Companion", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hanatransaction.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _useBiometric = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> useBiometric = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _darkMode = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> darkMode = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _useSystemTheme = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> useSystemTheme = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _transactionAlerts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> transactionAlerts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _paymentReminders = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> paymentReminders = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _settingsSaved = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> settingsSaved = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USE_BIOMETRIC = "use_biometric";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DARK_MODE = "dark_mode";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USE_SYSTEM_THEME = "use_system_theme";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TRANSACTION_ALERTS = "transaction_alerts";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PAYMENT_REMINDERS = "payment_reminders";
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.ui.settings.SettingsViewModel.Companion Companion = null;
    
    public SettingsViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getUseBiometric() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getDarkMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getUseSystemTheme() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getTransactionAlerts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getPaymentReminders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getSettingsSaved() {
        return null;
    }
    
    private final void loadSettings() {
    }
    
    public final void saveSettings() {
    }
    
    public final void resetSettingsSavedFlag() {
    }
    
    public final void setUseBiometric(boolean value) {
    }
    
    public final void setDarkMode(boolean value) {
    }
    
    public final void setUseSystemTheme(boolean value) {
    }
    
    public final void setTransactionAlerts(boolean value) {
    }
    
    public final void setPaymentReminders(boolean value) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/hanatransaction/ui/settings/SettingsViewModel$Companion;", "", "()V", "KEY_DARK_MODE", "", "KEY_PAYMENT_REMINDERS", "KEY_TRANSACTION_ALERTS", "KEY_USE_BIOMETRIC", "KEY_USE_SYSTEM_THEME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}