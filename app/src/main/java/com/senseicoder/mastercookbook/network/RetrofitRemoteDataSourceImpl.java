package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;
import com.senseicoder.mastercookbook.network.callbacks.GetCategoriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetCountriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetIngredientsCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetMealOfTheDayCallback;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRemoteDataSourceImpl implements FoodRemoteDataSource{
    public static final String TAG = "RetrofitRemoteDataSourceImpl";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
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
    public void getMealOfTheDay(GetMealOfTheDayCallback callback){
        foodService.getMealOfTheDay().enqueue(new Callback<GetMealsResponse>() {
            @Override
            public void onResponse(Call<GetMealsResponse> call, Response<GetMealsResponse> response) {
                callback.onGetMealsOfTheDaySuccess(response.body().getMeals().get(0));
            }
            @Override
            public void onFailure(Call<GetMealsResponse> call, Throwable throwable) {
                callback.onGetMealsOfTheDayFailure(throwable.getMessage());
            }
        });
    }

    @Override
    public void getCategories(GetCategoriesCallback getCategoriesCallback) {
        foodService.getCategoriesList().enqueue(new Callback<GetCategoriesResponse>() {
            @Override
            public void onResponse(Call<GetCategoriesResponse> call, Response<GetCategoriesResponse> response) {
                getCategoriesCallback.onGetCategoriesSuccess(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<GetCategoriesResponse> call, Throwable throwable) {
                getCategoriesCallback.onGetCategoriesFailure(throwable.getMessage());
            }
        });
    }

    @Override
    public void getCountries(GetCountriesCallback getCountriesCallback) {
        foodService.getCountriesList().enqueue(new Callback<GetCountriesResponse>() {
            @Override
            public void onResponse(Call<GetCountriesResponse> call, Response<GetCountriesResponse> response) {
                getCountriesCallback.onGetCountriesSuccess(response.body().getCountry());
            }

            @Override
            public void onFailure(Call<GetCountriesResponse> call, Throwable throwable) {
                getCountriesCallback.onGetCountriesFailure(throwable.getMessage());
            }
        });
    }

    @Override
    public void getIngredients(GetIngredientsCallback getIngredientsCallback) {
        foodService.getIngredientsList().enqueue(new Callback<GetIngredientsResponse>() {
            @Override
            public void onResponse(Call<GetIngredientsResponse> call, Response<GetIngredientsResponse> response) {
                getIngredientsCallback.onGetIngredientsSuccess(response.body().getIngredient());
            }

            @Override
            public void onFailure(Call<GetIngredientsResponse> call, Throwable throwable) {
                getIngredientsCallback.onGetIngredientsFailure(throwable.getMessage());
            }
        });
    }

    public static RetrofitRemoteDataSourceImpl getInstance(File cacheDirectory) {
        if(instance == null || cacheDirectory != null)
            instance = new RetrofitRemoteDataSourceImpl(cacheDirectory);
        return instance;
    }
}
