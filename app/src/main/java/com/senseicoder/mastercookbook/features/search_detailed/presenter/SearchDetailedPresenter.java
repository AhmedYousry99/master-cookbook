package com.senseicoder.mastercookbook.features.search_detailed.presenter;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.util.enums.SearchType;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public interface SearchDetailedPresenter {


    void getMealsBySearchWord(String word, SearchType searchType);

    void getListByType(SearchType type);

    void addDisposable(Disposable disposable);

    void clear();

    void reloadState(List<MealSimplifiedModel> meals, SearchType searchType, List<String> suggestions);
}