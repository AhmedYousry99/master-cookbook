package com.senseicoder.mastercookbook.network.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;

import java.util.List;

public interface GetIngredientsCallback {
    public void onGetIngredientsSuccess(List<IngredientDTO> ingredient);

    public void onGetIngredientsFailure(String message);
}
