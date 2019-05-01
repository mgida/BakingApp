package com.example.android.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.android.bakingapp.Utils.JsonParsing;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity implements StepListFragment.OnStepItemClickedListener {
    private Recipe recipe;
    static int recipeID;
    private boolean mTwopane;
    private Step sStep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        recipeID = recipe.getId();
        if (findViewById(R.id.detail_linearLayout) != null) {
            mTwopane = true;


            /* INGREDIENT */
            IngredientListFragment ingredientListFragment = new IngredientListFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientListFragment)
                    .commit();
            FetchIngredients fetchIngredients = new FetchIngredients();
            fetchIngredients.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");



            /*STEP*/
            StepListFragment stepListFragment = new StepListFragment();
            FragmentManager manager1 = getSupportFragmentManager();
            manager1.beginTransaction()
                    .add(R.id.steps_container, stepListFragment)
                    .commit();
            FetchSteps fetchSteps = new FetchSteps();
            fetchSteps.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

            /*Detail*/
            StepDetailListFragment stepDetailListFragmentstFragment = new StepDetailListFragment();
            stepDetailListFragmentstFragment.ReciveStep(sStep);
            FragmentManager manager2 = getSupportFragmentManager();
            manager2.beginTransaction()
                    .add(R.id.step_detail_container, stepDetailListFragmentstFragment)
                    .commit();


        } else {
            mTwopane = false;
            /* INGREDIENT */
            IngredientListFragment ingredientListFragment = new IngredientListFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientListFragment)
                    .commit();
            FetchIngredients fetchIngredients = new FetchIngredients();
            fetchIngredients.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");



            /*STEP*/
            StepListFragment stepListFragment = new StepListFragment();
            FragmentManager manager1 = getSupportFragmentManager();
            manager1.beginTransaction()
                    .add(R.id.steps_container, stepListFragment)
                    .commit();
            FetchSteps fetchSteps = new FetchSteps();
            fetchSteps.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");


        }


    }

    @Override
    public void onStepClicked(Step step) {
        if (mTwopane) {
            StepDetailListFragment stepDetailListFragmentstFragment = new StepDetailListFragment();
            stepDetailListFragmentstFragment.ReciveStep(step);
            FragmentManager manager2 = getSupportFragmentManager();
            manager2.beginTransaction()
                    .replace(R.id.step_detail_container, stepDetailListFragmentstFragment)
                    .commit();


        } else {
            Intent openStepDetailActivity = new Intent(this, StepDetailActivity.class);
            openStepDetailActivity.putExtra("step", step);
            startActivity(openStepDetailActivity);

        }
    }


    public static class FetchIngredients extends AsyncTask<String, Void, String> {

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JsonParsing.parseIngredient(recipeID, s);
            //recipeAdapter.setRecipeData(recipes);
        }
    }

    private class FetchSteps extends AsyncTask<String, Void, String> {

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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JsonParsing.parseSteps(recipeID, s);
            //recipeAdapter.setRecipeData(recipes);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
