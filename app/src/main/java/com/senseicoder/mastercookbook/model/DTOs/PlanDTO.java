package com.senseicoder.mastercookbook.model.DTOs;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.MealKeys;
import com.senseicoder.mastercookbook.model.UserKeys;
import com.senseicoder.mastercookbook.util.enums.WeekDays;

@Entity(tableName = "plan", primaryKeys = {"id", UserKeys.USER_ID})
public class PlanDTO {

    @NonNull
    @SerializedName("id")
    private String id;
    @SerializedName(MealKeys.DAY)
    private WeekDays days;
    @SerializedName(MealKeys.STR_MEAL)
    private String title;
    @SerializedName(MealKeys.STR_CATEGORY)
    private String category;
    @SerializedName(MealKeys.STR_DESCRIPTION)
    private String description;
    @SerializedName(MealKeys.STR_MEAL_THUMB)
    private String thumbnailUrl;
    @SerializedName(UserKeys.USER_ID)
    private String userId;


    public PlanDTO(@NonNull String id, WeekDays days, String title, String category, String description, String thumbnailUrl, String userId) {
        this.id = id;
        this.days = days;
        this.title = title;
        this.category = category;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.userId = userId;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setDays(WeekDays days) {
        this.days = days;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public WeekDays getDays() {
        return days;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
