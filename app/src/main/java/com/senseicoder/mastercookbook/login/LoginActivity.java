package com.senseicoder.mastercookbook.login;

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
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.firebase.FirebaseAuthRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.login.presenter.LoginPresenter;
import com.senseicoder.mastercookbook.login.presenter.LoginPresenterImpl;
import com.senseicoder.mastercookbook.login.view.LoginView;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepositoryImpl;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.dialogs.CircularProgressIndicatorDialog;

public class LoginActivity extends AppCompatActivity implements LoginView {

    Toolbar toolbar;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    Button loginButton;
    CircularProgressIndicatorDialog circularProgressIndicatorDialog;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.login_toolbar);
        emailTextInputEditText = findViewById(R.id.emailLoginEditText);
        passwordTextInputEditText = findViewById(R.id.passwordLoginEditText);
        loginButton = findViewById(R.id.loginButton);

        applyInsetsOnToolbar(toolbar);
        circularProgressIndicatorDialog = new CircularProgressIndicatorDialog(this);

        presenter = new LoginPresenterImpl(this, AuthenticationRepositoryImpl.getInstance(
                FirebaseAuthRemoteDataSourceImpl.getInstance()
        ),
                DataRepositoryImpl.getInstance(
                        FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                        RetrofitRemoteDataSourceImpl.getInstance(getCacheDir())
                ));
        setUpListeners();
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
    public void handleLoginSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleLoginFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideValidationErrors() {
        emailTextInputEditText.setError(null);
        passwordTextInputEditText.setError(null);
    }

    @Override
    public void showLoadingDialog() {
        circularProgressIndicatorDialog.startProgressBar();
    }

    @Override
    public void hideLoadingDialog() {
        circularProgressIndicatorDialog.dismissProgressBar();
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

    private void setUpListeners(){
        toolbar.setNavigationOnClickListener(v -> finish());
        emailTextInputEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                presenter.validateEmail(emailTextInputEditText.getText().toString());
            }
        });
        passwordTextInputEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                presenter.validatePassword(passwordTextInputEditText.getText().toString());
            }
        });
        loginButton.setOnClickListener(v -> presenter.loginEmail(
                emailTextInputEditText.getText().toString(),
                passwordTextInputEditText.getText().toString()
        ));
    }
}