package com.senseicoder.mastercookbook.features.main.ui.favorite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.features.main.ui.favorite.listeners.FavoriteRecyclerViewListeners;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;

import java.util.List;

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<FavoriteViewHolder>{

    List<MealSimplifiedModel> meals;
    Context context;
    FavoriteRecyclerViewListeners listeners;

    public FavoriteRecyclerAdapter(List<MealSimplifiedModel> meals, Context context, FavoriteRecyclerViewListeners listeners) {
        this.meals = meals;
        this.context = context;
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_list_tile_large, parent, false));
        return favoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        MealSimplifiedModel meal = meals.get(position);
        holder.
                getFavoriteButton().
                setOnClickListener(v -> listeners.onFavoriteClicked(meal));
        holder.getCheckIngredientsButton().setOnClickListener(v -> listeners.onFavoriteClicked(meal));
        holder.getTitleTextView().setText(meal.getTitle());
        Glide.with(context).load(meal.getThumbnailUrl()).placeholder(R.drawable.food_photo).into(holder.getBackgroundImageView());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}

class FavoriteViewHolder extends RecyclerView.ViewHolder {
    private ImageButton favoriteButton;
    private Button checkIngredientsButton;
    private TextView titleTextView;
    private ImageView backgroundImageView;


    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);
        favoriteButton = itemView.findViewById(R.id.favoriteListTileCardFavoriteButton);
        checkIngredientsButton = itemView.findViewById(R.id.favoriteListTilecardCheckIngredientsButton);
        titleTextView = itemView.findViewById(R.id.favoriteListTilecardTitleTextView);
        backgroundImageView = itemView.findViewById(R.id.favoriteListTileCardBackgroundImageView);
    }

    public ImageButton getFavoriteButton() {
        return favoriteButton;
    }

    public Button getCheckIngredientsButton() {
        return checkIngredientsButton;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public ImageView getBackgroundImageView() {
        return backgroundImageView;
    }
}
