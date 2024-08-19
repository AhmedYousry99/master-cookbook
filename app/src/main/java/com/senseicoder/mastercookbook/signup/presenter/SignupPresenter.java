package com.senseicoder.mastercookbook.signup.presenter;

import androidx.annotation.Nullable;

import com.senseicoder.mastercookbook.model.DTOs.UserDTO;

public interface SignupPresenter {

    void validateDisplayName(@Nullable String displayName);

    void validateEmail(@Nullable String email);

    void validatePassword(@Nullable String password);

    void validateConfirmPassword(@Nullable String password, @Nullable String confirmPassword);

    void signup(UserDTO userDTO);


}
