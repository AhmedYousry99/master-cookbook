package com.senseicoder.mastercookbook.features.search_detailed.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.model.DTOs.MealSimplifiedModel;

import java.util.List;

public class SearchDetailedAdapter extends RecyclerView.Adapter<SearchDetailedAdapter.SearchDetailedViewHolder>{

    List<MealSimplifiedModel> meals;
    Context context;
    private static final String TAG = "SearchDetailedAdapter";

    public SearchDetailedAdapter(List<MealSimplifiedModel> list, Context context) {
        this.meals = list;
        this.context = context;
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
        holder.getSearchDetailedListTileTitle().setText(meal.getTitle());
        Glide.with(context).load(meal.getThumbnail()).placeholder(R.drawable.food_photo).into(holder.getSearchDetailedListTileImageView());
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

    class SearchDetailedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView searchDetailedListTileImageView;
        private final TextView searchDetailedListTileTitle;

        public SearchDetailedViewHolder(@NonNull View itemView) {
            super(itemView);
            searchDetailedListTileImageView = itemView.findViewById(R.id.searchDetailedListTileImageView);
            searchDetailedListTileTitle = itemView.findViewById(R.id.searchDetailedListTileTitle);
        }

        public ImageView getSearchDetailedListTileImageView() {
            return searchDetailedListTileImageView;
        }

        public TextView getSearchDetailedListTileTitle() {
            return searchDetailedListTileTitle;
        }
    }

}


