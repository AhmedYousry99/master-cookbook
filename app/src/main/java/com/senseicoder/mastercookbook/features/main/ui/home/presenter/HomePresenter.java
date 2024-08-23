package com.senseicoder.mastercookbook.features.main.ui.home.presenter;



public interface HomePresenter {

    void getCategories();

    void getMealsYouMightLike(String letter);

    void getMealOfTheDay();

    void clear();

}
