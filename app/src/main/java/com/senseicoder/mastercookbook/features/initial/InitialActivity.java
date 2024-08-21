package com.senseicoder.mastercookbook.features.initial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.firebase.FirebaseAuthRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.main.MainActivity;
import com.senseicoder.mastercookbook.features.initial.presenter.InitialActivityPresenter;
import com.senseicoder.mastercookbook.features.initial.presenter.InitialActivityPresenterImpl;
import com.senseicoder.mastercookbook.features.initial.view.InitialView;
import com.senseicoder.mastercookbook.features.login.LoginActivity;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepositoryImpl;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.signup.SignupActivity;
import com.senseicoder.mastercookbook.util.dialogs.CircularProgressIndicatorDialog;
import com.senseicoder.mastercookbook.util.dialogs.ConfirmationDialog;

public class InitialActivity extends AppCompatActivity implements InitialView {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "InitialActivity";
    Toolbar toolbar;
    InitialActivityPresenter presenter;
    TextView loginTextView;
    TextView skipTextView;
    Button signupWithEmailButton;
    ImageButton loginWithGoogleButton;
    ImageButton loginWithFacebookButton;
    ConfirmationDialog loginUsingGuestConfirmationDialog;
    CircularProgressIndicatorDialog circularProgressIndicatorDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_initial);

        toolbar = findViewById(R.id.initialToolbar);
        loginTextView = findViewById(R.id.emailLoginText);
        skipTextView = findViewById(R.id.skipButton);
        signupWithEmailButton = findViewById(R.id.emailSignupButton);
        loginWithGoogleButton = findViewById(R.id.googleLoginButton);
        loginWithFacebookButton = findViewById(R.id.facebookLoginButton);

        presenter = new InitialActivityPresenterImpl(
                AuthenticationRepositoryImpl.getInstance(
                        FirebaseAuthRemoteDataSourceImpl.getInstance()
                ),
                DataRepositoryImpl.getInstance(
                        FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                        RetrofitRemoteDataSourceImpl.getInstance(getCacheDir())
                ),
                this
        );
        loginUsingGuestConfirmationDialog = new ConfirmationDialog(presenter::loginUsingGuest, null, this);
        circularProgressIndicatorDialog = new CircularProgressIndicatorDialog(this);
        applyInsetsOnToolbar(toolbar);

        setupListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: result code -> " + requestCode);

        if(requestCode == RC_SIGN_IN){
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                if (account != null) {
                    presenter.loginUsingGoogleEmail(account.getIdToken());
                }
            } catch (ApiException e) {
                Toast.makeText(this, "couldn't get the mail" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void handleRequestUserGoogleAccount(GoogleSignInClient googleSignInClient) {
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    public GoogleSignInClient getClient(GoogleSignInOptions gOptions) {
        return GoogleSignIn.getClient(this, gOptions);
    }

    @Override
    public void handleOnLoginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void handleOnLoginFailure(String message) {
        Toast.makeText(this, "Google Login Failure: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog() {
        circularProgressIndicatorDialog.startProgressBar();
    }

    @Override
    public void hideLoadingDialog() {
        circularProgressIndicatorDialog.dismissProgressBar();
    }

    @Override
    public void handleLoginAsGuest() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void applyInsetsOnToolbar(Toolbar toolbar){
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

    private void setupListeners(){
        loginTextView.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        skipTextView.setOnClickListener(v -> showSkipDialog());
        signupWithEmailButton.setOnClickListener(v -> startActivity(new Intent(this, SignupActivity.class)));
        loginWithGoogleButton.setOnClickListener(v -> presenter.requestUserGoogleAccount());
        loginWithFacebookButton.setOnClickListener(v -> {});
    }

    private void showSkipDialog() {
        loginUsingGuestConfirmationDialog.setMessage(getString(R.string.skip_dialog_message));
        loginUsingGuestConfirmationDialog.showDialog();
    }


}