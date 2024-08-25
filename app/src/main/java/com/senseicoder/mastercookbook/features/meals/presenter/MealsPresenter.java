package com.senseicoder.mastercookbook.features.meals.presenter;

import com.senseicoder.mastercookbook.util.enums.SearchType;

public interface MealsPresenter {

    void clear();

    void getMeals(String word, SearchType type);
}
