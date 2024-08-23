package com.senseicoder.mastercookbook.features.main.ui.home.presenter;

import com.senseicoder.mastercookbook.features.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;
    DataRepository dataRepository;
    CompositeDisposable compositeDisposable;

    public HomePresenterImpl(HomeView homeView, DataRepository dataRepository) {
        this.homeView = homeView;
        this.dataRepository = dataRepository;
        compositeDisposable = new CompositeDisposable();
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
        compositeDisposable.add(dataRepository.addMealToFavorites(MealSimplifiedModel.fromMeal(meal, dataRepository.getCurrentUser().getId()))
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> {
                    meal.setFavorite(true);
                    homeView.onMealAddedSuccess();
                },
                throwable -> homeView.handleError(throwable)
        ));
    }

    @Override
    public void deleteMealFromFavorite(MealDTO meal) {
        compositeDisposable.add(dataRepository.deleteMealFromFavorite(MealSimplifiedModel.fromMeal(meal, dataRepository.getCurrentUser().getId())).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> {
                    meal.setFavorite(false);
                    homeView.onMealRemovedSuccess();
                }
        ));
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}
