package com.senseicoder.mastercookbook.util.global;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;

import java.util.ArrayList;

public class Constants{


    public static final String MEALDB_BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String INGREDIENTS_URL = "https://www.themealdb.com/images/ingredients/";

    public static ArrayList<CountryDTO> Countries;
    static {
        Countries = new ArrayList<>();
        Countries.add(new CountryDTO("American",R.drawable.america));
        Countries.add(new CountryDTO("British",R.drawable.british));
        Countries.add(new CountryDTO("Canadian",R.drawable.canada));
        Countries.add(new CountryDTO("Chinese",R.drawable.china));
        Countries.add(new CountryDTO("Croatian",R.drawable.croatian));
        Countries.add(new CountryDTO("Dutch",R.drawable.dutch));
        Countries.add(new CountryDTO("Egyptian",R.drawable.egypt));
        Countries.add(new CountryDTO("French",R.drawable.french));
        Countries.add(new CountryDTO("Greek",R.drawable.greek));
        Countries.add(new CountryDTO("Indian",R.drawable.indian));
        Countries.add(new CountryDTO("Irish",R.drawable.ireland));
        Countries.add(new CountryDTO("Italian",R.drawable.italian));
        Countries.add(new CountryDTO("Jamaican",R.drawable.jamaican));
        Countries.add(new CountryDTO("Japanese",R.drawable.japan));
        Countries.add(new CountryDTO("Kenyan",R.drawable.kenya));
        Countries.add(new CountryDTO("Malaysian",R.drawable.malaysian));
        Countries.add(new CountryDTO("Mexican",R.drawable.mexico));
        Countries.add(new CountryDTO("Moroccan",R.drawable.moroco));
        Countries.add(new CountryDTO("Polish",R.drawable.poland));
        Countries.add(new CountryDTO("Portuguese",R.drawable.portug));
        Countries.add(new CountryDTO("Russian",R.drawable.russian));
        Countries.add(new CountryDTO("Spanish",R.drawable.spani));
        Countries.add(new CountryDTO("Thai",R.drawable.thia));
        Countries.add(new CountryDTO("Tunisian",R.drawable.tunisian));
        Countries.add(new CountryDTO("Turkish",R.drawable.turcia));
        Countries.add(new CountryDTO("Unknown",R.drawable.unknown));
        Countries.add(new CountryDTO("Vietnamese",R.drawable.vietname));
    }

    private Constants(){}
}
