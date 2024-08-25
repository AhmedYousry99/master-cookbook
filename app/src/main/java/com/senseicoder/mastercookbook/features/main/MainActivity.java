package com.senseicoder.mastercookbook.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.authentication.FirebaseAuthRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.initial.InitialActivity;
import com.senseicoder.mastercookbook.features.main.presenter.MainPresenter;
import com.senseicoder.mastercookbook.features.main.presenter.MainPresenterImpl;
import com.senseicoder.mastercookbook.features.main.view.MainView;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepositoryImpl;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.global.UiUtils;

public class MainActivity extends AppCompatActivity implements MainView {

    Toolbar toolbar;
    BottomNavigationView navView;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    private static final String TAG = "MainActivity";
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        toolbar = findViewById(R.id.mainToolBar);
        navView = findViewById(R.id.nav_view);

        UiUtils.applyAppBarInsetsOnView(findViewById(R.id.mainContainer));
        presenter = new MainPresenterImpl(
                DataRepositoryImpl.getInstance(
                        FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                        RetrofitRemoteDataSourceImpl.getInstance(getCacheDir()),
                        RoomLocalDateSourceImpl.getInstance(this)
                ),
                AuthenticationRepositoryImpl.getInstance(
                        FirebaseAuthRemoteDataSourceImpl.getInstance()
                ),
                this
        );

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_home);
        toolbar.showOverflowMenu();
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.HomeFragment, R.id.SearchFragment, R.id.FavoriteFragment, R.id.PlanFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if (!canNavigate(navDestination.getId())){
                navController1.popBackStack();
                Log.d(TAG, "onCreate: " + FirebaseFirestoreRemoteDataSourceImpl.getInstance().getCurrentUser());
                Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
            }
                setupNavigationListener(navDestination);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setupNavigationListener(NavDestination navDestination) {
        Log.d(TAG, "setupNavigationListener: " + navDestination.getLabel());
        if (navDestination.getLabel().equals("Home")
                || navDestination.getLabel().equals("Search")
                || navDestination.getLabel().equals("Favorite")
                || navDestination.getLabel().equals("Profile")
                || navDestination.getLabel().equals("Plan")) {
            navView.setVisibility(View.VISIBLE);
        } else
            new Handler(Looper.getMainLooper()).postDelayed(() -> navView.setVisibility(View.GONE), 20);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.signOut){
            presenter.signOut();
            Intent intent = new Intent(this, InitialActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean canNavigate(int destinationId) {
        if(destinationId == R.id.FavoriteFragment || destinationId == R.id.PlanFragment){
            return !presenter.isUserGuest();
        }
        return true;
    }

}