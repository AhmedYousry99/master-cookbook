package com.senseicoder.mastercookbook.db.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.senseicoder.mastercookbook.db.DBRemoteDataSource;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;

public class FirebaseFirestoreRemoteDataSourceImpl implements DBRemoteDataSource {

    private static final String TAG = "FirebaseFirestoreRemoteDataSourceImpl";
    private static FirebaseFirestoreRemoteDataSourceImpl instance = null;
    private final FirebaseFirestore firebaseFirestoreInstance;
    private UserDTO currentUser;

    public static FirebaseFirestoreRemoteDataSourceImpl getInstance() {
        if(instance == null)
            instance = new FirebaseFirestoreRemoteDataSourceImpl();
        return instance;
    }

    public FirebaseFirestoreRemoteDataSourceImpl() {
        firebaseFirestoreInstance = FirebaseFirestore.getInstance();
    }

    @Override
    public UserDTO getCurrentUser() {
        return currentUser;
    }
    @Override
    public void setCurrentUser(UserDTO currentUser) {
        this.currentUser = currentUser;
        Log.i(TAG, "setCurrentUser: " + currentUser.toString());
    }

    @Override
    public void addUser(UserDTO userDTO, DatabaseCallback firebaseFirestoreCallback) {
        DocumentReference documentReference = firebaseFirestoreInstance.collection(UserDTO.collectionName).document();
        if(userDTO.getId() == null)
            userDTO.setId(documentReference.getId());
        documentReference.set(userDTO).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Log.i(TAG, "addUser: Success");
                firebaseFirestoreCallback.onDatabaseSuccess();
            }else{
                Log.d(TAG, "addUser: Failure \n" + task.getException().getMessage());
                firebaseFirestoreCallback.onDatabaseFailure(task.getException().getMessage());
            }
        });
    }


    @Override
    public void getUserByEmail(String email, GetUserByEmailCallback callback) {
        firebaseFirestoreInstance.collection(UserDTO.collectionName).whereEqualTo(UserDTO.UserKeys.email, email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(!task.getResult().isEmpty()){
                        callback.onGetUserByIdSuccess(UserDTO.fromDocument(task.getResult().getDocuments().get(0)));
                        Log.i(TAG, "getUserByEmail: Success");
                    }
                }else{
                    callback.onGetUserByIdFailure(task.getException().getMessage());
                    Log.d(TAG, "getUserByEmail: Failure \n" + task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback) {
        DocumentReference userRef = firebaseFirestoreInstance.collection("users").document(userDTO.getId());
        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    callback.onGetUserByIdOrAddUserCallbackSuccess(UserDTO.fromDocument(document));
                    Log.i(TAG, "getUserByIdOrAddUser: Success and User Exists");
                } else {
                    // User doesn't exist, add the new user
                    userRef.set(userDTO)
                            .addOnSuccessListener(aVoid -> {
                                callback.onGetUserByIdOrAddUserCallbackSuccess(userDTO);
                                Log.i(TAG, "getUserByIdOrAddUser: Success and User Created");
                            })
                            .addOnFailureListener(e -> {
                                callback.onGetUserByIdOrAddUserCallbackailure(e.getMessage());
                                Log.d(TAG, "getUserByIdOrAddUser: Success but User creation failed\n" + e.getMessage());
                            });
                }

            } else {
                callback.onGetUserByIdOrAddUserCallbackailure(task.getException().getMessage());
                Log.d(TAG, "addUser: Failure \n" + task.getException().getMessage());
            }
        });
    }
}
