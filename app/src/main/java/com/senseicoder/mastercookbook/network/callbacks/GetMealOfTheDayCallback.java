package com.senseicoder.mastercookbook.network.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

public interface GetMealOfTheDayCallback {
    public void onGetMealsOfTheDaySuccess(MealDTO mealDTO);

    public void onGetMealsOfTheDayFailure(String message);
}
