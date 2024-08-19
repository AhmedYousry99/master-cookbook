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

    public void addUser(UserDTO userDTO, DatabaseCallback databaseCallback);

    public void getUserByEmail(String email, GetUserByEmailCallback callback);

    public void getUserByIdOrAddUser(UserDTO userDTO, GetUserByIdOrAddUserCallback callback);

    public UserDTO getCurrentUser();

    public void getCategories(GetCategoriesCallback callback);

    public void getCountries(GetCountriesCallback callback);

    public void getIngredients(GetIngredientsCallback callback);

    public void getMealOfTheDay(GetMealOfTheDayCallback callback);

    public void setCurrentUser(UserDTO userDTO);
}
