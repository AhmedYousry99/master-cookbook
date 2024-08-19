package com.senseicoder.mastercookbook.util.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.UserDTO;

public interface SignupAuthCallback {

    public void onSignupAuthSuccess(UserDTO userDTO);

    public void onSignupAuthFailure(String message);
}
