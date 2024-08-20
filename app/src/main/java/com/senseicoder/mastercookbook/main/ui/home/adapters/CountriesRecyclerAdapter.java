package com.senseicoder.mastercookbook.main.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.main.ui.home.listeners.nestedRecyclerViewItemListeners;
import com.senseicoder.mastercookbook.model.DTOs.CountryDTO;

import java.util.List;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesViewHolder> {

    List<Object> countries;
    Context context;
    nestedRecyclerViewItemListeners listener;

    public CountriesRecyclerAdapter(List countries, Context context, nestedRecyclerViewItemListeners listener) {
        this.countries = countries;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new CountriesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tile_small, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        CountryDTO country = (CountryDTO) countries.get(position);
        holder.getTitle().setText(country.getName());
        holder.getCardView().setOnClickListener(v -> listener.onGetMealsByCountryClicked(country.getName()));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}


class CountriesViewHolder extends RecyclerView.ViewHolder{
    private final TextView title;
    private final CardView cardView;

    public CountriesViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.smallListTitleTextView);
        this.cardView = itemView.findViewById(R.id.smallListCardView);
    }

    public TextView getTitle() {
        return title;
    }

    public CardView getCardView() {
        return cardView;
    }
}

