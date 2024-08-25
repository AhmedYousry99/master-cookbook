package com.senseicoder.mastercookbook.features.meals.presenter;


import android.util.Log;

import com.senseicoder.mastercookbook.features.meals.view.MealsView;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.util.enums.SearchType;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsPresenterImpl implements MealsPresenter{

    private static final String TAG = "MealsPresenterImpl";

    MealsView mealsView;
    DataRepository dataRepository;
    CompositeDisposable compositeDisposable;


    public MealsPresenterImpl(MealsView mealsView, DataRepository dataRepository) {
        this.mealsView = mealsView;
        this.dataRepository = dataRepository;
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(mealsView.initiateNetworkObserver().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
           mealsView::handleConnection,
           mealsView::handleError
        ));
    }

    @Override
    public void getMeals(String word, SearchType type) {
        switch(type){
            case Country:

            compositeDisposable.add(dataRepository.getMealsByCountry(word).doOnSuccess(mealDTOS -> Log.d(TAG, "getMeals: " + mealDTOS.size())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    mealsView::updateList,
                    mealsView::handleError
            ));
                break;
            case Category:
                compositeDisposable.add(dataRepository.getMealsByCategory(word).doOnSuccess(mealDTOS -> Log.d(TAG, "getMeals: " + mealDTOS.size())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        mealsView::updateList,
                        mealsView::handleError
                ));
                break;
        }
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}
