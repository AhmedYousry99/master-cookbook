package com.senseicoder.mastercookbook.login.presenter;

import androidx.annotation.Nullable;

public interface LoginPresenter {

    void validateEmail(@Nullable String email);

    void validatePassword(@Nullable String password);

    void loginEmail(String email, String password);
}
