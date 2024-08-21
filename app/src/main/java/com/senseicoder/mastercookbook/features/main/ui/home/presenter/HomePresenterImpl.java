package com.senseicoder.mastercookbook.features.main.ui.home.presenter;

import com.senseicoder.mastercookbook.features.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;
    DataRepository dataRepository;

    public HomePresenterImpl(HomeView homeView, DataRepository dataRepository) {
        this.homeView = homeView;
        this.dataRepository = dataRepository;
    }

    @Override
    public void getCategories() {
        Single<List<CategoryDTO>> categoriesSingle = dataRepository.getCategories();
        categoriesSingle.subscribe(categories -> homeView.updateCategoriesList(categories), e -> homeView.handleError(e.getMessage()));
    }

    @Override
    public void getIngredients() {

    }

    @Override
    public void getMealsYouMightLike(String letter) {
        Single<List<MealDTO>> mealOfTheDaySingle = dataRepository.getMealsYouMightLike(letter);
        mealOfTheDaySingle.subscribe(meals -> homeView.updateMealsYouMightLikeList(meals), e -> homeView.handleError(e.getMessage()));
    }


    @Override
    public void getMealOfTheDay() {
        Single<List<MealDTO>> categoriesSingle = dataRepository.getMealOfTheDay();
        categoriesSingle.subscribe(meal -> homeView.updateMealOfTheDayList(meal.get(0)), e -> homeView.handleError(e.getMessage()));
    }

}
