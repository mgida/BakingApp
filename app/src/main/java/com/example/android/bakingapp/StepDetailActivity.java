package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android.bakingapp.model.Step;

public class StepDetailActivity extends AppCompatActivity {
    Step step ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
         step =intent.getParcelableExtra("step");

        StepDetailListFragment stepDetailListFragmentstFragment = new StepDetailListFragment();
        stepDetailListFragmentstFragment.ReciveStep(step);
        FragmentManager manager2 = getSupportFragmentManager();
        manager2.beginTransaction()
                .add(R.id.step_detail_container, stepDetailListFragmentstFragment)
                .commit();


//        StepDetailListFragment stepDetailListFragmentstFragment = new StepDetailListFragment();
//        stepDetailListFragmentstFragment.ReciveStep(step);
//        FragmentManager manager2 = getSupportFragmentManager();
//        manager2.beginTransaction()
//                .add(R.id.step_detail_container, stepDetailListFragmentstFragment)
//                .commit();


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
