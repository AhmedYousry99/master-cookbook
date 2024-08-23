package com.senseicoder.mastercookbook.features.meal.presenter;

import com.senseicoder.mastercookbook.features.meal.view.MealView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealPresenterImpl implements MealPresenter{

    DataRepository dataRepository;
    MealView mealView;
    CompositeDisposable compositeDisposable;

    public MealPresenterImpl(DataRepository dataRepository, MealView mealView) {
        this.dataRepository = dataRepository;
        this.mealView = mealView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMealData(String mealId) {
        compositeDisposable.add(dataRepository.getMealDetailsById(mealId).observeOn(AndroidSchedulers.mainThread()).subscribe(mealDTO -> mealView.updateMealData(mealDTO),
                e -> mealView.handleError(e.getMessage())));
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}
