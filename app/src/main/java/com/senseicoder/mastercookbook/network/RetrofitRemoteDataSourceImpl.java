package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;

import java.io.File;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
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
    public Single<GetMealsResponse> getMealOfTheDay(){
        return foodService.getMealOfTheDay().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<GetCategoriesResponse> getCategories() {
        return foodService.getCategoriesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<GetCountriesResponse> getCountries() {
        return foodService.getCountriesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<GetIngredientsResponse> getIngredients() {
       return foodService.getIngredientsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<GetMealsResponse> getMoreYouMightLike(String letter) {
        return foodService.getMealsYouMightLikeList(letter).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static RetrofitRemoteDataSourceImpl getInstance(File cacheDirectory) {
        if(instance == null || cacheDirectory != null)
            instance = new RetrofitRemoteDataSourceImpl(cacheDirectory);
        return instance;
    }
}
