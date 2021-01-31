package com.android.kevng2.freestuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private NavHostFragment mNavHostFragment;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());

        // signInAccount.getDisplayName() signInAccount.getEmail()
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (mNavHostFragment != null)
            mNavController =  mNavHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBottomNavigationView, mNavController);

        MyFirebaseMessagingService service = new MyFirebaseMessagingService();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Timber.w(task.getException(), "Fetching FCM registration token failed");
                return;
            }

            // Get new FCM registration token
            String token = task.getResult();

            // Log and toast
            Timber.d(token);
            Toast.makeText(MainActivity.this, "yep", Toast.LENGTH_SHORT).show();
        });
    }
}