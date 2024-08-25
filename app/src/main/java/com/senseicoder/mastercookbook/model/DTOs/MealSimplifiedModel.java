package com.senseicoder.mastercookbook.model.DTOs;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.senseicoder.mastercookbook.model.MealKeys;
import com.senseicoder.mastercookbook.model.UserKeys;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


@Entity(tableName = "meal", primaryKeys = {"id", UserKeys.USER_ID})
public class MealSimplifiedModel implements Parcelable {
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


    protected MealSimplifiedModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        category = in.readString();
        country = in.readString();
        thumbnailUrl = in.readString();
        userId = in.readString();
        favouriteSynced = in.readByte() != 0;
    }

    public static final Creator<MealSimplifiedModel> CREATOR = new Creator<MealSimplifiedModel>() {
        @Override
        public MealSimplifiedModel createFromParcel(Parcel in) {
            return new MealSimplifiedModel(in);
        }

        @Override
        public MealSimplifiedModel[] newArray(int size) {
            return new MealSimplifiedModel[size];
        }
    };

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof MealSimplifiedModel) {
            MealSimplifiedModel that = (MealSimplifiedModel) o;
            return that.userId.equals(userId) && that.id.equals(id);
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    @NonNull
    @Override
    public String toString() {
        return title + ", " +
                country + ", "+
                category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(country);
        dest.writeString(thumbnailUrl);
        dest.writeString(userId);
        dest.writeByte((byte) (favouriteSynced ? 1 : 0));
    }
}
