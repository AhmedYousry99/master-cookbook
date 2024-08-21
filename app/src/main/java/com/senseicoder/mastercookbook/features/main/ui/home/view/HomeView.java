package com.senseicoder.mastercookbook.features.main.ui.home.view;

import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

import java.util.List;

public interface HomeView {

    void updateCategoriesList(List<CategoryDTO> categories);

    void updateMealOfTheDayList(MealDTO mealDTO);

    void updateMealsYouMightLikeList(List<MealDTO> meals);

    void handleError(String message);

}
