package com.senseicoder.mastercookbook.network;

import android.util.Log;

import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByCategoryResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByCountryResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByIngredientResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;
import com.senseicoder.mastercookbook.util.global.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRemoteDataSourceImpl implements FoodRemoteDataSource{
    public static final String TAG = "RetrofitRemoteDataSourceImpl";

    private static final String BASE_URL = Constants.MEALDB_BASE_URL;
    private static RetrofitRemoteDataSourceImpl instance = null;
    private FoodService foodService;


    private RetrofitRemoteDataSourceImpl(File cacheDirectory) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheDirectory, cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        foodService = retrofit.create(FoodService.class);
    }

    @Override
    public Single<List<MealDTO>> getMealOfTheDay(){
        return foodService.getMealOfTheDay().subscribeOn(Schedulers.io()).map(
                GetMealsResponse::getMeals
        );
    }

    @Override
    public Single<List<CategoryDTO>> getCategories() {
        return foodService.getCategoriesList().subscribeOn(Schedulers.io()).map(
                GetCategoriesResponse::getCategories
        );
    }

    @Override
    public Single<List<CountryDTO>> getCountries() {
        return foodService.getCountriesList().subscribeOn(Schedulers.io()).map(
                GetCountriesResponse::getCountries
        );
    }

    @Override
    public Single<List<IngredientDTO>> getIngredients() {
       return foodService.getIngredientsList().subscribeOn(Schedulers.io()).map(
               GetIngredientsResponse::getIngredient
       );
    }

    @Override
    public Single<List<MealDTO>> getMoreYouMightLike(String letter) {
        return foodService.getMealsYouMightLikeList(letter).subscribeOn(Schedulers.io()).map(
                GetMealsResponse::getMeals
        );
    }

    @Override
    public Single<MealDTO> getMealDetails(String mealId) {
        return foodService.getMealDetailsById(mealId).subscribeOn(Schedulers.io()).map(
                getMealDataResponse -> getMealDataResponse.getMeals().get(0)
        );
    }

    @Override
    public Single<List<MealDTO>> getMealsByLetters(String letters) {
        return foodService.getMealsByLetters(letters).subscribeOn(Schedulers.io()).doOnError(throwable -> Log.d(TAG, "getMealsByLetters: " + throwable.getMessage()))
                .map(getMealsByLettersResponse -> {
                    List<MealDTO> meals = getMealsByLettersResponse.getMeals();
                    if(meals == null)
                        meals = new ArrayList<>();
                    return meals;
                }).doOnError(throwable -> Log.d(TAG, "getMealsByLetters: " + throwable.getMessage()));
    }

    @Override
    public Single<List<MealDTO>> getMealsByIngredient(String ingredient) {
        return foodService.getMealsByIngredient(ingredient).subscribeOn(Schedulers.io())
                .map(GetMealsByIngredientResponse::getMeals);
    }

    @Override
    public Single<List<MealDTO>> getMealsByCategory(String category) {
        return foodService.getMealsByCategory(category).subscribeOn(Schedulers.io()).doOnError(throwable -> Log.d(TAG, "getMealsByCategory: Error" + throwable.getMessage())).doOnSuccess(getMealsByCategoryResponse -> Log.d(TAG, "getMealsByCategory: " + getMealsByCategoryResponse.getMeals().toString()))
                .map(GetMealsByCategoryResponse::getMeals).doOnSuccess(mealDTOS -> Log.d(TAG, "getMealsByCategory: " + mealDTOS.toString()));
    }

    @Override
    public Single<List<MealDTO>> getMealsByCountry(String country) {
        return foodService.getMealsByCountry(country).subscribeOn(Schedulers.io())
                .map(GetMealsByCountryResponse::getMeals);
    }


    public static RetrofitRemoteDataSourceImpl getInstance(File cacheDirectory) {
        if(instance == null || cacheDirectory != null)
            instance = new RetrofitRemoteDataSourceImpl(cacheDirectory);
        return instance;
    }
}
