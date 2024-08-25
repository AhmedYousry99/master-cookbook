package com.senseicoder.mastercookbook.model.DTOs;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.MealKeys;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryDTO)) return false;
        CountryDTO that = (CountryDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
