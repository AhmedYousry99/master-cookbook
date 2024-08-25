package com.senseicoder.mastercookbook.features.initial.presenter;

import static com.senseicoder.mastercookbook.authentication.FirebaseAuthRemoteDataSourceImpl.firebaseGoogleWebId;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.util.callbacks.LoginAuthCallback;
import com.senseicoder.mastercookbook.features.initial.view.InitialView;
import com.senseicoder.mastercookbook.model.repositories.AuthenticationRepository;

public class InitialActivityPresenterImpl implements InitialActivityPresenter, LoginAuthCallback {

    private final AuthenticationRepository authenticationRepository;
    private final DataRepository dataRepository;
    private InitialView initialView;


    public InitialActivityPresenterImpl(AuthenticationRepository authenticationRepository, DataRepository dataRepository, InitialView initialView) {
        this.authenticationRepository = authenticationRepository;
        this.dataRepository = dataRepository;
        this.initialView = initialView;
    }

    @Override
    public void loginUsingGoogleEmail(String tokenId) {
        initialView.showLoadingDialog();
        authenticationRepository.loginUsingGoogleEmail(tokenId, this);
    }

    @Override
    public void requestUserGoogleAccount() {
        GoogleSignInOptions gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(firebaseGoogleWebId)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = initialView.getClient(gOptions);
        initialView.handleRequestUserGoogleAccount(googleSignInClient);
    }

    @Override
    public void loginUsingFacebookEmail() {

    }

    @Override
    public void loginUsingGuest() {
        authenticationRepository.loginUsingGuest(this);
    }

    @Override
    public void onLoginAuthSuccess(UserDTO userDTO) {
        if(userDTO != null)
        {
            dataRepository.getUserByIdOrAddUser(userDTO, new GetUserByIdOrAddUserCallback() {
                @Override
                public void onGetUserByIdOrAddUserCallbackSuccess(UserDTO userDTO) {
                    dataRepository.setCurrentUser(userDTO);
                    initialView.hideLoadingDialog();
                    initialView.handleOnLoginSuccess();
                }

                @Override
                public void onGetUserByIdOrAddUserCallbackailure(String message) {
                    initialView.hideLoadingDialog();
                    initialView.handleOnLoginFailure(message);
                }
            });
        }else {
            dataRepository.setCurrentUser(new UserDTO());
            initialView.hideLoadingDialog();
            initialView.handleOnLoginSuccess();
        }
    }

    @Override
    public void onLoginAuthFailure(String message) {
        initialView.hideLoadingDialog();
        initialView.handleOnLoginFailure(message);
    }
}
