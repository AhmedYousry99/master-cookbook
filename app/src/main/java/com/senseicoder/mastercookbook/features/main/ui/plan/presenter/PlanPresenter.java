package com.senseicoder.mastercookbook.features.main.ui.plan.presenter;

import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;

public interface PlanPresenter {

    void getPlans();

    void deleteMealFromPlan(PlanDTO planDTO);

    void clear();
}
