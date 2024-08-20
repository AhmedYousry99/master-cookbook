package com.senseicoder.mastercookbook.main.ui.home.listeners;

public interface nestedRecyclerViewItemListeners {
    void onCheckIngredientsClicked(String idMeal);
    void onFavoriteClicked(String idMeal);
    void onGetMealsByCountryClicked(String country);
    void onGetMealsByCategoryClicked(String category);
}
