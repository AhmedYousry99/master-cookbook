package com.senseicoder.mastercookbook.model.DTOs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.senseicoder.mastercookbook.model.MealKeys;
import com.senseicoder.mastercookbook.model.UserKeys;

import org.jetbrains.annotations.NotNull;


@Entity(tableName = "meal", primaryKeys = {"id", UserKeys.USER_ID})
public class MealSimplifiedModel {
    @NotNull
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = MealKeys.STR_MEAL)
    private String title;
    @ColumnInfo(name = MealKeys.STR_CATEGORY)
    private String category;
    @ColumnInfo(name = MealKeys.STR_AREA)
    private String country;
    @ColumnInfo(name = MealKeys.STR_MEAL_THUMB)
    private String thumbnailUrl;
    @NotNull
    @ColumnInfo(name = UserKeys.USER_ID)
    private String userId;
    @ColumnInfo(name = MealKeys.FAVORITE_SYNCED)
    private boolean favouriteSynced;


    public static MealSimplifiedModel fromMeal(MealDTO mealDTO){
        return new MealSimplifiedModel(
                mealDTO.getId(),
                mealDTO.getTitle(),
                mealDTO.getCategory(),
                mealDTO.getArea(),
                mealDTO.getThumbnail()
        );
    }

    public static MealSimplifiedModel fromMeal(MealDTO mealDTO, String userId){
        return new MealSimplifiedModel(
                mealDTO.getId(),
                mealDTO.getTitle(),
                mealDTO.getCategory(),
                mealDTO.getArea(),
                mealDTO.getThumbnail(),
                userId,
                false
        );
    }

    @Ignore
    public MealSimplifiedModel(String id, String title, String category, String country, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.country = country;
        this.thumbnailUrl = thumbnailUrl;
        this.favouriteSynced = true;
        this.userId = null;
    }

    public MealSimplifiedModel(@NotNull String id, String title, String category, String country, String thumbnailUrl, @NotNull String userId, @NotNull boolean favouriteSynced) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.country = country;
        this.thumbnailUrl = thumbnailUrl;
        this.userId = userId;
        this.favouriteSynced = favouriteSynced;
    }



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @NotNull
    public boolean isFavouriteSynced() {
        return favouriteSynced;
    }

    public @NotNull String getUserId() {
        return userId;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }

    public void setFavouriteSynced(@NotNull boolean favouriteSynced) {
        this.favouriteSynced = favouriteSynced;
    }
}
