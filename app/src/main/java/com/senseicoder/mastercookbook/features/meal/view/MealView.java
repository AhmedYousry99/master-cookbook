package com.senseicoder.mastercookbook.features.meal.view;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

public interface MealView {
    void updateMealData(MealDTO meal);

    void onAddSuccess(MealDTO meal);

    void onDeleteSuccess();

    void onFailure(Throwable th);

    void handleError(Throwable th);

    void showPermissionDenied();
}
