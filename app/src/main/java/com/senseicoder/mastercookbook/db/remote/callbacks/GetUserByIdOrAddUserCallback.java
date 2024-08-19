package com.senseicoder.mastercookbook.db.remote.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.UserDTO;

public interface GetUserByIdOrAddUserCallback {

    void onGetUserByIdOrAddUserCallbackSuccess(UserDTO userDTO);

    void onGetUserByIdOrAddUserCallbackailure(String message);
}
