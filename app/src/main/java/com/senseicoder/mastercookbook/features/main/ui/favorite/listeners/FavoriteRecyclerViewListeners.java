package com.senseicoder.mastercookbook.features.main.ui.favorite.listeners;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;

public interface FavoriteRecyclerViewListeners {

    void onFavoriteClicked(MealSimplifiedModel meal);

    void onCheckIngredientsClicked(MealSimplifiedModel meal);
}
