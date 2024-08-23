package com.senseicoder.mastercookbook.model.repositories;

import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;
import com.senseicoder.mastercookbook.util.enums.SearchType;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface DataRepository {

    void addUser(UserDTO userDTO, DatabaseCallback databaseCallback);

    void getUserByEmail(String email, GetUserByEmailCallback callback);

    void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback);

    UserDTO getCurrentUser();

    Single<List<CategoryDTO>> getCategories();

    Single<List<CountryDTO>> getCountries();

    Single<List<IngredientDTO>> getIngredients();

    Single<MealDTO> getMealDetailsById(String mealId);

    Single<List<MealDTO>> getMealsYouMightLike(String letter);

    Single<List<MealDTO>> getMealOfTheDay();

    Single<List<MealDTO>> searchMeal(String word, SearchType type);

    Single<List<MealSimplifiedModel>> getFavoriteMeals(String userId);

    Completable addMealToFavorites(MealSimplifiedModel meal);

    Completable deleteMealFromFavorite(MealSimplifiedModel meal);

    void setCurrentUser(UserDTO userDTO);

    Single<List<PlanDTO>> getUserPlan(String userId);

    Completable addMealToPlan(PlanDTO planDTO);

    Completable deleteMealFromPlan(PlanDTO planDTO);

}
