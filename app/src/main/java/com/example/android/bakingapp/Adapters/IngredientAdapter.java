package com.example.android.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {
    ArrayList<Ingredient> mIngredient;

    public IngredientAdapter(ArrayList<Ingredient> mIngredient) {
        this.mIngredient = mIngredient;
    }

    @NonNull
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_ingredient, parent, false);
        IngredientAdapterViewHolder viewHolder = new
                IngredientAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder holder, int i) {
        Ingredient ingredient = mIngredient.get(i);
        holder.tv_ingredient.setText(ingredient.getIngredient());
        holder.tv_quantity.setText(String.valueOf(ingredient.getQuantity()));
        holder.tv_measure.setText(ingredient.getMeasure());

    }

    @Override
    public int getItemCount() {
        return mIngredient.size();
    }

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ingredient;
        TextView tv_measure;
        TextView tv_quantity;


        public IngredientAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ingredient = itemView.findViewById(R.id.tv_ingredient);
            tv_measure = itemView.findViewById(R.id.tv_measure);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);

        }
    }
}
