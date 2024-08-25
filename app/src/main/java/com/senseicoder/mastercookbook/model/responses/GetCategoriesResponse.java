package com.senseicoder.mastercookbook.model.responses;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.MealKeys;

import java.util.List;

public class GetCategoriesResponse {
    @SerializedName(MealKeys.CATEGORIES)
    List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
