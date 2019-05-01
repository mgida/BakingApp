package com.example.android.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingapp.Adapters.RecipeAdapter;
import com.example.android.bakingapp.Utils.CheckConnection;
import com.example.android.bakingapp.Utils.JsonParsing;
import com.example.android.bakingapp.model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeClickHandler {
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    public static RecipeAdapter recipeAdapter;
    static Recipe[] recipes;
    TextView tv_error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_error = (TextView) findViewById(R.id.error);
        recyclerView = (RecyclerView) findViewById(R.id.rc_recipe);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recipeAdapter = new RecipeAdapter(this);
        recyclerView.setAdapter(recipeAdapter);
        if (savedInstanceState != null) {
            Parcelable p = savedInstanceState.getParcelable("RecState");
            recyclerView.getLayoutManager().onRestoreInstanceState(p);
        }


        if (CheckConnection.isOnline(getApplicationContext())) {
            FetchRecipes fetchRecipes = new FetchRecipes();
            fetchRecipes.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

        } else {
            tv_error.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void OnClick(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);


    }

    public class FetchRecipes extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            recipes = JsonParsing.parseRecipe(json);
            recipeAdapter.setRecipeData(recipes);


        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("RecState", recyclerView.getLayoutManager().onSaveInstanceState());
    }
}
