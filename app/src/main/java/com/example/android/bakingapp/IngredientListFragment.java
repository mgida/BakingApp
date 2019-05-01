package com.example.android.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.IngredientAdapter;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IngredientListFragment extends Fragment {
    @BindView(R.id.rc_ingredients)
    RecyclerView recyclerViewIngredient;
    public static IngredientAdapter ingredientAdapter;
    LinearLayoutManager layoutManager;
    Recipe recipe;
    int recipeID;
    public static ArrayList<Ingredient> ingredientArrayList;
    Unbinder unbinder;
    private static final String INGREDIENT_REC = "Potsition";






    public IngredientListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_ingredient_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        ingredientArrayList = new ArrayList<>();
        recyclerViewIngredient.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewIngredient.setLayoutManager(layoutManager);
        ingredientAdapter = new IngredientAdapter(ingredientArrayList);
        recyclerViewIngredient.setAdapter(ingredientAdapter);
        if (savedInstanceState != null) {
            Parcelable recyclerViewState = savedInstanceState.getParcelable(INGREDIENT_REC);
            recyclerViewIngredient.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }


        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(INGREDIENT_REC,
                recyclerViewIngredient.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
