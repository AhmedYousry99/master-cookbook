package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.model.responses.GetCategoriesNamesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealDataResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByCategoryResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByCountryResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByIngredientResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsByLettersResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {

    @GET(FoodServiceResponseKeys.RANDOM)
    Single<GetMealsResponse> getMealOfTheDay();

    @GET(FoodServiceResponseKeys.CATEGORIES_LIST_DETAILED)
    Single<GetCategoriesResponse> getCategoriesList();

    @GET(FoodServiceResponseKeys.COUNTRIES_LIST)
    Single<GetCountriesResponse> getCountriesList();

    @GET(FoodServiceResponseKeys.INGREDIENT_LIST)
    Single<GetIngredientsResponse> getIngredientsList();

    @GET(FoodServiceResponseKeys.MEAL_BY_FIRST_LETTER)
    Single<GetMealsResponse> getMealsYouMightLikeList(@Query("f") String letter);

    @GET(FoodServiceResponseKeys.MEAL_BY_ID)
    Single<GetMealDataResponse> getMealDetailsById(@Query("i") String mealId);

    @GET(FoodServiceResponseKeys.MEAL_SEARCH_BY_MEAL_LETTERS)
    Single<GetMealsByLettersResponse> getMealsByLetters(@Query("s") String letters);

    @GET(FoodServiceResponseKeys.MEAL_SEARCH_BY_INGREDIENT)
    Single<GetMealsByIngredientResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET(FoodServiceResponseKeys.MEAL_SEARCH_BY_CATEGORY)
    Single<GetMealsByCategoryResponse> getMealsByCategory(@Query("c") String category);

    @GET(FoodServiceResponseKeys.MEAL_SEARCH_BY_COUNTRY)
    Single<GetMealsByCountryResponse> getMealsByCountry(@Query("a") String country);

    @GET(FoodServiceResponseKeys.CATEGORIES_LIST_BRIEF)
    Single<GetCategoriesNamesResponse> getCategoriesNamesList();



}

class FoodServiceResponseKeys{
    public static final String RANDOM = "random.php";
    public static final String LIST = "list.php";
    public static final String CATEGORIES_LIST_BRIEF = "list.php?c=list";
    public static final String CATEGORIES_LIST_DETAILED = "categories.php";
    public static final String COUNTRIES_LIST = "list.php?a=list";
    public static final String INGREDIENT_LIST = "list.php?i=list";
    public static final String MEAL_BY_FIRST_LETTER = "search.php?f=a";
    public static final String MEAL_BY_ID = "lookup.php";
    public static final String MEAL_SEARCH_BY_MEAL_LETTERS = "search.php";
    public static final String MEAL_SEARCH_BY_INGREDIENT = "filter.php";
    public static final String MEAL_SEARCH_BY_CATEGORY = "filter.php";
    public static final String MEAL_SEARCH_BY_COUNTRY = "filter.php";
}