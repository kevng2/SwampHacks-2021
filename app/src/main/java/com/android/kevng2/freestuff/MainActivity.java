package com.android.kevng2.freestuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private NavHostFragment mNavHostFragment;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (mNavHostFragment != null)
            mNavController =  mNavHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBottomNavigationView, mNavController);
    }
}