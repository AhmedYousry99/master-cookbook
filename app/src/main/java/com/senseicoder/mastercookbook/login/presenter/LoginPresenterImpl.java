package com.senseicoder.mastercookbook.login.presenter;

import android.util.Log;
import android.util.Patterns;

import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.util.callbacks.LoginAuthCallback;
import com.senseicoder.mastercookbook.login.view.LoginView;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepository;

public class LoginPresenterImpl implements LoginPresenter, LoginAuthCallback {

    private static final String TAG = "LoginPresenterImpl";
    private LoginView loginView;
    private boolean emailIsValid;
    private boolean passwordIsValid;
    private final AuthenticationRepository authenticationRepository;
    private final DataRepository dataRepository;

    public LoginPresenterImpl(LoginView loginView, AuthenticationRepository authenticationRepository, DataRepository dataRepository) {
        this.loginView = loginView;
        this.authenticationRepository = authenticationRepository;
        this.dataRepository = dataRepository;
        emailIsValid = false;
        passwordIsValid = false;
    }

    @Override
    public void validateEmail(String email) {
        emailIsValid = isValidEmail(email);
        Log.i(TAG, "validateEmail: " + emailIsValid);
        loginView.handleEmailValidation(emailIsValid);
    }

    @Override
    public void validatePassword(String password) {
        passwordIsValid = isValidPassword(password);
        Log.i(TAG, "validatePassword: " + passwordIsValid);
        loginView.handlePasswordValidation(passwordIsValid);
    }

    @Override
    public void loginEmail(String email, String password) {
        if (areFieldsValid()) {
            loginView.showLoadingDialog();
            authenticationRepository.loginUsingNormalEmail(
                    email,
                    password,
                    this);
        }
    }

    @Override
    public void onLoginAuthSuccess(UserDTO userDTO) {
        dataRepository.getUserByIdOrAddUser(userDTO, new GetUserByIdOrAddUserCallback() {
            @Override
            public void onGetUserByIdOrAddUserCallbackSuccess(UserDTO userDTO) {
                dataRepository.setCurrentUser(userDTO);
                loginView.hideLoadingDialog();
                loginView.handleLoginSuccess();
            }

            @Override
            public void onGetUserByIdOrAddUserCallbackailure(String message) {
                loginView.hideLoadingDialog();
                loginView.handleLoginFailure(message);
            }
        });

    }

    @Override
    public void onLoginAuthFailure(String message) {
        loginView.hideLoadingDialog();
        loginView.handleLoginFailure(message);
    }


    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() > 5;
    }

    private boolean areFieldsValid() {
        boolean areFieldsValid = true;
        loginView.hideValidationErrors();
        if (!emailIsValid) {
            loginView.handleEmailValidation(false);
            areFieldsValid = false;
        }
        if (!passwordIsValid) {
            loginView.handlePasswordValidation(false);
            areFieldsValid = false;
        }
        Log.i(TAG, "areFieldsValid: " + areFieldsValid);
        return areFieldsValid;
    }
}
