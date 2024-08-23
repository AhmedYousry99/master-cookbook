package com.senseicoder.mastercookbook.model.repositories;

import androidx.annotation.Nullable;

import com.senseicoder.mastercookbook.db.DBRemoteDataSource;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.network.FoodRemoteDataSource;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;
import com.senseicoder.mastercookbook.util.enums.SearchType;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class DataRepositoryImpl implements DataRepository {

    private static DataRepositoryImpl instance = null;
    private final DBRemoteDataSource dbRemoteDataSource;
    private final FoodRemoteDataSource foodRemoteDataSource;
    @Nullable
    private UserDTO currentUser;

    public static DataRepositoryImpl getInstance(DBRemoteDataSource dbRemoteDataSource, FoodRemoteDataSource foodRemoteDataSource) {
        if (instance == null)
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
    public Single<List<CategoryDTO>> getCategories() {
        Single<List<CategoryDTO>> temp = foodRemoteDataSource.getCategories();
        return temp;
    }

    @Override
    public Single<List<CountryDTO>> getCountries() {
        Single<List<CountryDTO>> temp = foodRemoteDataSource.getCountries();
        return temp;
    }

    @Override
    public Single<List<IngredientDTO>> getIngredients() {
        return foodRemoteDataSource.getIngredients();
    }

    @Override
    public Single<MealDTO> getMealDetailsById(String mealId) {
        return foodRemoteDataSource.getMealDetails(mealId);
    }

    @Override
    public Single<List<MealDTO>> getMealsYouMightLike(String letter) {
        return foodRemoteDataSource.getMoreYouMightLike(letter);
    }

    @Override
    public Single<List<MealDTO>> getMealOfTheDay() {
        return foodRemoteDataSource.getMealOfTheDay();
    }

    @Override
    public Single<List<MealDTO>> searchMeal(String word, SearchType type) {
        switch (type){
            case Name:
                return foodRemoteDataSource.getMealsByLetters(word);
            case Country:
                return foodRemoteDataSource.getMealsByCountry(word);
            case Category:
                return foodRemoteDataSource.getMealsByCategory(word);
            case Ingredient:
                return foodRemoteDataSource.getMealsByIngredient(word);
        }
        return null;
    }

    @Override
    public void setCurrentUser(@Nullable UserDTO userDTO) {
        currentUser = userDTO;
    }
}
