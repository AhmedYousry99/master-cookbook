package com.senseicoder.mastercookbook.features.meals.view;

import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.util.interfaces.BaseView;

import java.util.List;

public interface MealsView extends BaseView {

    void updateList(List<MealDTO> meals);
}
