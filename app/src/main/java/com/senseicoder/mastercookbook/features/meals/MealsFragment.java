package com.senseicoder.mastercookbook.features.meals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.meals.adapters.MealsRecyclerAdapter;
import com.senseicoder.mastercookbook.features.meals.listeners.MealsRecyclerViewListeners;
import com.senseicoder.mastercookbook.features.meals.presenter.MealsPresenter;
import com.senseicoder.mastercookbook.features.meals.presenter.MealsPresenterImpl;
import com.senseicoder.mastercookbook.features.meals.view.MealsView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.enums.SearchType;
import com.senseicoder.mastercookbook.util.global.NetworkChangeObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class MealsFragment extends Fragment implements MealsView, MealsRecyclerViewListeners {


    private static final String TAG = "MealsFragment";

    ProgressBar mealsProgressBar;
    TextView mealsConnectionText;
    RecyclerView mealsRecyclerView;

    MealsPresenter presenter;
    MealsRecyclerAdapter adapter;

    String word;
    SearchType type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        word = MealsFragmentArgs.fromBundle(getArguments()).getWord();
        type = MealsFragmentArgs.fromBundle(getArguments()).getType();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsProgressBar = view.findViewById(R.id.mealsProgressBar);
        mealsConnectionText = view.findViewById(R.id.mealsConnectionText);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);

        presenter = new MealsPresenterImpl(
                this, DataRepositoryImpl.getInstance(
                FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                RetrofitRemoteDataSourceImpl.getInstance(getContext().getCacheDir()),
                RoomLocalDateSourceImpl.getInstance(getContext()))
        );
        adapter = new MealsRecyclerAdapter(new ArrayList<>(), this, getContext());
        mealsRecyclerView.setAdapter(adapter);
    }


    @Override
    public Observable<Boolean> initiateNetworkObserver() {
        return NetworkChangeObserver.observeNetworkConnectivity(getContext());
    }

    @Override
    public void handleConnection(boolean connected) {
        if(connected) {
            Log.d(TAG, "handleConnection: " + connected);
            mealsConnectionText.setVisibility(View.GONE);
            if(adapter.getMeals().isEmpty()) {
                mealsProgressBar.setVisibility(View.VISIBLE);
                presenter.getMeals(word, type);
            }else{
                mealsRecyclerView.setVisibility(View.VISIBLE);
                mealsProgressBar.setVisibility(View.INVISIBLE);
            }
        }else{
            mealsRecyclerView.setVisibility(View.GONE);
            mealsProgressBar.setVisibility(View.INVISIBLE);
            mealsConnectionText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void handleError(Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(List<MealDTO> meals) {
        mealsProgressBar.setVisibility(View.INVISIBLE);
        mealsRecyclerView.setVisibility(View.VISIBLE);
        Log.d(TAG, "updateList: " + meals.size());
        adapter.updateList(meals);
    }

    @Override
    public void onMealClicked(MealDTO meal) {
        if(NetworkChangeObserver.isConnected(getContext()))
        {
            MealsFragmentDirections.ActionMealsFragmentToMealFragment action =
                    MealsFragmentDirections.actionMealsFragmentToMealFragment(meal.getId());
            Navigation.findNavController(getView()).navigate(action);
        }else
            Toast.makeText(getContext(), getString(R.string.disconnected_text), Toast.LENGTH_SHORT).show();
    }

}