package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.network.callbacks.GetCategoriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetCountriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetIngredientsCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetMealOfTheDayCallback;

public interface FoodRemoteDataSource {

    public void getMealOfTheDay(GetMealOfTheDayCallback getMealOfTheDayCallback);

    public void getCategories(GetCategoriesCallback getCategoriesCallback);

    public void getCountries(GetCountriesCallback getCountriesCallback);

    public void getIngredients(GetIngredientsCallback getIngredientsCallback);
}
