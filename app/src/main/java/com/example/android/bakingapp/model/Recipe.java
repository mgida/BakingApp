package com.example.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    int id;
    String name;
    int servings;
    String image;

    public Recipe(int id, String name, int servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public Recipe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.servings = in.readInt();
        this.image = in.readString();


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.servings);
        dest.writeString(this.image);


    }

    public static final Creator<Recipe> CREATOR = new
            Creator<Recipe>() {
                @Override
                public Recipe createFromParcel(Parcel in) {
                    return new Recipe(in);
                }

                @Override
                public Recipe[] newArray(int size) {
                    return new Recipe[size];
                }
            };


}
