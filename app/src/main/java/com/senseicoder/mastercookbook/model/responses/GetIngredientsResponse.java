package com.senseicoder.mastercookbook.model.responses;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealKeys;

import java.util.List;

public class GetIngredientsResponse {
    @SerializedName(MealKeys.MEALS)
    List<IngredientDTO> ingredient;

    public List<IngredientDTO> getIngredient() {
        return ingredient;
    }
}
