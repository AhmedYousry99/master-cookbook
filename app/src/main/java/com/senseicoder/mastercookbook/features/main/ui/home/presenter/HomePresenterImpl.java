package com.senseicoder.mastercookbook.features.main.ui.home.presenter;

import com.senseicoder.mastercookbook.features.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;
    DataRepository dataRepository;
    CompositeDisposable compositeDisposable;

    public HomePresenterImpl(HomeView homeView, DataRepository dataRepository) {
        this.homeView = homeView;
        this.dataRepository = dataRepository;
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(homeView.initiateNetworkObserver().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                homeView::handleConnection,
                homeView::handleError
        ));
    }

    @Override
    public void getCategories() {
        compositeDisposable.add(dataRepository.getCategories().observeOn(AndroidSchedulers.mainThread()).subscribe(categories -> homeView.updateCategoriesList(categories), e -> homeView.handleError(e)));
    }

    @Override
    public void getMealsYouMightLike(String letter) {
        compositeDisposable.add(dataRepository.getMealsYouMightLike(letter).map(meals -> meals.stream().distinct().collect(Collectors.toList())).observeOn(AndroidSchedulers.mainThread()).subscribe(meals -> homeView.updateMealsYouMightLikeList(meals), e -> homeView.handleError(e)));
    }

    @Override
    public void getMealOfTheDay() {
        compositeDisposable.add(dataRepository.getMealOfTheDay().observeOn(AndroidSchedulers.mainThread()).subscribe(meal -> homeView.updateMealOfTheDayList(meal.get(0)), e -> homeView.handleError(e)));
    }

    @Override
    public void addMealToFavorite(MealDTO meal) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
            compositeDisposable.add(dataRepository.addLocalMealToFavorites(MealSimplifiedModel.fromMeal(meal, dataRepository.getCurrentUser().getId()))
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            () -> {
                                meal.setFavorite(true);
                                homeView.onMealAddedSuccess();
                            },
                            throwable -> homeView.handleError(throwable)
                    ));
        }else
            homeView.showPermissionDenied();
    }

    @Override
    public void deleteMealFromFavorite(MealDTO meal) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
        compositeDisposable.add(dataRepository.deleteLocalMealFromFavorite(MealSimplifiedModel.fromMeal(meal, dataRepository.getCurrentUser().getId())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> {
                    meal.setFavorite(false);
                    homeView.onMealRemovedSuccess();
                }
        ));}
        else
            homeView.showPermissionDenied();
    }

    @Override
    public void addMealToBookmark(MealDTO meal, List<WeekDays> days) {
        if(dataRepository.getCurrentUser().getDisplayName() != null){
            compositeDisposable.add(dataRepository.addLocalMealToMultipleDaysInPlan(PlanDTO.convertMultipleDaysMealIntoList(meal, days, dataRepository.getCurrentUser().getId())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    () -> homeView.onMealAddedSuccess(),
                    throwable -> homeView.handleError(throwable)
            ));
        }else
            homeView.showPermissionDenied();
    }


    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}
