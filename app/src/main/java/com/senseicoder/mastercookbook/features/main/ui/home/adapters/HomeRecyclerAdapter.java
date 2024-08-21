package com.senseicoder.mastercookbook.features.main.ui.home.adapters;

import android.content.Context;
import android.util.Log;
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
import com.senseicoder.mastercookbook.features.main.ui.home.HomeFragmentItem;
import com.senseicoder.mastercookbook.features.main.ui.home.listeners.nestedRecyclerViewItemListeners;
import com.senseicoder.mastercookbook.model.DTOs.MealDTO;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    static final int MAIN_HEADER = 0;
    static final int SECONDARY_HEADER = 1;
    static final int CATEGORIES = 2;
    static final int COUNTRIES = 3;
    static final int MORE_YOU_MIGHT_LIKE = 4;

    private static final String TAG = "HomeRecyclerAdapter";

    Context context;
    List<HomeFragmentItem> items;
    nestedRecyclerViewItemListeners listener;

    public HomeRecyclerAdapter(Context context, List<HomeFragmentItem> items,  nestedRecyclerViewItemListeners listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    public void setData(List<HomeFragmentItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MAIN_HEADER:
            {
                MainHeaderViewHolder viewHolder = new MainHeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_list_header, viewGroup, false));
                viewHolder.getCardCheckIngredientsButton().setOnClickListener(v -> listener.onCheckIngredientsClicked(items.get(MAIN_HEADER).getMeal().getId()));
                viewHolder.getCardFavoriteButton().setOnClickListener(v -> listener.onFavoriteClicked(items.get(MAIN_HEADER).getMeal().getId()));
                Log.d(TAG, "onCreateViewHolder: MAIN_HEADER" );
                return viewHolder;
            }
            case SECONDARY_HEADER:
            Log.d(TAG, "onCreateViewHolder: SECONDARY_HEADER" );
                return new SecondaryHeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_secondary_header, viewGroup, false));
            case CATEGORIES:
            Log.d(TAG, "onCreateViewHolder: CATEGORIES" );
            return new CategoriesRecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_secondary_recycler, viewGroup, false));
            case COUNTRIES:
            Log.d(TAG, "onCreateViewHolder: COUNTRIES" );
                return new CountriesRecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_secondary_recycler, viewGroup, false));
            case MORE_YOU_MIGHT_LIKE:
                Log.d(TAG, "onCreateViewHolder: MORE_YOU_MIGHT_LIKE" );
                MainRecyclerViewHolder viewHolder = new MainRecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_meals_you_might_like_recycler, viewGroup, false));
            return viewHolder;
            default:
                Log.d(TAG, "onCreateViewHolder: NULL?????????" );
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        if(holder instanceof  MainHeaderViewHolder){
            MealDTO meal = items.get(position).getMeal();
            MainHeaderViewHolder currentHolder = (MainHeaderViewHolder) holder;
            currentHolder.getCardTitleTextView().setText(meal.getTitle());
            Glide.with(context).load(meal.getThumbnail()).placeholder(R.drawable.food_photo).into(currentHolder.getHomeListTIleCardBackgroundImageView());
            Log.d(TAG, "onCreateViewHolder: MAIN_HEADER" );
        } else if (holder instanceof SecondaryHeaderViewHolder){
            SecondaryHeaderViewHolder currentHolder = (SecondaryHeaderViewHolder) holder;
            currentHolder.getImageView().setImageResource(items.get(position).getIconResourceId());
            currentHolder.getTextView().setText(items.get(position).getTitle());
            Log.d(TAG, "onCreateViewHolder: SECONDARY_HEADER" );
        } else if(holder instanceof CategoriesRecyclerViewHolder){
            CategoriesRecyclerViewHolder currentHolder = (CategoriesRecyclerViewHolder) holder;
            CategoriesRecyclerAdapter adapter = new CategoriesRecyclerAdapter(
                    items.get(position).getList(), context, listener
            );
            currentHolder.getCategoriesRecyclerView().setAdapter(adapter);
            Log.d(TAG, "onCreateViewHolder: CATEGORIES" );
        } else if(holder instanceof CountriesRecyclerViewHolder){
            CountriesRecyclerViewHolder currentHolder = (CountriesRecyclerViewHolder) holder;
            CountriesRecyclerAdapter adapter = new CountriesRecyclerAdapter(
                     context, listener
            );
            currentHolder.getCountriesRecyclerView().setAdapter(adapter);
             Log.d(TAG, "onCreateViewHolder: COUNTRIES" );
        }else if(holder instanceof MainRecyclerViewHolder){
            MainRecyclerViewHolder currentHolder = (MainRecyclerViewHolder) holder;
            MoreYouMightLikeRecyclerAdapter adapter = new MoreYouMightLikeRecyclerAdapter(
                    items.get(position).getList(),
                    context,
                    listener
            );
            currentHolder.getMoreYouMightLikeRecyclerView().setAdapter(adapter);
            Log.d(TAG, "onCreateViewHolder: MORE_YOU_MIGHT_LIKE" );
        }

    }
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + items.size());
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);
        HomeFragmentItem<Object> currentItem = items.get(position);
        return currentItem.getType();
    }
}

abstract class BaseViewHolder extends RecyclerView.ViewHolder{

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

class MainHeaderViewHolder extends  BaseViewHolder{
    private ImageView homeListTilecardBackgroundImageView;
    private ImageButton cardFavoriteButton;
    private Button cardCheckIngredientsButton;
    private TextView cardTitleTextView;

    public MainHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        this.homeListTilecardBackgroundImageView = itemView.findViewById(R.id.smallListTileImageView);
        cardFavoriteButton = itemView.findViewById(R.id.cardFavoriteButton);
        cardCheckIngredientsButton = itemView.findViewById(R.id.cardCheckIngredientsButton);
        cardTitleTextView = itemView.findViewById(R.id.cardTitleTextView);
    }

    public ImageView getHomeListTIleCardBackgroundImageView() {
        return homeListTilecardBackgroundImageView;
    }

    public ImageButton getCardFavoriteButton() {
        return cardFavoriteButton;
    }

    public Button getCardCheckIngredientsButton() {
        return cardCheckIngredientsButton;
    }

    public TextView getCardTitleTextView() {
        return cardTitleTextView;
    }
}

class CategoriesRecyclerViewHolder extends BaseViewHolder{

    private RecyclerView categoriesRecyclerView;

    public CategoriesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        categoriesRecyclerView = itemView.findViewById(R.id.homeFragmentSecondaryRecycleView);
    }

    public RecyclerView getCategoriesRecyclerView() {
        return categoriesRecyclerView;
    }
}

class CountriesRecyclerViewHolder extends BaseViewHolder{

    private RecyclerView countriesRecyclerView;

    public CountriesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        countriesRecyclerView = itemView.findViewById(R.id.homeFragmentSecondaryRecycleView);
    }

    public RecyclerView getCountriesRecyclerView() {
        return countriesRecyclerView;
    }
}

class SecondaryHeaderViewHolder extends BaseViewHolder{

    private ImageView imageView;
    private TextView textView;

    public SecondaryHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.fragmentHomeSecondaryHeaderIcon);
        textView = itemView.findViewById(R.id.fragmentHomeSecondaryHeaderTextView);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }
}

class MainRecyclerViewHolder extends BaseViewHolder{

    RecyclerView moreYouMightLikeRecyclerView;

    public MainRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        moreYouMightLikeRecyclerView = itemView.findViewById(R.id.homeRecyclerView);
    }

    public RecyclerView getMoreYouMightLikeRecyclerView() {
        return moreYouMightLikeRecyclerView;
    }
}