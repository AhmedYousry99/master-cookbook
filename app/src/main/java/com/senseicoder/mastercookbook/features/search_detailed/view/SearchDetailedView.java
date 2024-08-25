package com.senseicoder.mastercookbook.features.search_detailed.view;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;

import java.util.List;

public interface SearchDetailedView {

    void updateMealsList(List<MealSimplifiedModel> meals);

    void updateSearchList(List<String> titles);

    void handleError(Throwable throwable);


    void initTextObservable();
}
