package com.senseicoder.mastercookbook.features.search_detailed.presenter;

import android.util.Log;

import com.senseicoder.mastercookbook.features.search_detailed.view.SearchDetailedView;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.IngredientDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.util.enums.SearchType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchDetailedPresenterImpl implements SearchDetailedPresenter {


    private final DataRepository dataRepository;
    private final SearchDetailedView searchDetailedView;
    private final CompositeDisposable compositeDisposable;

    private static final String TAG = "SearchDetailedPresenter";

    public SearchDetailedPresenterImpl(DataRepository dataRepository, SearchDetailedView searchDetailedView) {
        this.dataRepository = dataRepository;
        this.searchDetailedView = searchDetailedView;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getMealsBySearchWord(String word, SearchType searchType) {
        compositeDisposable.add(dataRepository.searchMeal(word, searchType).map(
                meals -> {
                    List<MealSimplifiedModel> mealsSimplified = new ArrayList<>();
                    for (MealDTO meal : meals) {
                        mealsSimplified.add(MealSimplifiedModel.fromMeal(meal));
                    }
                    return mealsSimplified;
                }
        ).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(mealSimplifiedModels -> Log.d(TAG, "getMealsBySearchWord: " + mealSimplifiedModels.size())).subscribe(
                mealSimplifiedModels -> {
                    searchDetailedView.updateMealsList(mealSimplifiedModels);
                },
                searchDetailedView::handleError
        ));
    }

    @Override
    public void getListByType(SearchType searchType) {
        List<String> list = new ArrayList<>();
        switch (searchType) {
            case Country:
                compositeDisposable.add(dataRepository.getCountries().observeOn(AndroidSchedulers.mainThread()).map(
                        countries -> {
                            for (CountryDTO country : countries) {
                                list.add(country.getName());
                            }
                            return list;
                        }
                ).subscribe(strings -> searchDetailedView.updateSearchList(list)));
                break;
            case Ingredient:
                compositeDisposable.add(dataRepository.getIngredients().observeOn(AndroidSchedulers.mainThread()).map(
                        ingredients -> {
                            for (IngredientDTO ingredient : ingredients) {
                                list.add(ingredient.getTitle());
                            }
                            return list;
                        }
                ).subscribe(strings -> searchDetailedView.updateSearchList(list)));
                break;
            case Category:
                compositeDisposable.add(dataRepository.getCategories().observeOn(AndroidSchedulers.mainThread()).map(
                        categories -> {
                            for (CategoryDTO category : categories) {
                                list.add(category.getTitle());
                            }
                            return list;
                        }
                ).subscribe(strings -> searchDetailedView.updateSearchList(list)));
                break;
            case Name:
                searchDetailedView.initTextObservable();
                break;
        }
    }

    @Override
    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void reloadState(List<MealSimplifiedModel> meals, SearchType searchType, List<String> suggestions) {
        if(searchType.equals(SearchType.Name))
            searchDetailedView.initTextObservable();
        else
            searchDetailedView.updateSearchList(suggestions);
        searchDetailedView.updateMealsList(meals);
    }

}
