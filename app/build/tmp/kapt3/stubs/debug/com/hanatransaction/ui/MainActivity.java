package com.hanatransaction.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hanatransaction.R;
import com.hanatransaction.databinding.ActivityMainBinding;
import com.hanatransaction.security.AuthManager;
import com.hanatransaction.security.PinManager;
import com.hanatransaction.ui.auth.AuthViewModel;
import com.hanatransaction.ui.auth.LoginActivity;
import com.hanatransaction.ui.auth.PinFragmentDialog;
import com.hanatransaction.worker.WorkManagerUtil;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0014J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/hanatransaction/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/hanatransaction/security/AuthManager;", "authViewModel", "Lcom/hanatransaction/ui/auth/AuthViewModel;", "binding", "Lcom/hanatransaction/databinding/ActivityMainBinding;", "navController", "Landroidx/navigation/NavController;", "pinManager", "Lcom/hanatransaction/security/PinManager;", "wasInBackground", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onSupportNavigateUp", "startLoginActivity", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hanatransaction.databinding.ActivityMainBinding binding;
    private androidx.navigation.NavController navController;
    private com.hanatransaction.security.PinManager pinManager;
    private com.hanatransaction.security.AuthManager authManager;
    private com.hanatransaction.ui.auth.AuthViewModel authViewModel;
    private boolean wasInBackground = false;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    private final void startLoginActivity() {
    }
}