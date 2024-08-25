package com.senseicoder.mastercookbook.model.DTOs;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.MealKeys;
import com.senseicoder.mastercookbook.model.UserKeys;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(tableName = "plan", primaryKeys = {"id", MealKeys.DAY, UserKeys.USER_ID})
public class PlanDTO {

    @NonNull
    @SerializedName("id")
    private String id;
    @NonNull
    @SerializedName(MealKeys.DAY)
    private WeekDays day;
    @SerializedName(MealKeys.STR_MEAL)
    private String title;
    @SerializedName(MealKeys.STR_CATEGORY)
    private String category;
    @SerializedName(MealKeys.STR_MEAL_THUMB)
    private String thumbnailUrl;
    @SerializedName(MealKeys.FAVORITE)
    private boolean favorite;
    @NonNull
    @SerializedName(UserKeys.USER_ID)
    private String userId;


    public PlanDTO(@NonNull String id, WeekDays day, String title, String category, String thumbnailUrl, String userId, boolean favorite) {
        this.id = id;
        this.day = day;
        this.title = title;
        this.category = category;
        this.thumbnailUrl = thumbnailUrl;
        this.userId = userId;
        this.favorite = favorite;
    }

    public static List<PlanDTO> convertMultipleDaysMealIntoList(MealDTO meal, List<WeekDays> days, String userId) {
        List<PlanDTO> plans = new ArrayList<>();
        for(WeekDays day :days){
            plans.add(new PlanDTO(
                    meal.getId(),
                    day,
                    meal.getTitle(),
                    meal.getCategory(),
                    meal.getThumbnail(),
                    userId,
                    meal.isFavorite()
            ));
        }
        return plans;
    }


    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setDay(WeekDays day) {
        this.day = day;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public WeekDays getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof PlanDTO)
            return Objects.equals(((PlanDTO) o).id, id) && ((PlanDTO) o).day == day && ((PlanDTO) o).userId.equals(userId);
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, userId);
    }
}
