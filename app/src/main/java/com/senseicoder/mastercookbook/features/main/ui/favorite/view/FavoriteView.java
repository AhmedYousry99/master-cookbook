package com.senseicoder.mastercookbook.features.main.ui.favorite.view;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;

import java.util.List;

public interface FavoriteView {

    void updateList(List<MealSimplifiedModel> meals);

    void onGetMealDataSuccess(MealDTO mealDTO);

    void onMealDeletedSuccess();

    void onFailure(Throwable e);

    void onMealAddedSuccess();
}
