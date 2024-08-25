package com.senseicoder.mastercookbook.model.DTOs;

import androidx.annotation.Nullable;

import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.List;

public class PlansModel{

    private List<PlanDTO> plans;
    private WeekDays day;

    public PlansModel(List<PlanDTO> plans, WeekDays day) {
        this.plans = plans;
        this.day = day;
    }

    public List<PlanDTO> getPlans() {
        return plans;
    }

    public WeekDays getDay() {
        return day;
    }

    public void addPlan(PlanDTO plan){
        plans.add(plan);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof PlansModel)
            return day.equals(((PlansModel) obj).day);
        return super.equals(obj);
    }

}
