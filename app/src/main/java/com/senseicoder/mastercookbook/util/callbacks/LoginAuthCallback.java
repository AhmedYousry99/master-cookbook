package com.senseicoder.mastercookbook.util.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.UserDTO;

public interface LoginAuthCallback {

    public void onLoginAuthSuccess(UserDTO userDTO);

    public void onLoginAuthFailure(String message);
}
