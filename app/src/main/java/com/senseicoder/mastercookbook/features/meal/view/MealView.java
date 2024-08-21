package com.senseicoder.mastercookbook.features.meal.view;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

public interface MealView {
    void updateMealData(MealDTO meal);

    void handleError(String message);
}
