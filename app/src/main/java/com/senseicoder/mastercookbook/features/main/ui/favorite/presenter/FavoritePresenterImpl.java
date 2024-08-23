package com.senseicoder.mastercookbook.features.main.ui.favorite.presenter;

import com.senseicoder.mastercookbook.features.main.ui.favorite.view.FavoriteView;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FavoritePresenterImpl implements FavoritePresenter{

    private final DataRepository dataRepository;
    private final FavoriteView favoriteView;
    private final CompositeDisposable compositeDisposable;

    public FavoritePresenterImpl(DataRepository dataRepository, FavoriteView favoriteView) {
        this.dataRepository = dataRepository;
        this.favoriteView = favoriteView;
        this.compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getFavorites() {
        compositeDisposable.add(dataRepository.getFavoriteMeals(dataRepository.getCurrentUser().getId()).observeOn(AndroidSchedulers.mainThread()).subscribe(favoriteView::updateList));
    }

    @Override
    public void deleteMeal(MealSimplifiedModel meal) {
        compositeDisposable.add(dataRepository.deleteMealFromFavorite(meal).observeOn(AndroidSchedulers.mainThread()).subscribe(favoriteView::onMealDeletedSuccess, favoriteView::onFailure));
    }

    @Override
    public void getMealData(MealSimplifiedModel meal) {
        compositeDisposable.add(dataRepository.getMealDetailsById(meal.getId()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                favoriteView::onGetMealDataSuccess,
                favoriteView::onFailure
        ));
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}
