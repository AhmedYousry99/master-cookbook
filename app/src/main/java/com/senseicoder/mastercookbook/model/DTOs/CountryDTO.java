package com.senseicoder.mastercookbook.model.DTOs;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.MealKeys;

public class CountryDTO {

    @SerializedName(MealKeys.STR_AREA)
    private String name;

    private int countryFlag;

    public CountryDTO(String name, int countryFlag) {
        this.name = name;
        this.countryFlag = countryFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        this.countryFlag = countryFlag;
    }
}
