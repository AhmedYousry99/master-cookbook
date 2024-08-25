package com.senseicoder.mastercookbook.features.initial.view;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface InitialView {

    void handleRequestUserGoogleAccount(GoogleSignInClient googleSignInClient);

    GoogleSignInClient getClient(GoogleSignInOptions gOptions);

    void handleOnLoginSuccess();

    void handleOnLoginFailure(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void handleLoginAsGuest();
}
