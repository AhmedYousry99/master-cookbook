package com.senseicoder.mastercookbook.features.main.ui.home.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.features.main.ui.home.listeners.nestedRecyclerViewItemListeners;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;
import com.senseicoder.mastercookbook.util.global.Constants;

import java.util.List;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesViewHolder> {

    private static final String TAG = "CountriesRecyclerAdapte";

    List<CountryDTO> countries;
    Context context;
    nestedRecyclerViewItemListeners listener;

    public CountriesRecyclerAdapter(Context context, nestedRecyclerViewItemListeners listener) {
        this.context = context;
        this.listener = listener;
        this.countries = Constants.Countries;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
         CountriesViewHolder viewHolder = new CountriesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_country_list_tile_small, viewGroup, false));
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        CountryDTO country = countries.get(position);
        holder.getCardView().setOnClickListener(v -> listener.onGetMealsByCountryClicked(country.getName()));
        Log.d(TAG, "onBindViewHolder: resource ID:" + country.getCountryFlag());
        Glide.with(context).load(country.getCountryFlag()).override(200, 200).into(holder.getBackgroundImage());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}


class CountriesViewHolder extends RecyclerView.ViewHolder{
    private final ImageView backgroundImage;
    private final CardView cardView;

    public CountriesViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cardView = itemView.findViewById(R.id.homeCountrySmallListCardView);
        this.backgroundImage = itemView.findViewById(R.id.homeCountrySmallListTileImageView);
    }


    public ImageView getBackgroundImage() {
        return backgroundImage;
    }

    public CardView getCardView() {
        return cardView;
    }
}

