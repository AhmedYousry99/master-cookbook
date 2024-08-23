package com.senseicoder.mastercookbook.model.responses;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.MealKeys;

import java.util.List;

public class GetMealsByCategoryResponse {

    @SerializedName(MealKeys.MEALS)
    List<MealDTO> meals;

    public List<MealDTO> getMeals() {
        return meals;
    }
}
