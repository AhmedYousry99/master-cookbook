package com.senseicoder.mastercookbook.features.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.util.global.UiUtils;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navView;
    NavController navController;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        toolbar = findViewById(R.id.mainToolBar);
        navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.HomeFragment, R.id.SearchFragment, R.id.FavoriteFragment, R.id.PlanFragment, R.id.ProfileFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> setupNavigationListener(navDestination));

        UiUtils.applyAppBarInsetsOnView(findViewById(R.id.mainContainer));
    }

    private void setupNavigationListener(NavDestination navDestination) {
        Log.d(TAG, "setupNavigationListener: " + navDestination.getLabel());
        if (navDestination.getLabel().equals("Home")
                || navDestination.getLabel().equals("Search")
                || navDestination.getLabel().equals("Favorite")
                || navDestination.getLabel().equals("Profile")
                || navDestination.getLabel().equals("Plan")) {
            navView.setVisibility(View.VISIBLE);
        }else
            new Handler(Looper.getMainLooper()).postDelayed(() -> navView.setVisibility(View.GONE),20);

    }
}