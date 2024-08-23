package com.senseicoder.mastercookbook.features.main.ui.favorite.presenter;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;


public interface FavoritePresenter {

    void getFavorites();

    void deleteMeal(MealSimplifiedModel meal);

    void getMealData(MealSimplifiedModel meal);

    void clear();
}
