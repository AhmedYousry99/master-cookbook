package com.senseicoder.mastercookbook.firebase;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.LoginAuthCallback;
import com.senseicoder.mastercookbook.util.callbacks.SignupAuthCallback;

public class FirebaseAuthRemoteDataSourceImpl implements AuthRemoteDataSource {

    private static final String TAG = "FirebaseRemoteDataSourceImpl";
    private static FirebaseAuthRemoteDataSourceImpl instance = null;
    private FirebaseAuth firebaseAuthInstance;
    public static  final String firebaseGoogleWebId = "130824704588-q4tn48kknoneiuhh193pk7bjefk2c6us.apps.googleusercontent.com";


    public static FirebaseAuthRemoteDataSourceImpl getInstance() {
        if (instance == null)
            instance = new FirebaseAuthRemoteDataSourceImpl();
        return instance;
    }

    private FirebaseAuthRemoteDataSourceImpl() {
        firebaseAuthInstance = FirebaseAuth.getInstance();
    }

    @Override
    public void signup(UserDTO userDTO, SignupAuthCallback signupAuthCallback) {
        firebaseAuthInstance.createUserWithEmailAndPassword(userDTO.getEmail(), userDTO.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userDTO.setId(firebaseAuthInstance.getCurrentUser().getUid());
                            signupAuthCallback.onSignupAuthSuccess(userDTO);
                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            signupAuthCallback.onSignupAuthFailure(task.getException().getMessage());
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    @Override
    public void loginUsingNormalEmail(String email, String password, LoginAuthCallback loginAuthCallback) {
        firebaseAuthInstance.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuthInstance.getCurrentUser();

                            loginAuthCallback.onLoginAuthSuccess(new UserDTO(
                                    user.getDisplayName(),
                                    user.getEmail(),
                                    password,
                                    user.getUid()
                            ));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            loginAuthCallback.onLoginAuthFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void loginUsingGoogleEmail(String idToken, LoginAuthCallback loginAuthCallback) {
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            firebaseAuthInstance.signInWithCredential(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = firebaseAuthInstance.getCurrentUser();
                            loginAuthCallback.onLoginAuthSuccess(
                                    new UserDTO(
                                            currentUser.getDisplayName(),
                                            currentUser.getEmail(),
                                            null,
                                            currentUser.getUid()
                                    )
                            );
                        } else {
                            loginAuthCallback.onLoginAuthFailure(task.getException().getMessage());
                        }
                    });
    }

    @Override
    public void loginUsingFacebookEmail(LoginAuthCallback loginAuthCallback) {

    }

    @Override
    public void loginUsingGuest(LoginAuthCallback loginAuthCallback) {
        firebaseAuthInstance.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            loginAuthCallback.onLoginAuthSuccess(null);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            loginAuthCallback.onLoginAuthFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void signOut() {
        firebaseAuthInstance.signOut();
    }

    @Override
    public boolean isUserLoggedIn() {
        boolean isLoggedIn = true;
        FirebaseUser currentUser = firebaseAuthInstance.getCurrentUser();
        if (currentUser == null) {
            isLoggedIn = false;
        }
        return isLoggedIn;
    }

}
