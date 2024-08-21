package com.senseicoder.mastercookbook.features.meal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.senseicoder.mastercookbook.R;
import com.senseicoder.mastercookbook.util.global.Constants;

import java.util.List;

public class MealDetailsIngredientsRecyclerAdapter extends RecyclerView.Adapter<IngredientViewHolder>{

    List<String> ingredients;
    Context context;
    public MealDetailsIngredientsRecyclerAdapter(List<String> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IngredientViewHolder viewHolder = new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.meal_ingredients_list_tile, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        String ingredientTitle = ingredients.get(position);
        holder.getTitle().setText(ingredientTitle);
        Glide.with(context).load(Constants.INGREDIENTS_URL + ingredientTitle + "-Small.png").placeholder(R.drawable.ingredient_placeholder).into(holder.getImage());
    }

    public void updateList(List<String> newList){
        ingredients = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}

class IngredientViewHolder extends RecyclerView.ViewHolder{

    private ImageView image;
    private TextView title;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.mealListTileTitle);
        image = itemView.findViewById(R.id.mealListTileImageView);
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getTitle() {
        return title;
    }
}
