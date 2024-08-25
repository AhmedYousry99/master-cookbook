package com.senseicoder.mastercookbook.features.signup;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.signup.presenter.SignupPresenter;
import com.senseicoder.mastercookbook.features.signup.presenter.SignupPresenterImpl;
import com.senseicoder.mastercookbook.features.signup.view.SignupView;
import com.senseicoder.mastercookbook.authentication.FirebaseAuthRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepositoryImpl;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.dialogs.CircularProgressIndicatorDialog;

public class SignupActivity extends AppCompatActivity implements SignupView {

    Toolbar toolbar;
    TextInputEditText displayNameTextInputEditText;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    TextInputEditText confirmPasswordTextInputEditText;
    Button signupButton;
    SignupPresenter presenter;
    CircularProgressIndicatorDialog circularProgressIndicatorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        toolbar = findViewById(R.id.signup_toolbar);
        displayNameTextInputEditText = findViewById(R.id.displayNameSignupText);
        emailTextInputEditText = findViewById(R.id.emailSignupEditText);
        passwordTextInputEditText = findViewById(R.id.passwordSignupEditText);
        confirmPasswordTextInputEditText = findViewById(R.id.confirmPasswordSignupEditText);
        signupButton = findViewById(R.id.signupButton);

        applyInsetsOnToolbar(toolbar);
        circularProgressIndicatorDialog = new CircularProgressIndicatorDialog(this);

        presenter = new SignupPresenterImpl(this, AuthenticationRepositoryImpl.getInstance(
                FirebaseAuthRemoteDataSourceImpl.getInstance()
        ),
                DataRepositoryImpl.getInstance(
                        FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                        RetrofitRemoteDataSourceImpl.getInstance(getCacheDir()),
                        RoomLocalDateSourceImpl.getInstance(this)
                ));
        setupListeners();
    }

    @Override
    public void handleDisplayNameValidation(boolean isValid) {
        if (isValid) {
            displayNameTextInputEditText.setError(null);
        }else{
            displayNameTextInputEditText.setError(getString(R.string.display_name_error_message));
        }
    }

    @Override
    public void handleEmailValidation(boolean isValid) {
        if (isValid) {
            emailTextInputEditText.setError(null);
        }else{
            emailTextInputEditText.setError(getString(R.string.email_error_message));
        }
    }

    @Override
    public void handlePasswordValidation(boolean isValid) {
        if (isValid) {
            passwordTextInputEditText.setError(null);
        }else{
            passwordTextInputEditText.setError(getString(R.string.password_error_message));
        }
    }

    @Override
    public void handleConfirmPasswordValidation(boolean isValid) {
        if (isValid) {
            confirmPasswordTextInputEditText.setError(null);
        }else{
            confirmPasswordTextInputEditText.setError(getString(R.string.confirm_password_error_message));
        }
    }

    @Override
    public void handleSignupSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSignupFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideValidationErrors() {
        displayNameTextInputEditText.setError(null);
        emailTextInputEditText.setError(null);
        passwordTextInputEditText.setError(null);
        confirmPasswordTextInputEditText.setError(null);
    }

    @Override
    public void showLoadingDialog() {
        circularProgressIndicatorDialog.startProgressBar();
    }

    @Override
    public void hideLoadingDialog() {
        circularProgressIndicatorDialog.dismissProgressBar();
    }

    private void applyInsetsOnToolbar(Toolbar toolbar) {
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

    private void setupListeners() {
        toolbar.setNavigationOnClickListener(
                v -> finish()
        );
        displayNameTextInputEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                presenter.validateDisplayName(displayNameTextInputEditText.getText().toString());
            }
        });
        emailTextInputEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                presenter.validateEmail(emailTextInputEditText.getText().toString());
            }
        });
        passwordTextInputEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                presenter.validatePassword(passwordTextInputEditText.getText().toString());
                if(confirmPasswordTextInputEditText.getText().toString() != null){
                    presenter.validateConfirmPassword(passwordTextInputEditText.getText().toString(), confirmPasswordTextInputEditText.getText().toString());
                }
            }
        });
        confirmPasswordTextInputEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                presenter.validateConfirmPassword(passwordTextInputEditText.getText().toString(), confirmPasswordTextInputEditText.getText().toString());
            }
        });
        signupButton.setOnClickListener(v -> presenter.signup(
                new UserDTO(
                        displayNameTextInputEditText.getText().toString(),
                        emailTextInputEditText.getText().toString(),
                        passwordTextInputEditText.getText().toString()
                )
        ));
    }
}