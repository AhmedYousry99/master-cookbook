package com.senseicoder.mastercookbook.model.repositories;

import androidx.annotation.Nullable;

import com.senseicoder.mastercookbook.db.DBRemoteDataSource;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.network.FoodRemoteDataSource;
import com.senseicoder.mastercookbook.network.callbacks.GetCategoriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetCountriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetIngredientsCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetMealOfTheDayCallback;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;

public class DataRepositoryImpl implements DataRepository{

    private static DataRepositoryImpl instance = null;
    private final DBRemoteDataSource dbRemoteDataSource;
    private final FoodRemoteDataSource foodRemoteDataSource;
    @Nullable
    private UserDTO currentUser;

    public static DataRepositoryImpl getInstance(DBRemoteDataSource dbRemoteDataSource, FoodRemoteDataSource foodRemoteDataSource) {
        if(instance == null)
            instance = new DataRepositoryImpl(dbRemoteDataSource, foodRemoteDataSource);
        return instance;
    }

    public DataRepositoryImpl(DBRemoteDataSource dbRemoteDataSource, FoodRemoteDataSource foodRemoteDataSource) {
        this.dbRemoteDataSource = dbRemoteDataSource;
        this.foodRemoteDataSource = foodRemoteDataSource;
    }


    @Override
    public void addUser(UserDTO userDTO, DatabaseCallback databaseCallback) {
        dbRemoteDataSource.addUser(userDTO, databaseCallback);
    }

    @Override
    public void getUserByEmail(String email, GetUserByEmailCallback callback) {
        dbRemoteDataSource.getUserByEmail(email, callback);
    }

    @Override
    public void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback) {
        dbRemoteDataSource.getUserByIdOrAddUser(userDTO, callback);
    }

    @Override
    @Nullable
    public UserDTO getCurrentUser() {
        currentUser = dbRemoteDataSource.getCurrentUser();
        return currentUser;
    }

    @Override
    public void getCategories(GetCategoriesCallback callback) {
        foodRemoteDataSource.getCategories(callback);
    }

    @Override
    public void getCountries(GetCountriesCallback callback) {
        foodRemoteDataSource.getCountries(callback);
    }

    @Override
    public void getIngredients(GetIngredientsCallback callback) {
        foodRemoteDataSource.getIngredients(callback);
    }

    @Override
    public void getMealOfTheDay(GetMealOfTheDayCallback callback) {
        foodRemoteDataSource.getMealOfTheDay(callback);
    }

    @Override
    public void setCurrentUser(@Nullable UserDTO userDTO) {
        currentUser = userDTO;
    }
}
