package com.senseicoder.mastercookbook.model.DTOs;

import com.google.gson.annotations.SerializedName;
import com.senseicoder.mastercookbook.model.MealKeys;

import java.util.Objects;

public class CategoryDTO {

    @SerializedName(MealKeys.STR_CATEGORY)
    private String title;
    @SerializedName(MealKeys.ID_CATEGORY)
    private String id;
    @SerializedName(MealKeys.STR_CATEGORY_THUMB)
    private String thumbnail;
    @SerializedName(MealKeys.STR_CATEGORY_DESCRIPTION)
    private String description;

    public CategoryDTO(String title, String id, String thumbnail, String description) {
        this.title = title;
        this.id = id;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO)) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
