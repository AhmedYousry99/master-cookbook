package com.senseicoder.mastercookbook.features.meal.presenter;

import com.senseicoder.mastercookbook.features.meal.view.MealView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealPresenterImpl implements MealPresenter{

    DataRepository dataRepository;
    MealView mealView;

    public MealPresenterImpl(DataRepository dataRepository, MealView mealView) {
        this.dataRepository = dataRepository;
        this.mealView = mealView;

    }

    @Override
    public void getMealData(String mealId) {
        dataRepository.getMealDetailsById(mealId).subscribe(new SingleObserver<MealDTO>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealDTO mealDTO) {
                mealView.updateMealData(mealDTO);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mealView.handleError(e.getMessage());
            }
        });
    }
}
