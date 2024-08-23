package com.senseicoder.mastercookbook.features.meal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.main.ui.home.HomeFragmentDirections;
import com.senseicoder.mastercookbook.features.meal.adapters.MealDetailsIngredientsRecyclerAdapter;
import com.senseicoder.mastercookbook.features.meal.presenter.MealPresenter;
import com.senseicoder.mastercookbook.features.meal.presenter.MealPresenterImpl;
import com.senseicoder.mastercookbook.features.meal.view.MealView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;


public class MealFragment extends Fragment implements MealView {


    RecyclerView recyclerView;
    MealDetailsIngredientsRecyclerAdapter mealDetailsIngredientsRecyclerAdapter;
    MealPresenter presenter;
    TextView mealTitle;
    TextView mealDescription;
    ImageView mealImage;
    Group mealGroup;
    ProgressBar mealProgressBar;
    String mealId;

    private static final String TAG = "MealFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealId = MealFragmentArgs.fromBundle(getArguments()).getMealId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mealIngredientsRecyclerView);
        mealTitle = view.findViewById(R.id.mealTitle);
        mealDescription = view.findViewById(R.id.mealDescription);
        mealImage = view.findViewById(R.id.mealImage);
        mealGroup = view.findViewById(R.id.mealGroup);
        mealProgressBar = view.findViewById(R.id.mealProgressBar);
        presenter = new MealPresenterImpl(DataRepositoryImpl.getInstance(
                FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                RetrofitRemoteDataSourceImpl.getInstance(getActivity().getCacheDir()),
                RoomLocalDateSourceImpl.getInstance(getContext())
        ),this);
        presenter.getMealData(mealId);
    }

    @Override
    public void updateMealData(MealDTO meal) {
        mealTitle.setText(String.format("%s %s", meal.getArea(), meal.getTitle()));
        mealDescription.setText(meal.getInstructions());
        mealDetailsIngredientsRecyclerAdapter = new MealDetailsIngredientsRecyclerAdapter(
                meal.getIngredients(),getContext()
        );
        recyclerView.setAdapter(mealDetailsIngredientsRecyclerAdapter);
        mealProgressBar.setVisibility(View.INVISIBLE);
        mealGroup.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(meal.getThumbnail()).placeholder(R.drawable.food_photo).into(mealImage);
    }

    @Override
    public void handleError(String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }
}