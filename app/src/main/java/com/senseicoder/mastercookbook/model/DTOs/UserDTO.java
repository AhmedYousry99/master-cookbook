package com.senseicoder.mastercookbook.model.DTOs;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;

public class UserDTO {

    public static final String collectionName = "Users";
    private String displayName;
    private String email;
    private String password;
    private String id;

    public static UserDTO fromDocument(DocumentSnapshot document) {
        String tempDisplayName = document.getString(UserKeys.displayName);
        String tempEmail = document.getString(UserKeys.email);
        String tempPassword = document.getString(UserKeys.password);
        String tempId = document.getId();
        return new UserDTO(
                tempDisplayName,
                tempEmail,
                tempPassword,
                tempId
        );
    }

    public UserDTO() {
        displayName = null;
        email = null;
        password = null;
        id = null;
    }

    public UserDTO(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        id = null;
    }

    public UserDTO(String displayName, String email, String password, String id) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class UserKeys {
        public static final String user = "user";
        public static final String displayName = "displayName";
        public static final String email = "email";
        public static final String password = "password";
    }

    @NonNull
    @Override
    public String toString() {
        return "displayName: " +
                displayName +
                ", email: " +
                email +
                ", password: " +
                password;
    }
}
