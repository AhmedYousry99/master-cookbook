package com.senseicoder.mastercookbook.model.repositories;

import com.senseicoder.mastercookbook.util.callbacks.LoginAuthCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.SignupAuthCallback;

public interface AuthenticationRepository {

    public void signup(UserDTO userDTO, SignupAuthCallback signupAuthCallback);

    public void loginUsingNormalEmail(String email, String password, LoginAuthCallback loginAuthCallback);

    public void loginUsingGoogleEmail(String idToken, LoginAuthCallback loginAuthCallback);

    public void loginUsingFacebookEmail(LoginAuthCallback loginAuthCallback);

    public void loginUsingGuest(LoginAuthCallback loginAuthCallback);
}
