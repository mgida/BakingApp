package com.example.android.bakingapp.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {
    ArrayList<Step> mStep;
    OnStepItemClicked mOnStepItemClicked ;


    public interface OnStepItemClicked {
        void onStep(Step step);

    }


    public StepAdapter(ArrayList<Step> mStep ,OnStepItemClicked onStepItemClicked) {
        this.mStep = mStep;
        this.mOnStepItemClicked =onStepItemClicked ;
    }



    @NonNull
    @Override
    public StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.step_list_item, parent, false);
        StepAdapterViewHolder viewHolder = new StepAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapterViewHolder holder, int position) {
        Step step = mStep.get(position);

        holder.textView_shortDes.setText(step.getShortDescription());


    }

    @Override
    public int getItemCount() {
        return mStep.size();
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_shortDes;

        public StepAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_shortDes = (TextView) itemView.findViewById(R.id.tv_short_dec);
            textView_shortDes.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Step step = mStep.get(position);
            mOnStepItemClicked.onStep(step);

        }
    }


}
