package com.senseicoder.mastercookbook.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.senseicoder.mastercookbook.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        toolbar = findViewById(R.id.mainToolBar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.HomeFragment, R.id.SearchFragment, R.id.FavoriteFragment, R.id.PlanFragment, R.id.ProfileFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        applyInsetsOnView(findViewById(R.id.mainContainer));
    }

    private void applyInsetsOnView(View view){
        ViewCompat.setOnApplyWindowInsetsListener(toolbar, new androidx.core.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
                int statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
                // Adjust the top margin of the toolbar
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
                params.topMargin = statusBarHeight;
                toolbar.setLayoutParams(params);
                return insets;
            }
        });
    }

}