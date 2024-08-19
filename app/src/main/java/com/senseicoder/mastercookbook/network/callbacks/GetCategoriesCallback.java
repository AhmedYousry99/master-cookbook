package com.senseicoder.mastercookbook.network.callbacks;

import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;

import java.util.List;

public interface GetCategoriesCallback {
    public void onGetCategoriesSuccess(List<CategoryDTO> categories);

    public void onGetCategoriesFailure(String message);
}
