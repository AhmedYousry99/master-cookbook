package com.senseicoder.mastercookbook.model.repositories;

import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByEmailCallback;
import com.senseicoder.mastercookbook.db.remote.callbacks.GetUserByIdOrAddUserCallback;
import com.senseicoder.mastercookbook.model.DTOs.UserDTO;
import com.senseicoder.mastercookbook.network.callbacks.GetCategoriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetCountriesCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetIngredientsCallback;
import com.senseicoder.mastercookbook.network.callbacks.GetMealOfTheDayCallback;
import com.senseicoder.mastercookbook.util.callbacks.DatabaseCallback;

public interface DataRepository {

    void addUser(UserDTO userDTO, DatabaseCallback databaseCallback);

    void getUserByEmail(String email, GetUserByEmailCallback callback);

    void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback);

    UserDTO getCurrentUser();

    void getCategories(GetCategoriesCallback callback);

    void getCountries(GetCountriesCallback callback);

    void getIngredients(GetIngredientsCallback callback);

    void getMealOfTheDay(GetMealOfTheDayCallback callback);

    void setCurrentUser(UserDTO userDTO);
}
