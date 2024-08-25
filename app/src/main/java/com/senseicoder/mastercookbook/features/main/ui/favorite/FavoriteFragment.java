package com.senseicoder.mastercookbook.features.main.ui.favorite;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.main.ui.favorite.adapters.FavoriteRecyclerAdapter;
import com.senseicoder.mastercookbook.features.main.ui.favorite.listeners.FavoriteRecyclerViewListeners;
import com.senseicoder.mastercookbook.features.main.ui.favorite.presenter.FavoritePresenter;
import com.senseicoder.mastercookbook.features.main.ui.favorite.presenter.FavoritePresenterImpl;
import com.senseicoder.mastercookbook.features.main.ui.favorite.view.FavoriteView;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.dialogs.ConfirmationDialog;
import com.senseicoder.mastercookbook.util.global.NetworkChangeObserver;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class FavoriteFragment extends Fragment implements FavoriteView, FavoriteRecyclerViewListeners {

    private FavoritePresenter presenter;
    private RecyclerView favoriteRecyclerView;
    private ProgressBar favoriteProgressBar;
    private FavoriteRecyclerAdapter favoriteRecyclerAdapter;
    private TextView favoriteListEmptyText;
    private ConfirmationDialog confirmationDialog;
    private boolean synced;

    List<MealSimplifiedModel> meals;


    private static final String TAG = "FavoriteFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavoritePresenterImpl(
                DataRepositoryImpl.getInstance(FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                        RetrofitRemoteDataSourceImpl.getInstance(getActivity().getCacheDir()),
                        RoomLocalDateSourceImpl.getInstance(getContext())),this
        );
        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView);
        favoriteProgressBar = view.findViewById(R.id.favoriteProgressBar);
        favoriteListEmptyText = view.findViewById(R.id.favoriteListEmptyText);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        favoriteRecyclerView.setLayoutManager(layoutManager);
        if(meals == null)
            presenter.getFavorites();
        else{
            updateList(meals);
        }
    }

    @Override
    public void updateList(List<MealSimplifiedModel> meals) {
        Log.d(TAG, "updateList: ");
        favoriteRecyclerAdapter = new FavoriteRecyclerAdapter(meals, getContext(), this);
        favoriteRecyclerView.setAdapter(favoriteRecyclerAdapter);
        favoriteProgressBar.setVisibility(View.GONE);
        favoriteRecyclerView.setVisibility(View.VISIBLE);
        this.meals = meals;
        if(meals.isEmpty())
            favoriteListEmptyText.setVisibility(View.VISIBLE);
        else
            favoriteListEmptyText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMealDeletedSuccess(MealSimplifiedModel meal) {
        favoriteRecyclerAdapter.UpdateDeletedMeal(meal);
        if(favoriteRecyclerAdapter.getMeals().isEmpty())
            favoriteListEmptyText.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "deleted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable e) {
        Log.e(TAG, "onFailure: ", e);
    }

    @Override
    public void onFavoriteClicked(MealSimplifiedModel meal) {
        Log.d(TAG, "onFavoriteClicked: " + meal);
        confirmationDialog = new ConfirmationDialog(() -> presenter.deleteMeal(meal), null,getActivity());
        confirmationDialog.setOnConfirmText(getString(R.string.favorite_delete_confirm_text));
        confirmationDialog.setOnCancelText(getString(R.string.favorite_delete_cancel_text));
        confirmationDialog.setMessage(getString(R.string.favorite_delete_message) + meal.getTitle());
        confirmationDialog.showDialog();
    }

    @Override
    public void onCheckIngredientsClicked(MealSimplifiedModel meal) {
        if(NetworkChangeObserver.isConnected(getContext())){
            FavoriteFragmentDirections.ActionFavoriteFragmentToMealFragment action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToMealFragment(meal.getId());
            Navigation.findNavController(getView()).navigate(action);
        }else
            Toast.makeText(getContext(), getString(R.string.disconnected_text), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }

    @Override
    public Observable<Boolean> initiateNetworkObserver() {
        return NetworkChangeObserver.observeNetworkConnectivity(getContext());
    }

    @Override
    public void handleConnection(boolean connected) {
        synced = connected;
    }

    @Override
    public void handleError(Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}