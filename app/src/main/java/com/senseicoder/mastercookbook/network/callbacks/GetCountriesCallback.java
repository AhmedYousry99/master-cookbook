package com.senseicoder.mastercookbook.network.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;

import java.util.List;

public interface GetCountriesCallback {

    public void onGetCountriesSuccess(List<CountryDTO> country);

    public void onGetCountriesFailure(String message);
}
