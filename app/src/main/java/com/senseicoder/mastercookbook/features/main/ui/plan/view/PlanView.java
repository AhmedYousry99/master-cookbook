package com.senseicoder.mastercookbook.features.main.ui.plan.view;

import com.senseicoder.mastercookbook.model.DTOs.DayPlansModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;
import com.senseicoder.mastercookbook.util.enums.WeekDays;
import com.senseicoder.mastercookbook.util.interfaces.BaseView;

import java.util.List;

public interface PlanView extends BaseView {

    void updatePlansList(DayPlansModel dayPlans);

    void onDeletePlanSuccess(PlanDTO planDTO);

    void handleError(Throwable e);
}
