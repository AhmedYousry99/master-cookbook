package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FoodRemoteDataSource {

    Single<List<MealDTO>> getMealOfTheDay();

    Single<List<CategoryDTO>> getCategories();

    Single<List<CountryDTO>> getCountries();

    Single<List<IngredientDTO>> getIngredients();

    Single<List<MealDTO>> getMoreYouMightLike(String letter);

    Single<MealDTO> getMealDetails(String mealId);

    Single<List<MealDTO>> getMealsByLetters(String letters);

    Single<List<MealDTO>> getMealsByIngredient(String ingredient);

    Single<List<MealDTO>> getMealsByCategory(String category);

    Single<List<MealDTO>> getMealsByCountry(String country);

}
