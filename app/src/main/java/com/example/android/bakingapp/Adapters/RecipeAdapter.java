package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    public static Recipe[] mRecipe;
    static RecipeClickHandler handler;

    public interface RecipeClickHandler {
        void OnClick(Recipe recipe);
    }

    public RecipeAdapter(RecipeClickHandler handler) {
        this.handler = handler;
    }

    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item_recipe, parent, false);
        RecipeAdapterViewHolder viewHolder = new RecipeAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder holder, int i) {

        Recipe recipe = mRecipe[i];
        holder.recipeName.setText(recipe.getName());
        holder.recipeServings.setText(String.valueOf(recipe.getServings()));
        Context context = holder.imageView.getContext();
        String poster = recipe.getImage();
        if (TextUtils.isEmpty(poster)) {
            holder.imageView.setImageResource(R.drawable.default_image);
            Log.d("gida", "default image");
        } else {
            Picasso.with(context).load(poster).into(holder.imageView);
            Log.d("gida", " poster");


        }
    }


    @Override
    public int getItemCount() {
        if (mRecipe == null) {
            return 0;
        }
        return mRecipe.length;
    }

    public static class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView recipeName;
        TextView recipeServings;

        public RecipeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            //view from onCreateViewHolder that inflated

            imageView = (ImageView) itemView.findViewById(R.id.iv_recipe);
            recipeName = (TextView) itemView.findViewById(R.id.tv_recipe_name);
            recipeServings = (TextView) itemView.findViewById(R.id.tv_recipe_servings);

            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Recipe recipe = mRecipe[position];
            handler.OnClick(recipe);
        }
    }

    public void setRecipeData(Recipe[] RecipeData) {
        mRecipe = RecipeData;
        notifyDataSetChanged();


    }


}
