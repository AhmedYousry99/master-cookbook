package com.senseicoder.mastercookbook.db;

import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;

public interface DBRemoteDataSource {

    public void addUser(UserDTO userDTO, DatabaseCallback databaseCallback);

    public void getUserByEmail(String email, GetUserByEmailCallback callback);

    public void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback);

    public UserDTO getCurrentUser();

    public void setCurrentUser(UserDTO userDTO);
}
