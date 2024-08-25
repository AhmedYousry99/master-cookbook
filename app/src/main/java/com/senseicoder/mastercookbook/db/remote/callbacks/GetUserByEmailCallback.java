package com.senseicoder.mastercookbook.db.remote.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.UserDTO;

public interface GetUserByEmailCallback {

    void onGetUserByIdSuccess(UserDTO userDTO);

    void onGetUserByIdFailure(String message);
}
