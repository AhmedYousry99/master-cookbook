package com.senseicoder.mastercookbook.signup.presenter;

import android.util.Log;
import android.util.Patterns;

import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepository;
import com.senseicoder.mastercookbook.signup.view.SignupView;
import com.senseicoder.mastercookbook.util.callbacks.SignupAuthCallback;

public class SignupPresenterImpl implements SignupPresenter, SignupAuthCallback {

    private static final String TAG = "SignupPresenterImpl";
    private SignupView signupView;

    private boolean emailIsValid;
    private boolean passwordIsValid;
    private boolean displayNameIsValid;
    private boolean confirmPasswordIsValid;
    private final AuthenticationRepository authenticationRepository;
    private final DataRepository dataRepository;

    public SignupPresenterImpl(SignupView signupView, AuthenticationRepository authenticationRepository, DataRepository dataRepository) {
        this.signupView = signupView;
        this.authenticationRepository = authenticationRepository;
        this.dataRepository = dataRepository;
        emailIsValid = false;
        passwordIsValid = false;
        displayNameIsValid = false;
        confirmPasswordIsValid = false;
    }

    @Override
    public void validateDisplayName(String displayName) {
        displayNameIsValid = isValidDisplayName(displayName);
        Log.i(TAG, "validateDisplayName: " + displayNameIsValid);
        signupView.handleDisplayNameValidation(displayNameIsValid);
    }

    @Override
    public void validateEmail(String email) {
        emailIsValid = isValidEmail(email);
        Log.i(TAG, "validateEmail: " + emailIsValid);
        signupView.handleEmailValidation(emailIsValid);
    }

    @Override
    public void validatePassword(String password) {
        passwordIsValid = isValidPassword(password);
        Log.i(TAG, "validatePassword: " + passwordIsValid);

        signupView.handlePasswordValidation(passwordIsValid);
    }

    @Override
    public void validateConfirmPassword(String password, String confirmPassword) {
        confirmPasswordIsValid = isValidConfirmPassword(password, confirmPassword);
        Log.i(TAG, "validateConfirmPassword: " + confirmPasswordIsValid);
        signupView.handleConfirmPasswordValidation(confirmPasswordIsValid);
    }

    @Override
    public void signup(UserDTO userDTO) {
        if (areFieldsValid()) {
            signupView.showLoadingDialog();
            authenticationRepository.signup(userDTO, this);
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }

    private boolean isValidDisplayName(String displayName) {
        return displayName != null && displayName.trim().length() > 1;
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() > 5;
    }

    private boolean isValidConfirmPassword(String password, String confirmPassword) {
        return confirmPassword != null && confirmPassword.equals(password);
    }

    private boolean areFieldsValid() {
        boolean areFieldsValid = true;
        signupView.hideValidationErrors();
        if (!emailIsValid) {
            signupView.handleEmailValidation(false);
            areFieldsValid = false;
        }
        if (!passwordIsValid) {
            signupView.handlePasswordValidation(false);
            areFieldsValid = false;
        }
        if (!confirmPasswordIsValid) {
            signupView.handleConfirmPasswordValidation(false);
            areFieldsValid = false;
        }
        if (!displayNameIsValid) {
            signupView.handleDisplayNameValidation(false);
            areFieldsValid = false;
        }
        Log.i(TAG, "areFieldsValid: " + areFieldsValid);
        return areFieldsValid;
    }

    @Override
    public void onSignupAuthSuccess(UserDTO userDTO) {
        dataRepository.getUserByIdOrAddUser(userDTO, new GetUserByIdOrAddUserCallback() {
            @Override
            public void onGetUserByIdOrAddUserCallbackSuccess(UserDTO userDTO) {
                dataRepository.setCurrentUser(userDTO);
                signupView.hideLoadingDialog();
                signupView.handleSignupSuccess();
            }

            @Override
            public void onGetUserByIdOrAddUserCallbackailure(String message) {
                signupView.hideLoadingDialog();
                signupView.handleSignupFailure(message);
            }
        });
    }

    @Override
    public void onSignupAuthFailure(String message) {
        signupView.hideLoadingDialog();
        signupView.handleSignupFailure(message);
    }
}
