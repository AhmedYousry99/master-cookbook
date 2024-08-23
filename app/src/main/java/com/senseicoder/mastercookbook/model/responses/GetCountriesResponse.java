package com.senseicoder.mastercookbook.model.responses;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.MealKeys;

import java.util.List;

public class GetCountriesResponse {
    @SerializedName(MealKeys.MEALS)
    List<CountryDTO> country;

    public List<CountryDTO> getCountries() {
        return country;
    }
}
