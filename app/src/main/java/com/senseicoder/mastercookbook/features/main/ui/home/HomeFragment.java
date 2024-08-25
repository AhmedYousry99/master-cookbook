package com.senseicoder.mastercookbook.features.main.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
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
import com.senseicoder.mastercookbook.util.dialogs.ChooseDayDialog;
import com.senseicoder.mastercookbook.util.enums.SearchType;
import com.senseicoder.mastercookbook.util.global.NetworkChangeObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class HomeFragment extends Fragment implements HomeView, nestedRecyclerViewItemListeners {

    private static final String TAG = "HomeFragment";

    private RecyclerView mainRecyclerView;
    private ProgressBar circularProgressBar;
    private HomeRecyclerAdapter homeRecyclerAdapter;
    private TextView connectivityText;
    private ArrayList<HomeFragmentItem> list;

    private MealDTO mealOfTheDay;
    private List<CategoryDTO> categories;
    private List<CountryDTO> countries;
    private List<MealDTO> mealsYouMightLike;

    private HomePresenter presenter;

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
                RetrofitRemoteDataSourceImpl.getInstance(getActivity().getCacheDir()),
                RoomLocalDateSourceImpl.getInstance(getContext())
        ));

        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        circularProgressBar = view.findViewById(R.id.homeCircularProgressBar);
        connectivityText = view.findViewById(R.id.connectivityText);

        list = new ArrayList<>();
        categories = new ArrayList<>();
        countries = new ArrayList<>();
        mealsYouMightLike = new ArrayList<>();
        homeRecyclerAdapter = new HomeRecyclerAdapter(getContext(), list, this);
        mainRecyclerView.setAdapter(homeRecyclerAdapter);

    }


    @Override
    public void updateCategoriesList(List<CategoryDTO> categories) {
        this.categories = categories;
        if (mealOfTheDay != null)
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
        if (mealOfTheDay != null)
            setupHomeList();
    }

    @Override
    public void onMealRemovedSuccess() {
        Toast.makeText(getContext(), "meal removed successfully", Toast.LENGTH_SHORT).show();
        homeRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealAddedSuccess() {
        Toast.makeText(getContext(), "meal added successfully", Toast.LENGTH_SHORT).show();
        homeRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPermissionDenied() {
        Toast.makeText(getContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Observable<Boolean> initiateNetworkObserver() {
        return NetworkChangeObserver.observeNetworkConnectivity(getContext());
    }

    @Override
    public void handleConnection(boolean connected) {
        Log.d(TAG, "handleConnection: is: " + connected);
        if (connected) {
            mainRecyclerView.setVisibility(View.VISIBLE);
            circularProgressBar.setVisibility(View.VISIBLE);
            connectivityText.setVisibility(View.GONE);
            if (mealOfTheDay == null)
                presenter.getMealOfTheDay();
            if (categories.isEmpty())
                presenter.getCategories();
            if (mealsYouMightLike.isEmpty())
                presenter.getMealsYouMightLike("a");

        } else {
            mainRecyclerView.setVisibility(View.GONE);
            circularProgressBar.setVisibility(View.INVISIBLE);
            connectivityText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void handleError(Throwable e) {
        Log.e(TAG, "handleError: ", e);
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckIngredientsClicked(String idMeal) {
        if (NetworkChangeObserver.isConnected(getContext())) {
            HomeFragmentDirections.ActionHomeFragmentToMealFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealFragment(idMeal);
            Navigation.findNavController(getView()).navigate(action);
        } else {
            Toast.makeText(getContext(), getString(R.string.disconnected_text), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFavoriteClicked(MealDTO meal) {
        Log.d(TAG, "onFavoriteClicked: " + meal.getTitle());
        if (meal.isFavorite())
            presenter.deleteMealFromFavorite(meal);
        else {
            presenter.addMealToFavorite(meal);
        }
    }

    @Override
    public void onGetMealsByCountryClicked(String country) {
        if (NetworkChangeObserver.isConnected(getContext())) {
            HomeFragmentDirections.ActionHomeFragmentToMealsFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealsFragment(country, SearchType.Country);
            Navigation.findNavController(getView()).navigate(action);
        } else {
            Toast.makeText(getContext(), getString(R.string.disconnected_text), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetMealsByCategoryClicked(String category) {
        if (NetworkChangeObserver.isConnected(getContext())) {
            HomeFragmentDirections.ActionHomeFragmentToMealsFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealsFragment(category, SearchType.Category);
            Navigation.findNavController(getView()).navigate(action);
        } else {
            Toast.makeText(getContext(), getString(R.string.disconnected_text), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBookmarkClicked(MealDTO meal) {
        ChooseDayDialog dialog = new ChooseDayDialog(getActivity());
        dialog.getConfirmButton().setOnClickListener(
                v -> {
                    presenter.addMealToBookmark(meal, dialog.getSelectedDays());
                    dialog.dismissDialog();
                }
        );
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    private void setupHomeList() {
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


}