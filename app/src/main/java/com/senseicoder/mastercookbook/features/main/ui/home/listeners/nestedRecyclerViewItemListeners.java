package com.senseicoder.mastercookbook.features.main.ui.home.listeners;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

public interface nestedRecyclerViewItemListeners {
    void onCheckIngredientsClicked(String idMeal);
    void onFavoriteClicked(MealDTO mealDTO);
    void onGetMealsByCountryClicked(String country);
    void onGetMealsByCategoryClicked(String category);
}
