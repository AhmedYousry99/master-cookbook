package com.senseicoder.mastercookbook.features.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.initial.InitialActivity;
import com.senseicoder.mastercookbook.features.main.MainActivity;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.splash.presenter.SplashActivityPresenter;
import com.senseicoder.mastercookbook.features.splash.view.SplashActivityView;
import com.senseicoder.mastercookbook.util.dialogs.ConfirmationDialog;


public class SplashActivity extends AppCompatActivity implements SplashActivityView {


    private static final long SPLASH_DISPLAY_LENGTH = 2000;
    private static final String TAG = "SplashActivity";
    private SplashActivityPresenter splashActivityPresenter;
    private ConfirmationDialog retryConfirmationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        splashActivityPresenter = new SplashActivityPresenter(
                this, DataRepositoryImpl.getInstance(
                FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                RetrofitRemoteDataSourceImpl.getInstance(getCacheDir()),
                RoomLocalDateSourceImpl.getInstance(this)
        )
        );
        retryConfirmationDialog = new ConfirmationDialog(
                splashActivityPresenter::onRetryConfirmed,
                splashActivityPresenter::onRetryCancelled,
                this);
        setContentView(R.layout.activity_splash);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
        initializeAndStartUserStateHandler();

    }

    private void initializeAndStartUserStateHandler(){
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Log.i(TAG, "onCreate: before check");
            splashActivityPresenter.checkIfUserIsLoggedIn();
            Log.i(TAG, "onCreate: after check");
        }, SPLASH_DISPLAY_LENGTH);
    }
    @Override
    public void handleCheckCurrentUser(boolean loggedIn) {
        if(loggedIn)
            startActivity(new Intent(this, MainActivity.class));
        else
            startActivity(new Intent(this, InitialActivity.class));
        finish();
    }

    @Override
    public void showRetryDialog() {
        retryConfirmationDialog.setMessage(
                getString(R.string.splash_retry_dialog)
        );
        retryConfirmationDialog.setOnConfirmText(
                getString(R.string.splash_dialog_on_confirm)
        );
        retryConfirmationDialog.setOnCancelText(
                getString(R.string.splash_dialog_on_cancel)
        );
        retryConfirmationDialog.showDialog();
    }

}