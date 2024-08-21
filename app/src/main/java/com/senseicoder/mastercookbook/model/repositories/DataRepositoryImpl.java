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
import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;
import com.senseicoder.mastercookbook.network.FoodRemoteDataSource;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;

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
        Single<List<CategoryDTO>> temp = foodRemoteDataSource.getCategories().map(
                GetCategoriesResponse::getCategories
        );
        return temp;
    }

    @Override
    public Single<List<CountryDTO>> getCountries() {
        Single<List<CountryDTO>> temp = foodRemoteDataSource.getCountries().map(
                GetCountriesResponse::getCountry
        );
        return temp;
    }

    @Override
    public Single<List<IngredientDTO>> getIngredients() {
        return foodRemoteDataSource.getIngredients().map(GetIngredientsResponse::getIngredient);
    }

    @Override
    public Single<MealDTO> getMealDetailsById(String mealId) {
        return foodRemoteDataSource.getMealDetails(mealId).map(getMealDataResponse -> getMealDataResponse.getMeals().get(0));
    }

    @Override
    public Single<List<MealDTO>> getMealsYouMightLike(String letter) {
        return foodRemoteDataSource.getMoreYouMightLike(letter).map(GetMealsResponse::getMeals);
    }

    @Override
    public Single<List<MealDTO>> getMealOfTheDay() {
        return foodRemoteDataSource.getMealOfTheDay().map(GetMealsResponse::getMeals);
    }

    @Override
    public void setCurrentUser(@Nullable UserDTO userDTO) {
        currentUser = userDTO;
    }
}
