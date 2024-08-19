package com.senseicoder.mastercookbook.network;

import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetCountriesResponse;
import com.senseicoder.mastercookbook.model.responses.GetIngredientsResponse;
import com.senseicoder.mastercookbook.model.responses.GetMealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodService {

    @GET(FoodServiceResponseKeys.RANDOM)
    Call<GetMealsResponse> getMealOfTheDay();

    @GET(FoodServiceResponseKeys.CATEGORIES_LIST_DETAILED)
    Call<GetCategoriesResponse> getCategoriesList();

    @GET(FoodServiceResponseKeys.COUNTRIES_LIST)
    Call<GetCountriesResponse> getCountriesList();

    @GET(FoodServiceResponseKeys.INGREDIENT_LIST)
    Call<GetIngredientsResponse> getIngredientsList();

    class FoodServiceResponseKeys{
        public static final String RANDOM = "random.php";
        public static final String LIST = "list.php";
        public static final String CATEGORIES_LIST_BRIEF = "list.php?c=list";
        public static final String CATEGORIES_LIST_DETAILED = "categories.php";
        public static final String COUNTRIES_LIST = "list.php?a=list";
        public static final String INGREDIENT_LIST = "list.php?i=list";
    }

    class FoodServiceRequestParams{

    }
}
