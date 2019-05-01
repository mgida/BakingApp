package com.example.android.bakingapp;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new
            ActivityTestRule<>(MainActivity.class);

    @Test
    public void ClickItem_Pie() {
        onView(withId(R.id.rc_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void ClickItem_Brownie() {
        onView(withId(R.id.rc_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    }

    @Test
    public void ClickItem_Cake() {
        onView(withId(R.id.rc_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

    }

    @Test
    public void ClickItem_Cheesecake() {
        onView(withId(R.id.rc_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

    }
    @Test
    public void CheckIngredientsDisplayed (){
        onView(withId(R.id.rc_ingredients)).check(matches(isDisplayed()));
    }
    @Test
    public void CheckStepsDisplayed (){
        onView(withId(R.id.rc_steps)).check(matches(isDisplayed()));
    }


}
