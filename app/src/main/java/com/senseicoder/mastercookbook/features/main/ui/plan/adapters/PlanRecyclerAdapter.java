package com.senseicoder.mastercookbook.features.main.ui.plan.adapters;

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
import com.senseicoder.mastercookbook.features.main.ui.plan.listeners.PlanRecyclerViewListeners;
import com.senseicoder.mastercookbook.model.DTOs.PlanDTO;

import java.util.List;

public class PlanRecyclerAdapter extends RecyclerView.Adapter<PlanViewHolder>{

    Context context;
    List<PlanDTO> plans;
    PlanRecyclerViewListeners listeners;

    public PlanRecyclerAdapter(Context context, List<PlanDTO> plans, PlanRecyclerViewListeners listeners) {
        this.context = context;
        this.plans = plans;
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlanViewHolder holder = new PlanViewHolder(LayoutInflater.from(context).inflate(R.layout.plan_list_tile_large, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        PlanDTO plan = plans.get(position);
        holder.getBookmarkButton().setOnClickListener(v -> listeners.onBookmarkClicked(plan));
        holder.getCheckIngredientsButton().setOnClickListener(v -> listeners.onCheckIngredientsClicked(plan));
        holder.getTitleTextView().setText(plan.getTitle());
        Glide.with(context).load(plan.getThumbnailUrl()).placeholder(R.drawable.food_photo).into(holder.getBackgroundImageView());
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void updateList(PlanDTO plan) {
        plans.remove(plan);
        notifyDataSetChanged();
    }
}

class PlanViewHolder extends RecyclerView.ViewHolder{

    private ImageButton bookmarkButton;
    private TextView titleTextView;
    private Button checkIngredientsButton;
    private ImageView backgroundImageView;

    public PlanViewHolder(@NonNull View itemView) {
        super(itemView);
        bookmarkButton = itemView.findViewById(R.id.planListTileCardBookmarkButton);
                titleTextView = itemView.findViewById(R.id.planListTilecardTitleTextView);
        checkIngredientsButton = itemView.findViewById(R.id.planListTilecardCheckIngredientsButton);
                backgroundImageView = itemView.findViewById(R.id.planListTileCardBackgroundImageView);
    }

    public ImageButton getBookmarkButton() {
        return bookmarkButton;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public Button getCheckIngredientsButton() {
        return checkIngredientsButton;
    }

    public ImageView getBackgroundImageView() {
        return backgroundImageView;
    }
}
