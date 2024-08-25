package com.senseicoder.mastercookbook.features.main.ui.plan.presenter;

import com.senseicoder.mastercookbook.features.main.ui.plan.view.PlanView;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.model.DTOs.DayPlansModel;
import com.senseicoder.mastercookbook.model.repositories.DataRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class PlanPresenterImpl implements PlanPresenter {

    PlanView planView;
    CompositeDisposable compositeDisposable;
    DataRepository dataRepository;


    public PlanPresenterImpl(DataRepository dataRepository, PlanView planView) {
        this.dataRepository = dataRepository;
        this.planView = planView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getPlans() {
        compositeDisposable.add(dataRepository.getLocalUserPlan(dataRepository.getCurrentUser().getId()).map(
                this::propagatePlansWithModelList
        ).observeOn(AndroidSchedulers.mainThread()).subscribe(planDTOS -> planView.updatePlansList(planDTOS)));
    }

    @Override
    public void deleteMealFromPlan(PlanDTO planDTO) {
        planDTO.setUserId(dataRepository.getCurrentUser().getId());
        compositeDisposable.add(dataRepository.deleteLocalMealFromPlan(planDTO).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> planView.onDeletePlanSuccess(planDTO)));
    }

    private DayPlansModel propagatePlansWithModelList(List<PlanDTO> plans) {
        DayPlansModel dayPlans = new DayPlansModel();
        for (PlanDTO plan : plans) {
            switch (plan.getDay()) {
                case Saterday:
                    dayPlans.getSaturdayPlans().addPlan(plan);
                    break;
                case Sunday:
                    dayPlans.getSundayPlans().addPlan(plan);
                    break;
                case Monday:
                    dayPlans.getMondayPlans().addPlan(plan);
                    break;
                case Tuesday:
                    dayPlans.getTuesdayPlans().addPlan(plan);
                    break;
                case Wednesday:
                    dayPlans.getWednesdayPlans().addPlan(plan);
                    break;
                case Thursday:
                    dayPlans.getThursdayPlans().addPlan(plan);
                    break;
                case Friday:
                    dayPlans.getFridayPlans().addPlan(plan);
                    break;
            }
        }
        return dayPlans;
    }


    @Override
    public void clear() {
        compositeDisposable.clear();
    }

}
