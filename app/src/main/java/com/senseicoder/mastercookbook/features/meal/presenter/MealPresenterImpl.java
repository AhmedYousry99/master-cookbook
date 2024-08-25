package com.senseicoder.mastercookbook.features.meal.presenter;

import android.util.Log;

import com.senseicoder.mastercookbook.features.meal.view.MealView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MealPresenterImpl implements MealPresenter{

    DataRepository dataRepository;
    MealView mealView;
    CompositeDisposable compositeDisposable;

    private static final String TAG = "MealPresenterImpl";

    public MealPresenterImpl(DataRepository dataRepository, MealView mealView) {
        this.dataRepository = dataRepository;
        this.mealView = mealView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMealData(String mealId) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
            compositeDisposable.add(dataRepository.getMealDetailsById(mealId).zipWith(dataRepository.getLocalFavoriteMeals(dataRepository.getCurrentUser().getId()), (mealDTO, mealSimplifiedModels) -> {
                mealDTO.setFavorite(mealSimplifiedModels.stream().anyMatch(model -> model.getId().equals(mealDTO.getId())));
                Log.d(TAG, "getMealData: " + mealDTO.isFavorite());
                return mealDTO;
            }).zipWith(dataRepository.getLocalUserPlan(dataRepository.getCurrentUser().getId()), (mealDTO, planDTOS) -> {
                mealDTO.setPlan(planDTOS.stream().anyMatch(planDTO -> planDTO.getId().equals(mealDTO.getId())));
                return mealDTO;
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(mealDTO -> mealView.updateMealData(mealDTO),
                    e -> mealView.handleError(e)));
        }else{
            compositeDisposable.add((dataRepository.getMealDetailsById(mealId).observeOn(AndroidSchedulers.mainThread())).subscribe(
                    mealView::updateMealData,
                    mealView::handleError
            ));
        }
    }


    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void addMealToFavorites(MealDTO meal) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
            compositeDisposable.add(dataRepository.addLocalMealToFavorites(MealSimplifiedModel.fromMeal(meal, dataRepository.getCurrentUser().getId())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    () -> {
                        meal.setFavorite(true);
                        mealView.onAddSuccess(meal);
                    },
                    mealView::onFailure
            ));
        } else
            mealView.showPermissionDenied();
    }

    @Override
    public void deleteMealFromFavorites(MealDTO meal) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
            compositeDisposable.add(dataRepository.deleteLocalMealFromFavorite(MealSimplifiedModel.fromMeal(meal, dataRepository.getCurrentUser().getId())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    () -> {
                        mealView.onDeleteSuccess();
                        meal.setFavorite(false);
                    },
                    mealView::onFailure
            ));
        }else
            mealView.showPermissionDenied();
    }

    @Override
    public void addMealToPlans(MealDTO meal, List<WeekDays> days) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
            compositeDisposable.add(dataRepository.addLocalMealToMultipleDaysInPlan(PlanDTO.convertMultipleDaysMealIntoList(meal, days, dataRepository.getCurrentUser().getId())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    () -> {
                        meal.setPlan(true);
                        mealView.onAddSuccess(meal);
                    },
                    mealView::onFailure
            ));
        }else
            mealView.showPermissionDenied();
    }
}
