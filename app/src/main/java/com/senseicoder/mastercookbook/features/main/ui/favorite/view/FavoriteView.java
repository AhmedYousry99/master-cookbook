package com.senseicoder.mastercookbook.features.main.ui.favorite.view;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.util.interfaces.BaseView;

import java.util.List;

public interface FavoriteView extends BaseView {

    void updateList(List<MealSimplifiedModel> meals);

    void onMealDeletedSuccess(MealSimplifiedModel meal);

    void onFailure(Throwable e);

}
