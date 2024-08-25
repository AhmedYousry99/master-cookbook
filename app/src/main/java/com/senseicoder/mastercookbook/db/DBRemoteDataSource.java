package com.senseicoder.mastercookbook.db;

import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;

public interface DBRemoteDataSource {

    void addUser(UserDTO userDTO, DatabaseCallback databaseCallback);

    void getUserByEmail(String email, GetUserByEmailCallback callback);

    void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback);

    UserDTO getCurrentUser();

    void setCurrentUser(UserDTO userDTO);
}
