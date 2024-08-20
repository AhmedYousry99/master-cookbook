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
import com.senseicoder.mastercookbook.model.DTOs.CategoryDTO;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    List<Object> categories;
    Context context;
    nestedRecyclerViewItemListeners listener;

    public CategoriesRecyclerAdapter(List categories, Context context, nestedRecyclerViewItemListeners listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tile_small, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoryDTO category = (CategoryDTO) categories.get(position);
        holder.getTitle().setText(category.getTitle());
        holder.getCardView().setOnClickListener(v -> listener.onGetMealsByCategoryClicked(category.getTitle()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}


class CategoriesViewHolder extends RecyclerView.ViewHolder{
    private final TextView title;
    private final CardView cardView;

    public CategoriesViewHolder(@NonNull View itemView) {
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

