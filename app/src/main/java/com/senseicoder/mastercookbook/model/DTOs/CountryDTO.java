package com.senseicoder.mastercookbook.model.DTOs;

import com.google.gson.annotations.SerializedName;

public class CountryDTO {

    @SerializedName(MealKeys.STR_AREA)
    private String name;

    public CountryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
