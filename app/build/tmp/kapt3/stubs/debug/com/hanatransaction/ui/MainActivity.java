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
import com.hanatransaction.worker.WorkManagerUtil;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\"\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0011H\u0014J\b\u0010\u001c\u001a\u00020\u0011H\u0014J\b\u0010\u001d\u001a\u00020\nH\u0016J\b\u0010\u001e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/hanatransaction/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/hanatransaction/security/AuthManager;", "authViewModel", "Lcom/hanatransaction/ui/auth/AuthViewModel;", "binding", "Lcom/hanatransaction/databinding/ActivityMainBinding;", "firstLaunch", "", "navController", "Landroidx/navigation/NavController;", "pinManager", "Lcom/hanatransaction/security/PinManager;", "wasInBackground", "checkAuthentication", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onSupportNavigateUp", "startLoginActivityForResult", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hanatransaction.databinding.ActivityMainBinding binding;
    private androidx.navigation.NavController navController;
    private com.hanatransaction.security.PinManager pinManager;
    private com.hanatransaction.security.AuthManager authManager;
    private com.hanatransaction.ui.auth.AuthViewModel authViewModel;
    private boolean firstLaunch = true;
    private boolean wasInBackground = false;
    private static final int LOGIN_REQUEST_CODE = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.hanatransaction.ui.MainActivity.Companion Companion = null;
    
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
    
    private final void checkAuthentication() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    private final void startLoginActivityForResult() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hanatransaction/ui/MainActivity$Companion;", "", "()V", "LOGIN_REQUEST_CODE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}