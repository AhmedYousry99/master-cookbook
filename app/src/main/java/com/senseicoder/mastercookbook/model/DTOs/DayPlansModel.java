package com.senseicoder.mastercookbook.model.DTOs;

import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.ArrayList;

public class DayPlansModel {

    private final PlansModel saturdayPlans;
    private final PlansModel sundayPlans;
    private final PlansModel mondayPlans;
    private final PlansModel tuesdayPlans;
    private final PlansModel wednesdayPlans;
    private final PlansModel thursdayPlans;
    private final PlansModel fridayPlans;

    public DayPlansModel() {
        saturdayPlans = new PlansModel(new ArrayList<>(), WeekDays.Saterday);
        sundayPlans = new PlansModel(new ArrayList<>(), WeekDays.Sunday);
        mondayPlans = new PlansModel(new ArrayList<>(), WeekDays.Monday);
        tuesdayPlans = new PlansModel(new ArrayList<>(), WeekDays.Tuesday);
        wednesdayPlans = new PlansModel(new ArrayList<>(), WeekDays.Wednesday);
        thursdayPlans = new PlansModel(new ArrayList<>(), WeekDays.Thursday);
        fridayPlans = new PlansModel(new ArrayList<>(), WeekDays.Friday);
    }

    public PlansModel getSaturdayPlans() {
        return saturdayPlans;
    }

    public PlansModel getSundayPlans() {
        return sundayPlans;
    }

    public PlansModel getMondayPlans() {
        return mondayPlans;
    }

    public PlansModel getTuesdayPlans() {
        return tuesdayPlans;
    }

    public PlansModel getWednesdayPlans() {
        return wednesdayPlans;
    }

    public PlansModel getThursdayPlans() {
        return thursdayPlans;
    }

    public PlansModel getFridayPlans() {
        return fridayPlans;
    }
}
