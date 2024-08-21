package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealDataResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;
import io.reactivex.rxjava3.core.Single;

public interface FoodRemoteDataSource {

    Single<GetMealsResponse> getMealOfTheDay();

    Single<GetCategoriesResponse> getCategories();

    Single<GetCountriesResponse> getCountries();

    Single<GetIngredientsResponse> getIngredients();

    Single<GetMealsResponse> getMoreYouMightLike(String letter);

    Single<GetMealDataResponse> getMealDetails(String mealId);
}
