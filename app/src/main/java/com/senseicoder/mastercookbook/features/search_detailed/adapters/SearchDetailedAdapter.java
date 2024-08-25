package com.senseicoder.mastercookbook.features.search_detailed.adapters;

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
import com.senseicoder.mastercookbook.features.search_detailed.listeners.SearchDetailedRecyclerListeners;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;

import java.util.List;

public class SearchDetailedAdapter extends RecyclerView.Adapter<SearchDetailedAdapter.SearchDetailedViewHolder>{

    private static final String TAG = "SearchDetailedAdapter";

    List<MealSimplifiedModel> meals;
    Context context;
    SearchDetailedRecyclerListeners listeners;

    public SearchDetailedAdapter(List<MealSimplifiedModel> list, Context context, SearchDetailedRecyclerListeners listeners) {
        this.meals = list;
        this.context = context;
        this.listeners = listeners;
    }

    public List<MealSimplifiedModel> getMeals() {
        return meals;
    }

    @NonNull
    @Override
    public SearchDetailedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchDetailedViewHolder viewHolder = new SearchDetailedViewHolder(LayoutInflater.from(context).inflate(R.layout.search_datiled_list_tile, parent, false));
        Log.d(TAG, "onCreateViewHolder: " + viewHolder.getAdapterPosition());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDetailedViewHolder holder, int position) {
        MealSimplifiedModel meal = meals.get(position);
        Log.d(TAG, "onBindViewHolder: " + meal);
        holder.getTitle().setText(meal.getTitle());
        holder.getCountry().setText(meal.getCountry());
        holder.getCategory().setText(meal.getCategory());
        holder.getCardView().setOnClickListener(v -> listeners.onItemClicked(meal.getId()));
        Glide.with(context).load(meal.getThumbnailUrl()).placeholder(R.drawable.food_photo).into(holder.getImageView());
    }

    public void updateList(List<MealSimplifiedModel> meals){
        Log.d(TAG, "updateList: " + meals.size());
        this.meals = meals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class SearchDetailedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView title;
        private final TextView country;
        private final TextView category;
        private final CardView cardView;

        public SearchDetailedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.searchDetailedListTileImageView);
            title = itemView.findViewById(R.id.searchDetailedListTileTitle);
            category = itemView.findViewById(R.id.searchDetailedListTileCategory);
            country = itemView.findViewById(R.id.searchDetailedListTileCountry);
            cardView = itemView.findViewById(R.id.searchDetailedCardView);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getCountry() {
            return country;
        }

        public TextView getCategory() {
            return category;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

}


