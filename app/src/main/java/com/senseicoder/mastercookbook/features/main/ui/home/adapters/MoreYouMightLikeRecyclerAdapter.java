package com.senseicoder.mastercookbook.features.main.ui.home.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.features.main.ui.home.listeners.nestedRecyclerViewItemListeners;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

import java.util.List;

public class MoreYouMightLikeRecyclerAdapter extends  RecyclerView.Adapter<MoreYouMightLikeViewHolder>{
    List<MealDTO> meals;
    Context context;
    nestedRecyclerViewItemListeners listener;
    private static final String TAG = "MoreYouMightLikeRecycle";

    public MoreYouMightLikeRecyclerAdapter(List meals, Context context, nestedRecyclerViewItemListeners listener) {
        this.meals = meals;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MoreYouMightLikeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        MoreYouMightLikeViewHolder holder = new MoreYouMightLikeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_tile_large, viewGroup, false), listener);
        int position = holder.getAdapterPosition();
        Log.d(TAG, "onCreateViewHolder: " + position);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoreYouMightLikeViewHolder holder, int position) {
        MealDTO meal = meals.get(position);
        holder.getTitleTextView().setText(meal.getTitle());
        holder.setupListeners(meal);
        holder.getFavoriteButton().setImageResource(meal.isFavorite() ? R.drawable.favorite_filled_ic : R.drawable.favorite_ic);
        Log.d(TAG, "onBindViewHolder: " + position);
        Glide.with(context).load(meal.getThumbnail()).placeholder(R.drawable.food_photo).into(holder.getBackgroundImageView());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}


class MoreYouMightLikeViewHolder extends RecyclerView.ViewHolder{
    private final ImageView favoriteButton;
    private final ImageView backgroundImageView;
    private final TextView titleTextView;
    private final Button checkIngredientsButton;
    private final nestedRecyclerViewItemListeners listener;

    public MoreYouMightLikeViewHolder(@NonNull View itemView, nestedRecyclerViewItemListeners listener) {
        super(itemView);
        favoriteButton = itemView.findViewById(R.id.homeListTileCardFavoriteButton);
        backgroundImageView = itemView.findViewById(R.id.smallListTileImageView);
        titleTextView = itemView.findViewById(R.id.homeListTilecardTitleTextView);
        checkIngredientsButton = itemView.findViewById(R.id.homeListTilecardCheckIngredientsButton);
        this.listener = listener;
    }

    public void setupListeners(MealDTO meal) {
        favoriteButton.setOnClickListener(
                v -> listener.onFavoriteClicked(meal)
        );
        checkIngredientsButton.setOnClickListener(
                v -> listener.onCheckIngredientsClicked(meal.getId())
        );
    }

    public ImageView getFavoriteButton() {
        return favoriteButton;
    }

    public ImageView getBackgroundImageView() {
        return backgroundImageView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public Button getCheckIngredientsButton() {
        return checkIngredientsButton;
    }
}