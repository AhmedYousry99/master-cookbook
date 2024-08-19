package com.senseicoder.mastercookbook.firebase;

import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.LoginAuthCallback;
import com.senseicoder.mastercookbook.util.callbacks.SignupAuthCallback;

public interface AuthRemoteDataSource {

    public void signup(UserDTO userDTO, SignupAuthCallback firebaseAuthCallback);

    public void loginUsingNormalEmail(String email, String password, LoginAuthCallback firebaseAuthCallback);

    public void loginUsingGoogleEmail(String idToken, LoginAuthCallback firebaseAuthCallback);

    public void loginUsingFacebookEmail(LoginAuthCallback firebaseAuthCallback);

    public void loginUsingGuest(LoginAuthCallback firebaseAuthCallback);

    public void signOut();

    public boolean isUserLoggedIn();

}
