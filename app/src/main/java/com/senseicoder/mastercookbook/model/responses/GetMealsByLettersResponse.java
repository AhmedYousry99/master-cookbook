package com.senseicoder.mastercookbook.model.responses;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealKeys;

import java.util.List;

public class GetMealsByLettersResponse {

    @SerializedName(MealKeys.MEALS)
    List<MealDTO> meals;

    public List<MealDTO> getMeals() {
        return meals;
    }
}
