package com.senseicoder.mastercookbook.db.local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.model.UserKeys;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FoodDao {

    @Query("SELECT * From meal WHERE " + UserKeys.USER_ID + "= :userId")
    Single<List<MealSimplifiedModel>> getFavouriteMeals(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealInFavorite(MealSimplifiedModel meal);

    @Delete
    Completable deleteMealFromFavorite(MealSimplifiedModel meal);

    @Query("SELECT * From plan WHERE " + UserKeys.USER_ID + "= :userId")
    Single<List<PlanDTO>> getPlanMeals(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealInPlan(PlanDTO plan);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMultipleMealsIntoPlan(List<PlanDTO> plans);

    @Delete
    Completable deleteMealFromPlan(PlanDTO plan);
}
