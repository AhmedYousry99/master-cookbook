package com.senseicoder.mastercookbook.features.meal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.meal.adapters.ingredientsAdapter;
import com.senseicoder.mastercookbook.features.meal.presenter.MealPresenter;
import com.senseicoder.mastercookbook.features.meal.presenter.MealPresenterImpl;
import com.senseicoder.mastercookbook.features.meal.view.MealView;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.dialogs.ChooseDayDialog;
import com.senseicoder.mastercookbook.util.dialogs.ConfirmationDialog;


public class MealFragment extends Fragment implements MealView {


    RecyclerView recyclerView;
    ingredientsAdapter ingredientsAdapter;
    MealPresenter presenter;
    TextView mealTitle;
    TextView mealDescription;
    ImageView mealImage;
    Group mealGroup;
    ProgressBar mealProgressBar;
    String mealId;

    ImageView mealFavouritebutton;
    ImageView mealPlanButton;

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
        mealFavouritebutton = view.findViewById(R.id.mealFavouritebutton);
        mealPlanButton = view.findViewById(R.id.mealPlanButton);

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
        ingredientsAdapter = new ingredientsAdapter(
                meal.getIngredients(),getContext()
        );
        mealFavouritebutton.setImageResource(meal.isFavorite() ? R.drawable.favorite_filled_ic : R.drawable.favorite_ic);
        mealFavouritebutton.setOnClickListener(v -> {
            if(!meal.isFavorite())
                presenter.addMealToFavorites(meal);
            else
            {
                ConfirmationDialog confirmationDialog = new ConfirmationDialog(() -> presenter.deleteMealFromFavorites(meal), null, getActivity());
                confirmationDialog.setMessage(getString(R.string.favorite_delete_message) + meal.getTitle());
                confirmationDialog.setOnConfirmText(getString(R.string.favorite_delete_confirm_text));
                confirmationDialog.setOnCancelText(getString(R.string.favorite_delete_cancel_text));
                confirmationDialog.showDialog();
            }
        });
        mealPlanButton.setImageResource(meal.isPlan() ? R.drawable.bookmark_filled_ic : R.drawable.bookmark_ic);
        mealPlanButton.setOnClickListener(v -> {
            if(!meal.isPlan())
            {
                ChooseDayDialog dialog = new ChooseDayDialog(getActivity());
                dialog.getConfirmButton().setOnClickListener(v1 ->{
                    presenter.addMealToPlans(meal, dialog.getSelectedDays());
                    dialog.dismissDialog();
                });
            }
        });

        recyclerView.setAdapter(ingredientsAdapter);
        mealProgressBar.setVisibility(View.INVISIBLE);
        mealGroup.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(meal.getThumbnail()).placeholder(R.drawable.food_photo).into(mealImage);
    }

    @Override
    public void onAddSuccess(MealDTO meal) {
        if(meal.isFavorite())
            mealFavouritebutton.setImageResource(R.drawable.favorite_filled_ic);
        if(meal.isPlan())
            mealPlanButton.setImageResource(R.drawable.bookmark_filled_ic);
        Toast.makeText(getContext(), getString(R.string.added_successfully_toast_text), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSuccess() {
        mealFavouritebutton.setImageResource(R.drawable.favorite_ic);
        Toast.makeText(getContext(), getString(R.string.deleted_successfully_toast_text), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable th) {
        Log.e(TAG, "onFailure: ", th);
        Toast.makeText(getContext(), th.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleError(Throwable th) {
        Log.e(TAG, "handleError: ", th);
        Toast.makeText(getContext(), th.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPermissionDenied() {
        Toast.makeText(getContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }
}