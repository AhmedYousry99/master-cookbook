package com.senseicoder.mastercookbook.features.signup.view;

public interface SignupView {

    void handleDisplayNameValidation(boolean isValid);

    void handleEmailValidation(boolean isValid);

    void handlePasswordValidation(boolean isValid);

    void handleConfirmPasswordValidation(boolean isValid);

    void handleSignupSuccess();

    void handleSignupFailure(String errorMessage);

    void hideValidationErrors();

    void showLoadingDialog();

    void hideLoadingDialog();
}
