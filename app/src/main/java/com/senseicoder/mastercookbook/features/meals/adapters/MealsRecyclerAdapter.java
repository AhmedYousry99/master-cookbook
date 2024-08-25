package com.senseicoder.mastercookbook.features.meals.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.features.meals.listeners.MealsRecyclerViewListeners;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

import java.util.List;

public class MealsRecyclerAdapter extends RecyclerView.Adapter<MealsViewHolder>{

    private static final String TAG = "MealsRecyclerAdapter";

    List<MealDTO> meals;
    MealsRecyclerViewListeners listeners;
    Context context;

    public MealsRecyclerAdapter(List<MealDTO> meals, MealsRecyclerViewListeners listeners, Context context) {
        this.meals = meals;
        this.listeners = listeners;
        this.context = context;
    }

    public List<MealDTO> getMeals() {
        return meals;
    }

    public void updateList(List<MealDTO> meals){
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MealsViewHolder holder = new MealsViewHolder(LayoutInflater.from(context).inflate(R.layout.meals_list_tile_large, parent, false));
        Log.d(TAG, "onCreateViewHolder: holder position: " + holder.getAdapterPosition());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        MealDTO meal = meals.get(position);
        holder.getMealsCardView().setOnClickListener(v -> listeners.onMealClicked(meal));
        holder.getTitleTextView().setText(meal.getTitle());
        Glide.with(context).load(meal.getThumbnail()).placeholder(R.drawable.food_photo).into(holder.getBackgroundImageView());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}

class MealsViewHolder extends RecyclerView.ViewHolder{

    private ImageView backgroundImageView;
    private TextView TitleTextView;
    private CardView mealsCardView;

    public MealsViewHolder(@NonNull View itemView) {
        super(itemView);
        backgroundImageView = itemView.findViewById(R.id.mealsListTileCardBackgroundImageView);
        TitleTextView = itemView.findViewById(R.id.mealsListTilecardTitleTextView);
        mealsCardView = itemView.findViewById(R.id.mealsCardView);
    }

    public ImageView getBackgroundImageView() {
        return backgroundImageView;
    }

    public TextView getTitleTextView() {
        return TitleTextView;
    }

    public CardView getMealsCardView() {
        return mealsCardView;
    }
}
