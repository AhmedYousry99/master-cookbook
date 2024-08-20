package com.senseicoder.mastercookbook.main.ui.home.presenter;



public interface HomePresenter {

    void getCategories();

    void getCountries();

    void getIngredients();

    void getMealsYouMightLike(String letter);

    void getMealOfTheDay();

}
