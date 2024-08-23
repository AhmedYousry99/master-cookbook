package com.senseicoder.mastercookbook.features.main.ui.home.presenter;

import com.senseicoder.mastercookbook.features.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
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
        Single<List<CategoryDTO>> categoriesSingle = dataRepository.getCategories();
        compositeDisposable.add(categoriesSingle.observeOn(AndroidSchedulers.mainThread()).subscribe(categories -> homeView.updateCategoriesList(categories), e -> homeView.handleError(e.getMessage())));
    }

    @Override
    public void getMealsYouMightLike(String letter) {
        Single<List<MealDTO>> mealOfTheDaySingle = dataRepository.getMealsYouMightLike(letter);
        compositeDisposable.add(mealOfTheDaySingle.observeOn(AndroidSchedulers.mainThread()).subscribe(meals -> homeView.updateMealsYouMightLikeList(meals), e -> homeView.handleError(e.getMessage())));
    }

    @Override
    public void getMealOfTheDay() {
        Single<List<MealDTO>> mealOfTheDaySingle= dataRepository.getMealOfTheDay();
        compositeDisposable.add(mealOfTheDaySingle.observeOn(AndroidSchedulers.mainThread()).subscribe(meal -> homeView.updateMealOfTheDayList(meal.get(0)), e -> homeView.handleError(e.getMessage())));
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}
