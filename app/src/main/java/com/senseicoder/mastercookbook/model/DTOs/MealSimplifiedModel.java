package com.senseicoder.mastercookbook.model.DTOs;

public class MealSimplifiedModel {
    private String id;
    private String title;
    private String category;
    private String area;
    private String thumbnail;

    public static MealSimplifiedModel fromMeal(MealDTO mealDTO){
        return new MealSimplifiedModel(
                mealDTO.getId(),
                mealDTO.getTitle(),
                mealDTO.getCategory(),
                mealDTO.getArea(),
                mealDTO.getThumbnail()
        );
    }

    public MealSimplifiedModel(String id, String title, String category, String area, String thumbnail) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.area = area;
        this.thumbnail = thumbnail;
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

    public String getArea() {
        return area;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
