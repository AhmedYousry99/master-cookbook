package com.senseicoder.mastercookbook.features.main.ui.home.presenter;


import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.List;

public interface HomePresenter {

    void getCategories();

    void getMealsYouMightLike(String letter);

    void getMealOfTheDay();

    void addMealToFavorite(MealDTO meal);

    void deleteMealFromFavorite(MealDTO meal);

    void addMealToBookmark(MealDTO meal, List<WeekDays> days);


    void clear();

}
