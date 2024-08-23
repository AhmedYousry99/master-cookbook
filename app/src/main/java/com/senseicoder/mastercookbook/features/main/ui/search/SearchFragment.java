package com.senseicoder.mastercookbook.features.main.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.senseicoder.mastercookbook.R;

import com.senseicoder.mastercookbook.util.enums.SearchType;

public class SearchFragment extends Fragment {

    Button searchByMealButton;
    Button searchByCategoryButton;
    Button searchByIngredientButton;
    Button searchByCountryButton;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchByMealButton = view.findViewById(R.id.searchByMealButton);
        searchByCategoryButton = view.findViewById(R.id.searchByCategoryButton);
        searchByIngredientButton = view.findViewById(R.id.searchByIngredientButton);
        searchByCountryButton = view.findViewById(R.id.searchByCountryButton);
        searchByMealButton.setOnClickListener(v -> handleButtonClick(SearchType.Name));
                searchByCategoryButton.setOnClickListener(v -> handleButtonClick(SearchType.Category));
        searchByIngredientButton.setOnClickListener(v -> handleButtonClick(SearchType.Ingredient));
                searchByCountryButton.setOnClickListener(v -> handleButtonClick(SearchType.Country));
    }

    private void handleButtonClick(SearchType searchType){
        SearchFragmentDirections.ActionSearchFragmentToSearchDetailedFragment action =
                SearchFragmentDirections.actionSearchFragmentToSearchDetailedFragment(searchType);
        Navigation.findNavController(getView()).navigate(action);
    }
}