package com.senseicoder.mastercookbook.features.main.ui.home.presenter;



public interface HomePresenter {

    void getCategories();

    void getIngredients();

    void getMealsYouMightLike(String letter);

    void getMealOfTheDay();

}
