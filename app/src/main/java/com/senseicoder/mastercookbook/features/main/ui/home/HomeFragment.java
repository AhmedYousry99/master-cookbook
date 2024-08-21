package com.senseicoder.mastercookbook.features.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.main.ui.home.adapters.HomeRecyclerAdapter;
import com.senseicoder.mastercookbook.features.main.ui.home.presenter.HomePresenter;
import com.senseicoder.mastercookbook.features.main.ui.home.presenter.HomePresenterImpl;
import com.senseicoder.mastercookbook.features.main.ui.home.view.HomeView;
import com.senseicoder.mastercookbook.features.main.ui.home.listeners.nestedRecyclerViewItemListeners;
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView, nestedRecyclerViewItemListeners{

    private HomePresenter presenter;
    private RecyclerView mainRecyclerView;
    private ProgressBar circularProgressBar;
    private ArrayList<HomeFragmentItem> list;
    private HomeRecyclerAdapter homeRecyclerAdapter;
    private List<CategoryDTO> categories;
    private List<CountryDTO> countries;
    private List<MealDTO> mealsYouMightLike;
    private MealDTO mealOfTheDay;

    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenterImpl(this, DataRepositoryImpl.getInstance(
                FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                RetrofitRemoteDataSourceImpl.getInstance(getActivity().getCacheDir())
        ));
        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        circularProgressBar = view.findViewById(R.id.homeCircularProgressBar);
        list = new ArrayList<>();
        categories = new ArrayList<>();
        countries = new ArrayList<>();
        mealsYouMightLike = new ArrayList<>();
        homeRecyclerAdapter = new HomeRecyclerAdapter(getContext(), list, this);
        mainRecyclerView.setAdapter(homeRecyclerAdapter);
        presenter.getMealOfTheDay();
        presenter.getCategories();
        presenter.getMealsYouMightLike("s");
    }

    private void setupHomeList(){
        list.clear();
        list.add(new HomeFragmentItem<>(HomeFragmentItem.MAIN_HEADER, null, 0, null, mealOfTheDay));
        list.add(new HomeFragmentItem<>(HomeFragmentItem.SECONDARY_HEADER, getString(R.string.countries_text), R.drawable.country_flag_ic, null, null));
        list.add(new HomeFragmentItem<>(HomeFragmentItem.COUNTRIES, null, 0, countries, null));
        list.add(new HomeFragmentItem(HomeFragmentItem.SECONDARY_HEADER, getString(R.string.categories_text), R.drawable.category_ic, null, null));
        list.add(new HomeFragmentItem<>(HomeFragmentItem.CATEGORIES, null, 0, categories, null));
        list.add(new HomeFragmentItem(HomeFragmentItem.SECONDARY_HEADER, getString(R.string.more_you_might_like_text), R.drawable.question_mark_ic, null, null));
        list.add(new HomeFragmentItem(HomeFragmentItem.MORE_YOU_MIGHT_LIKE, null, 0, mealsYouMightLike, null));
        homeRecyclerAdapter.setData(list);
    }


    @Override
    public void updateCategoriesList(List<CategoryDTO> categories) {
        this.categories = categories;
        if(mealOfTheDay!= null)
            setupHomeList();
    }

    @Override
    public void updateMealOfTheDayList(MealDTO mealDTO) {
        this.mealOfTheDay = mealDTO;
        circularProgressBar.setVisibility(View.INVISIBLE);
        mainRecyclerView.setVisibility(View.VISIBLE);
        setupHomeList();
    }

    @Override
    public void updateMealsYouMightLikeList(List<MealDTO> meals) {
        this.mealsYouMightLike = meals;
        if(mealOfTheDay!= null)
            setupHomeList();
    }

    @Override
    public void handleError(String message) {

    }

    @Override
    public void onCheckIngredientsClicked(String idMeal) {
        HomeFragmentDirections.ActionHomeFragmentToMealFragment action =
                HomeFragmentDirections.actionHomeFragmentToMealFragment(idMeal);
        Navigation.findNavController(getView()).navigate(action);

    }

    @Override
    public void onFavoriteClicked(String idMeal) {

    }

    @Override
    public void onGetMealsByCountryClicked(String country) {

    }

    @Override
    public void onGetMealsByCategoryClicked(String category) {

    }
}