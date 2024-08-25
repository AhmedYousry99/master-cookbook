package com.senseicoder.mastercookbook.features.main.ui.home.view;

import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.util.interfaces.BaseView;

import java.util.List;

public interface HomeView extends BaseView {

    void updateCategoriesList(List<CategoryDTO> categories);

    void updateMealOfTheDayList(MealDTO mealDTO);

    void updateMealsYouMightLikeList(List<MealDTO> meals);

    void onMealRemovedSuccess();

    void onMealAddedSuccess();

    void showPermissionDenied();
}
