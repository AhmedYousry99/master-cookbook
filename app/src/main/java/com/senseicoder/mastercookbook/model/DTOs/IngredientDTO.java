package com.senseicoder.mastercookbook.model.DTOs;

import com.google.gson.annotations.SerializedName;

public class IngredientDTO {

    @SerializedName(MealKeys.ID_INGREDIENT)
    private String id;
    @SerializedName(MealKeys.STR_INGREDIENT)
    private String ingredient;
    @SerializedName(MealKeys.STR_DESCRIPTION)
    private String description;

    public IngredientDTO(String id, String ingredient, String description) {
        this.id = id;
        this.ingredient = ingredient;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
