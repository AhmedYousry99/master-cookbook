package com.senseicoder.mastercookbook.model.repositories;

import com.senseicoder.mastercookbook.util.callbacks.LoginAuthCallback;
import com.senseicoder.mastercookbook.firebase.AuthRemoteDataSource;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.SignupAuthCallback;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private static AuthenticationRepositoryImpl instance = null;
    private static UserDTO currentUser = null;
    private AuthRemoteDataSource authRemoteDataSource;

    private AuthenticationRepositoryImpl(AuthRemoteDataSource authRemoteDataSource) {
        this.authRemoteDataSource = authRemoteDataSource;
    }

    public static AuthenticationRepositoryImpl getInstance(AuthRemoteDataSource authRemoteDataSource) {
        if(instance == null)
            instance = new AuthenticationRepositoryImpl(authRemoteDataSource);
        return instance;
    }

    public static void setCurrentUser(UserDTO currentUser) {
        AuthenticationRepositoryImpl.currentUser = currentUser;
    }

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    @Override
    public void signup(UserDTO userDTO, SignupAuthCallback firebaseAuthCallback) {
        authRemoteDataSource.signup(userDTO, firebaseAuthCallback);
    }

    @Override
    public void loginUsingNormalEmail(String email, String password, LoginAuthCallback firebaseAuthCallback) {
        authRemoteDataSource.loginUsingNormalEmail(email, password, firebaseAuthCallback);
    }

    @Override
    public void loginUsingGoogleEmail(String idToken, LoginAuthCallback firebaseAuthCallback) {
        authRemoteDataSource.loginUsingGoogleEmail(idToken, firebaseAuthCallback);
    }

    @Override
    public void loginUsingFacebookEmail(LoginAuthCallback firebaseAuthCallback) {
        authRemoteDataSource.loginUsingFacebookEmail(firebaseAuthCallback);
    }

    @Override
    public void loginUsingGuest(LoginAuthCallback firebaseAuthCallback) {
        authRemoteDataSource.loginUsingGuest(firebaseAuthCallback);
    }

}
