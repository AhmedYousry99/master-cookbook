package com.senseicoder.mastercookbook.features.main.ui.plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.db.local.RoomLocalDateSourceImpl;
import com.senseicoder.mastercookbook.db.remote.FirebaseFirestoreRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.features.main.ui.plan.adapters.PlanRecyclerAdapter;
import com.senseicoder.mastercookbook.features.main.ui.plan.listeners.PlanRecyclerViewListeners;
import com.senseicoder.mastercookbook.features.main.ui.plan.presenter.PlanPresenter;
import com.senseicoder.mastercookbook.features.main.ui.plan.presenter.PlanPresenterImpl;
import com.senseicoder.mastercookbook.features.main.ui.plan.view.PlanView;
import com.senseicoder.mastercookbook.model.DTOs.DayPlansModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.model.repositories.DataRepositoryImpl;
import com.senseicoder.mastercookbook.network.RetrofitRemoteDataSourceImpl;
import com.senseicoder.mastercookbook.util.dialogs.ConfirmationDialog;
import com.senseicoder.mastercookbook.util.global.NetworkChangeObserver;

import io.reactivex.rxjava3.core.Observable;


public class PlanFragment extends Fragment implements PlanView, PlanRecyclerViewListeners {

    RecyclerView saterdayPlanRecyclerView;
    RecyclerView sundayPlanRecyclerView;
    RecyclerView mondayPlanRecyclerView;
    RecyclerView tuesdayPlanRecyclerView;
    RecyclerView wednesdayPlanRecyclerView;
    RecyclerView thursdayPlanRecyclerView;
    RecyclerView fridayPlanRecyclerView;
    PlanRecyclerAdapter saterdayAdapter;
    PlanRecyclerAdapter sundayAdapter;
    PlanRecyclerAdapter mondayAdapter;
    PlanRecyclerAdapter tuesdayAdapter;
    PlanRecyclerAdapter wednesdayAdapter;
    PlanRecyclerAdapter thursdayAdapter;
    PlanRecyclerAdapter fridayAdapter;
    ProgressBar planProgressBar;
    Group group;

    boolean synced;

    PlanPresenter presenter;
    private ConfirmationDialog confirmationDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saterdayPlanRecyclerView = view.findViewById(R.id.saterdayPlanRecyclerView);
        sundayPlanRecyclerView = view.findViewById(R.id.sundayPlanRecyclerView);
        mondayPlanRecyclerView = view.findViewById(R.id.mondayPlanRecyclerView);
        tuesdayPlanRecyclerView = view.findViewById(R.id.tuesdayPlanRecyclerView);
        wednesdayPlanRecyclerView = view.findViewById(R.id.wednesdayPlanRecyclerView);
        thursdayPlanRecyclerView = view.findViewById(R.id.thursdayPlanRecyclerView);
        fridayPlanRecyclerView = view.findViewById(R.id.fridayPlanRecyclerView);

        planProgressBar = view.findViewById(R.id.planProgressBar);
        group = view.findViewById(R.id.plansGroup);

        presenter = new PlanPresenterImpl(
                DataRepositoryImpl.getInstance(
                        FirebaseFirestoreRemoteDataSourceImpl.getInstance(),
                        RetrofitRemoteDataSourceImpl.getInstance(getActivity().getCacheDir()),
                        RoomLocalDateSourceImpl.getInstance(getContext())
                ),
                this
        );

        presenter.getPlans();
    }

    @Override
    public void updatePlansList(DayPlansModel dayPlans) {
        planProgressBar.setVisibility(View.GONE);
        group.setVisibility(View.VISIBLE);

        saterdayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getSaturdayPlans().getPlans(), this);
        saterdayPlanRecyclerView.setAdapter(saterdayAdapter);

        sundayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getSundayPlans().getPlans(), this);
        sundayPlanRecyclerView.setAdapter(sundayAdapter);

        mondayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getMondayPlans().getPlans(), this);
        mondayPlanRecyclerView.setAdapter(mondayAdapter);

        tuesdayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getTuesdayPlans().getPlans(), this);
        tuesdayPlanRecyclerView.setAdapter(tuesdayAdapter);

        wednesdayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getWednesdayPlans().getPlans(), this);
        wednesdayPlanRecyclerView.setAdapter(wednesdayAdapter);

        thursdayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getThursdayPlans().getPlans(), this);
        thursdayPlanRecyclerView.setAdapter(thursdayAdapter);

        fridayAdapter = new PlanRecyclerAdapter(getActivity(), dayPlans.getFridayPlans().getPlans(), this);
        fridayPlanRecyclerView.setAdapter(fridayAdapter);
    }

    @Override
    public void onDeletePlanSuccess(PlanDTO plan) {
        switch(plan.getDay()){
            case Saterday:
                saterdayAdapter.updateList(plan);
                break;
            case Sunday:
                sundayAdapter.updateList(plan);
                break;
            case Monday:
                mondayAdapter.updateList(plan);
                break;
            case Tuesday:
                tuesdayAdapter.updateList(plan);
                break;
            case Wednesday:
                wednesdayAdapter.updateList(plan);
                break;
            case Thursday:
                thursdayAdapter.updateList(plan);
                break;
            case Friday:
                fridayAdapter.updateList(plan);
                break;
        }
        Toast.makeText(getContext(), getString(R.string.plan_delete_success_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Observable<Boolean> initiateNetworkObserver() {
        return NetworkChangeObserver.observeNetworkConnectivity(getContext());
    }

    @Override
    public void handleConnection(boolean connected) {
        synced = false;
    }

    @Override
    public void handleError(Throwable e) {
        Toast.makeText(getContext(), getString(R.string.plan_delete_error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookmarkClicked(PlanDTO meal) {
        confirmationDialog = new ConfirmationDialog(() -> presenter.deleteMealFromPlan(meal), null, getActivity());
        confirmationDialog.setOnConfirmText(getString(R.string.favorite_delete_confirm_text));
        confirmationDialog.setOnCancelText(getString(R.string.favorite_delete_cancel_text));
        confirmationDialog.setMessage(getString(R.string.favorite_delete_message) + meal.getTitle());
        confirmationDialog.showDialog();
    }

    @Override
    public void onCheckIngredientsClicked(PlanDTO meal) {
        if(NetworkChangeObserver.isConnected(getContext())){
            PlanFragmentDirections.ActionPlanFragmentToMealFragment action =
                    PlanFragmentDirections.actionPlanFragmentToMealFragment(meal.getId());
            Navigation.findNavController(getView()).navigate(action);
        }else
            Toast.makeText(getContext(), getString(R.string.disconnected_text), Toast.LENGTH_SHORT).show();
    }
}