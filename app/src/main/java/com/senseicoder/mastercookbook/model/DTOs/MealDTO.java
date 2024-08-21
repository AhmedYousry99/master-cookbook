package com.senseicoder.mastercookbook.model.DTOs;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MealDTO {
    @SerializedName(MealKeys.ID_MEAL)
    private String id;
    @SerializedName(MealKeys.STR_MEAL)
    private String title;
    @SerializedName(MealKeys.STR_CATEGORY)
    private String category;
    @SerializedName(MealKeys.STR_AREA)
    private String area;
    @SerializedName(MealKeys.STR_INSTRUCTIONS)
    private String instructions;
    @SerializedName(MealKeys.STR_MEAL_THUMB)
    private String thumbnail;
    @SerializedName(MealKeys.STR_YOUTUBE)
    private String youtube;
    @SerializedName(MealKeys.STR_INGREDIENTS1)
    private String ingredient1;
    @SerializedName(MealKeys.STR_INGREDIENTS2)
    private String ingredient2;
    @SerializedName(MealKeys.STR_INGREDIENTS3)
    private String ingredient3;
    @SerializedName(MealKeys.STR_INGREDIENTS4)
    private String ingredient4;
    @SerializedName(MealKeys.STR_INGREDIENTS5)
    private String ingredient5;
    @SerializedName(MealKeys.STR_INGREDIENTS6)
    private String ingredient6;
    @SerializedName(MealKeys.STR_INGREDIENTS7)
    private String ingredient7;
    @SerializedName(MealKeys.STR_INGREDIENTS8)
    private String ingredient8;
    @SerializedName(MealKeys.STR_INGREDIENTS9)
    private String ingredient9;
    @SerializedName(MealKeys.STR_INGREDIENTS10)
    private String ingredient10;
    @SerializedName(MealKeys.STR_INGREDIENTS11)
    private String ingredient11;
    @SerializedName(MealKeys.STR_INGREDIENTS12)
    private String ingredient12;
    @SerializedName(MealKeys.STR_INGREDIENTS13)
    private String ingredient13;
    @SerializedName(MealKeys.STR_INGREDIENTS14)
    private String ingredient14;
    @SerializedName(MealKeys.STR_INGREDIENTS15)
    private String ingredient15;
    @SerializedName(MealKeys.STR_INGREDIENTS16)
    private String ingredient16;
    @SerializedName(MealKeys.STR_INGREDIENTS17)
    private String ingredient17;
    @SerializedName(MealKeys.STR_INGREDIENTS18)
    private String ingredient18;
    @SerializedName(MealKeys.STR_INGREDIENTS19)
    private String ingredient19;
    @SerializedName(MealKeys.STR_INGREDIENTS20)
    private String ingredient20;
    @SerializedName(MealKeys.STR_MEASURE)
    private String measure;
    @SerializedName(MealKeys.STR_MEASURE2)
    private String measure2;
    @SerializedName(MealKeys.STR_MEASURE3)
    private String measure3;
    @SerializedName(MealKeys.STR_MEASURE4)
    private String measure4;
    @SerializedName(MealKeys.STR_MEASURE5)
    private String measure5;
    @SerializedName(MealKeys.STR_MEASURE6)
    private String measure6;
    @SerializedName(MealKeys.STR_MEASURE7)
    private String measure7;
    @SerializedName(MealKeys.STR_MEASURE8)
    private String measure8;
    @SerializedName(MealKeys.STR_MEASURE9)
    private String measure9;
    @SerializedName(MealKeys.STR_MEASURE10)
    private String measure10;
    @SerializedName(MealKeys.STR_MEASURE11)
    private String measure11;
    @SerializedName(MealKeys.STR_MEASURE12)
    private String measure12;
    @SerializedName(MealKeys.STR_MEASURE13)
    private String measure13;
    @SerializedName(MealKeys.STR_MEASURE14)
    private String measure14;
    @SerializedName(MealKeys.STR_MEASURE15)
    private String measure15;
    @SerializedName(MealKeys.STR_MEASURE16)
    private String measure16;
    @SerializedName(MealKeys.STR_MEASURE17)
    private String measure17;
    @SerializedName(MealKeys.STR_MEASURE18)
    private String measure18;
    @SerializedName(MealKeys.STR_MEASURE19)
    private String measure19;
    @SerializedName(MealKeys.STR_MEASURE20)
    private String measure20;
    @SerializedName(MealKeys.STR_SOURCE)
    private String source;


    public MealDTO(String title) {
        this.title = title;
    }

    public MealDTO(String id, String title, String category, String area, String instructions, String thumbnail, String youtube, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11, String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16, String ingredient17, String ingredient18, String ingredient19, String ingredient20, String measure, String measure2, String measure3, String measure4, String measure5, String measure6, String measure7, String measure8, String measure9, String measure10, String measure11, String measure12, String measure13, String measure14, String measure15, String measure16, String measure17, String measure18, String measure19, String measure20, String source) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
        this.youtube = youtube;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
        this.ingredient11 = ingredient11;
        this.ingredient12 = ingredient12;
        this.ingredient13 = ingredient13;
        this.ingredient14 = ingredient14;
        this.ingredient15 = ingredient15;
        this.ingredient16 = ingredient16;
        this.ingredient17 = ingredient17;
        this.ingredient18 = ingredient18;
        this.ingredient19 = ingredient19;
        this.ingredient20 = ingredient20;
        this.measure = measure;
        this.measure2 = measure2;
        this.measure3 = measure3;
        this.measure4 = measure4;
        this.measure5 = measure5;
        this.measure6 = measure6;
        this.measure7 = measure7;
        this.measure8 = measure8;
        this.measure9 = measure9;
        this.measure10 = measure10;
        this.measure11 = measure11;
        this.measure12 = measure12;
        this.measure13 = measure13;
        this.measure14 = measure14;
        this.measure15 = measure15;
        this.measure16 = measure16;
        this.measure17 = measure17;
        this.measure18 = measure18;
        this.measure19 = measure19;
        this.measure20 = measure20;
        this.source = source;
    }

    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        List<Field> fields = Arrays.stream(this.getClass().getDeclaredFields()).distinct().collect(Collectors.toList());

        for (Field field : fields) {
            if (field.getName().startsWith("ingredient")) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null && !value.isEmpty()) {
                        ingredients.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return ingredients;
    }

    public List<String> getMeasures() {
        List<String> ingredients = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().startsWith("measure")) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null && !value.isEmpty()) {
                        ingredients.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }

    public String getIngredient16() {
        return ingredient16;
    }

    public void setIngredient16(String ingredient16) {
        this.ingredient16 = ingredient16;
    }

    public String getIngredient17() {
        return ingredient17;
    }

    public void setIngredient17(String ingredient17) {
        this.ingredient17 = ingredient17;
    }

    public String getIngredient18() {
        return ingredient18;
    }

    public void setIngredient18(String ingredient18) {
        this.ingredient18 = ingredient18;
    }

    public String getIngredient19() {
        return ingredient19;
    }

    public void setIngredient19(String ingredient19) {
        this.ingredient19 = ingredient19;
    }

    public String getIngredient20() {
        return ingredient20;
    }

    public void setIngredient20(String ingredient20) {
        this.ingredient20 = ingredient20;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public String getMeasure7() {
        return measure7;
    }

    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }

    public String getMeasure9() {
        return measure9;
    }

    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }

    public String getMeasure10() {
        return measure10;
    }

    public void setMeasure10(String measure10) {
        this.measure10 = measure10;
    }

    public String getMeasure11() {
        return measure11;
    }

    public void setMeasure11(String measure11) {
        this.measure11 = measure11;
    }

    public String getMeasure12() {
        return measure12;
    }

    public void setMeasure12(String measure12) {
        this.measure12 = measure12;
    }

    public String getMeasure13() {
        return measure13;
    }

    public void setMeasure13(String measure13) {
        this.measure13 = measure13;
    }

    public String getMeasure14() {
        return measure14;
    }

    public void setMeasure14(String measure14) {
        this.measure14 = measure14;
    }

    public String getMeasure15() {
        return measure15;
    }

    public void setMeasure15(String measure15) {
        this.measure15 = measure15;
    }

    public String getMeasure16() {
        return measure16;
    }

    public void setMeasure16(String measure16) {
        this.measure16 = measure16;
    }

    public String getMeasure17() {
        return measure17;
    }

    public void setMeasure17(String measure17) {
        this.measure17 = measure17;
    }

    public String getMeasure18() {
        return measure18;
    }

    public void setMeasure18(String measure18) {
        this.measure18 = measure18;
    }

    public String getMeasure19() {
        return measure19;
    }

    public void setMeasure19(String measure19) {
        this.measure19 = measure19;
    }

    public String getMeasure20() {
        return measure20;
    }

    public void setMeasure20(String measure20) {
        this.measure20 = measure20;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
}
