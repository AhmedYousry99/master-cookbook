package com.senseicoder.mastercookbook.main.ui.home.presenter;

import com.senseicoder.mastercookbook.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.network.callbacks.GetCategoriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetCountriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetIngredientsCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetMealOfTheDayCallback;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, GetCountriesCallback, GetIngredientsCallback, GetMealOfTheDayCallback, GetCategoriesCallback {

    HomeView homeView;
    DataRepository dataRepository;

    public HomePresenterImpl(HomeView homeView, DataRepository dataRepository) {
        this.homeView = homeView;
        this.dataRepository = dataRepository;
    }

    @Override
    public void getCategories() {

    }

    @Override
    public void getCountries() {

    }

    @Override
    public void getIngredients() {

    }

    @Override
    public void getMealOfTheDay() {

    }

    @Override
    public void onGetCountriesSuccess(List<CountryDTO> country) {

    }

    @Override
    public void onGetCountriesFailure(String message) {

    }

    @Override
    public void onGetIngredientsSuccess(List<IngredientDTO> ingredient) {

    }

    @Override
    public void onGetIngredientsFailure(String message) {

    }

    @Override
    public void onGetMealsOfTheDaySuccess(MealDTO mealDTO) {

    }

    @Override
    public void onGetMealsOfTheDayFailure(String message) {

    }

    @Override
    public void onGetCategoriesSuccess(List<CategoryDTO> categories) {

    }

    @Override
    public void onGetCategoriesFailure(String message) {

    }
}
