package com.senseicoder.mastercookbook.db.local;

import android.content.Context;
import android.util.Log;

import com.senseicoder.mastercookbook.db.DBLocalDataSource;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomLocalDateSourceImpl implements DBLocalDataSource {

    private static final String TAG = "RoomLocalDateSourceImpl";

    private final Context context;
    private final FoodDao foodDao;

    public static RoomLocalDateSourceImpl instance  = null;

    public static RoomLocalDateSourceImpl getInstance(Context _context) {
        if (instance == null)
            instance = new RoomLocalDateSourceImpl(_context);
        return instance;
    }

    private RoomLocalDateSourceImpl(Context _context) {
        context = _context;
        foodDao = AppDataBase.getInstance(context).getFoodDAO();
    }

    @Override
    public Single<List<MealSimplifiedModel>> getAllFavoriteMeals(String userId) {
        return foodDao.getFavouriteMeals(userId).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable addMealIntoFavorite(MealSimplifiedModel meal) {
        return foodDao.insertMealInFavorite(meal).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteMealFromFavorite(MealSimplifiedModel meal) {
        return foodDao.deleteMealFromFavorite(meal).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<PlanDTO>> getPlanMeals(String userId) {
        return foodDao.getPlanMeals(userId);
    }

    @Override
    public Completable addMealIntoPlan(PlanDTO plan) {
        return foodDao.insertMealInPlan(plan);
    }

    @Override
    public Completable deleteMealFromPlan(PlanDTO plan) {
        return foodDao.deleteMealFromPlan(plan);
    }

}
