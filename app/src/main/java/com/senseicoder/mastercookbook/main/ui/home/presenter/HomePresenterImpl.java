package com.senseicoder.mastercookbook.main.ui.home.presenter;

import com.senseicoder.mastercookbook.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.model.responses.GetCategoriesResponse;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;
    DataRepository dataRepository;

    public HomePresenterImpl(HomeView homeView, DataRepository dataRepository) {
        this.homeView = homeView;
        this.dataRepository = dataRepository;
    }

    @Override
    public void getCategories() {
        Single<List<CategoryDTO>> categoriesSingle= dataRepository.getCategories();
        categoriesSingle.subscribe(categories -> onGetCategoriesSuccess(categories), e -> onGetCategoriesFailure(e.getMessage()));
    }

    @Override
    public void getCountries() {
        Single<List<CountryDTO>> categoriesSingle= dataRepository.getCountries();
        categoriesSingle.subscribe(countries -> onGetCountriesSuccess(countries), e -> onGetCountriesFailure(e.getMessage()));
    }

    @Override
    public void getIngredients() {

    }

    @Override
    public void getMealsYouMightLike(String letter) {
        Single<List<MealDTO>> mealOfTheDaySingle= dataRepository.getMealsYouMightLike(letter);
        mealOfTheDaySingle.subscribe(meals -> onGetMealsYouMightLikeSuccess(meals), e -> onGetMealsYouMightLikeFailure(e.getMessage()));
    }

    private void onGetMealsYouMightLikeSuccess(List<MealDTO> meals) {
        homeView.updateMealsYouMightLikeList(meals);
    }

    private void onGetMealsYouMightLikeFailure(String message) {
    }

    @Override
    public void getMealOfTheDay() {
        Single<List<MealDTO>> categoriesSingle= dataRepository.getMealOfTheDay();
        categoriesSingle.subscribe(meal -> onGetMealsOfTheDaySuccess(meal.get(0)), e -> onGetMealsOfTheDayFailure(e.getMessage()));
    }

    public void onGetCountriesSuccess(List<CountryDTO> countries) {
        homeView.updateCountriesList(countries);
    }

    public void onGetCountriesFailure(String message) {

    }

    public void onGetIngredientsSuccess(List<IngredientDTO> ingredient) {

    }

    public void onGetIngredientsFailure(String message) {

    }

    public void onGetMealsOfTheDaySuccess(MealDTO mealDTO) {
        homeView.updateMealOfTheDayList(mealDTO);
    }

    public void onGetMealsOfTheDayFailure(String message) {

    }

    public void onGetCategoriesSuccess(List<CategoryDTO> categories) {
    homeView.updateCategoriesList(categories);
    }

    public void onGetCategoriesFailure(String message) {

    }
}
