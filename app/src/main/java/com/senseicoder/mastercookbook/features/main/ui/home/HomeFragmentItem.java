package com.senseicoder.mastercookbook.features.main.ui.home;


import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

import java.util.List;

public class HomeFragmentItem<T>{

    public static final int MAIN_HEADER = 0;
    public static final int SECONDARY_HEADER = 1;
    public static final int CATEGORIES = 2;
    public static final int COUNTRIES = 3;
    public static final int MORE_YOU_MIGHT_LIKE = 4;

    private int type;
    private String title;
    private int iconResourceId;
    private List<T> list;

    private MealDTO meal;



    public HomeFragmentItem(int type, String title, int iconResourceId, List<T> list, MealDTO meal) {
        this.type = type;
        this.title = title;
        this.iconResourceId = iconResourceId;
        this.list = list;
        this.meal = meal;
    }

    public String getTitle() {
        return title;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public List<T> getList() {
        return list;
    }

    public int getType() {
        return type;
    }

    public MealDTO getMeal() {
        return meal;
    }

}
