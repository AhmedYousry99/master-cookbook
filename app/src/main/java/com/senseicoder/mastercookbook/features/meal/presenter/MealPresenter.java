package com.senseicoder.mastercookbook.features.meal.presenter;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.List;

public interface MealPresenter {

    void getMealData(String mealId);

    void clear();

    void addMealToFavorites(MealDTO meal);

    void deleteMealFromFavorites(MealDTO meal);

    void addMealToPlans(MealDTO meal,  List<WeekDays> days);

}
