package com.android.kevng2.freestuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        //Code for the floating action button
        FloatingActionButton  fabAddListing = findViewById(R.id.fabAddListing);
        fabAddListing.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"FAB Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}