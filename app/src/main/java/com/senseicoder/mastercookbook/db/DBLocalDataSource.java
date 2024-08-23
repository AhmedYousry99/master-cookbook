package com.senseicoder.mastercookbook.db;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface DBLocalDataSource {

    Completable addMealIntoFavorite(MealSimplifiedModel meal);

    Completable deleteMealFromFavorite(MealSimplifiedModel meal);

    Single<List<MealSimplifiedModel>> getAllFavoriteMeals(String userId);

    Single<List<PlanDTO>> getPlanMeals(String userId);

    Completable addMealIntoPlan(PlanDTO plan);

    Completable deleteMealFromPlan(PlanDTO plan);
}
