package com.example.android.bakingapp;

import android.content.Context;
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

import com.example.android.bakingapp.Adapters.StepAdapter;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StepListFragment extends Fragment implements StepAdapter.OnStepItemClicked {
    @BindView(R.id.rc_steps)
    RecyclerView recyclerView;
    public static StepAdapter stepAdapter;
    LinearLayoutManager layoutManager2;
    public static ArrayList<Step> stepArrayList;
    View rootView;
    Unbinder unbinder;

    private static final String STEP_REC = "position";
    OnStepItemClickedListener mOnStepItemClickedListener;


    public StepListFragment() {
    }


    public interface OnStepItemClickedListener {
        void onStepClicked(Step step);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_step_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        stepArrayList = new ArrayList<>();
        // recyclerView = (RecyclerView) rootView.findViewById(R.id.rc_steps);
        recyclerView.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager2);
        stepAdapter = new StepAdapter(stepArrayList, this);
        recyclerView.setAdapter(stepAdapter);
        if (savedInstanceState != null) {
            Parcelable recyclerViewState = savedInstanceState.getParcelable(STEP_REC);
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }


        return rootView;
    }

    @Override
    public void onStep(Step step) {
        mOnStepItemClickedListener.onStepClicked(step);


//        if (DetailActivity.mTwopane ){
//
//
//
//        }else {
//            Intent openStepDetailActivity = new Intent(getContext(), StepDetailActivity.class);
//            openStepDetailActivity.putExtra("step", step);
//            startActivity(openStepDetailActivity);
//
//        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable parcelable = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(STEP_REC, parcelable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnStepItemClickedListener = (OnStepItemClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement OnStepItemClickedListener");
        }
    }


}



