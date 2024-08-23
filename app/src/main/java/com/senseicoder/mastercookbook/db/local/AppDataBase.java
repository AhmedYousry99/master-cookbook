package com.senseicoder.mastercookbook.db.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;

@Database(entities = {MealSimplifiedModel.class, PlanDTO.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract FoodDao getFoodDAO();

    public static synchronized AppDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"mydb2")
                    .build();
        }
        return instance;
    }
}
