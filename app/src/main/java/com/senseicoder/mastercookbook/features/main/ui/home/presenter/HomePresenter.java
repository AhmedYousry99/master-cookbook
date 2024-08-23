package com.senseicoder.mastercookbook.features.main.ui.home.presenter;


import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

public interface HomePresenter {

    void getCategories();

    void getMealsYouMightLike(String letter);

    void getMealOfTheDay();

    void addMealToFavorite(MealDTO meal);

    void deleteMealFromFavorite(MealDTO meal);

    void clear();

}
