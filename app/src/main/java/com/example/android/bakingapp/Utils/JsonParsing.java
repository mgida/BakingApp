package com.example.android.bakingapp.Utils;


import com.example.android.bakingapp.DetailActivity;
import com.example.android.bakingapp.IngredientListFragment;
import com.example.android.bakingapp.StepListFragment;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonParsing {


    public static Recipe[] parseRecipe(String rJson) {
        Recipe[] recipes = null;

        try {
            JSONArray jsonArray = new JSONArray(rJson);
            recipes = new Recipe[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                int servings = object.getInt("servings");
                String image = object.getString("image");

                recipes[i] = new Recipe(id, name, servings, image);
            }
            return recipes;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Void parseIngredient(int id, String iJson) {
        try {
            Ingredient mIngredient;

            JSONArray jsonArray = new JSONArray(iJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                if (jsonObject.getInt("id") == id) {
                    JSONArray IngredientsArray = jsonObject.getJSONArray("ingredients");
                    for (int j = 0; j < IngredientsArray.length(); j++) {
                        JSONObject ingredObject = (JSONObject) IngredientsArray.get(j);
                        String ingredient = ingredObject.getString("ingredient");
                        String measure = ingredObject.getString("measure");
                        Double quantity = ingredObject.getDouble("quantity");
                        mIngredient = new Ingredient(quantity, measure, ingredient);
                        IngredientListFragment.ingredientArrayList.add(mIngredient);
                    }
                }
            }
            IngredientListFragment.ingredientAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Void parseSteps(int id, String sJson) {
        try {
            Step mStep;

            JSONArray jsonArray = new JSONArray(sJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                if (jsonObject.getInt("id") == id) {
                    JSONArray StepsArray = jsonObject.getJSONArray("steps");
                    for (int j = 0; j < StepsArray.length(); j++) {
                        JSONObject stepObject = (JSONObject) StepsArray.get(j);
                        int id_step = stepObject.getInt("id");
                        String shortDescription = stepObject.getString("shortDescription");
                        String description = stepObject.getString("description");
                        String videoURL = stepObject.getString("videoURL");
                        String thumbnailURL = stepObject.getString("thumbnailURL");
                        mStep = new Step(id_step, shortDescription, description, videoURL, thumbnailURL);
                        StepListFragment.stepArrayList.add(mStep);
                    }
                }
            }
            StepListFragment.stepAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
