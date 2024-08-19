package com.senseicoder.mastercookbook.model.responses;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealKeys;

import java.util.List;

public class GetMealsResponse {

    @SerializedName(MealKeys.MEALS)
    List<MealDTO> meals;

    public List<MealDTO> getMeals() {
        return meals;
    }
}
