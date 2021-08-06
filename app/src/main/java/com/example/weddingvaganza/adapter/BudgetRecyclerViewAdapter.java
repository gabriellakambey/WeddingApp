package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.BudgetModel;

import java.util.List;

public class BudgetRecyclerViewAdapter extends RecyclerView.Adapter<BudgetRecyclerViewAdapter.MyViewHolder> {


    Context context;
    List<BudgetModel> budgetModels;

    public  BudgetRecyclerViewAdapter(Context context, List<BudgetModel> budgetModels){
        this.context = context;
        this.budgetModels = budgetModels;
    }
    @NonNull
    @Override
    public BudgetRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.budget_list_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.titleBudget.setText(budgetModels.get(position).getTitleBudget());
        holder.totalBudget.setText(budgetModels.get(position).getTotalBudget());

    }

    @Override
    public int getItemCount() {
        return budgetModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        Context context = itemView.getContext();
        private TextView titleBudget;
        private TextView totalBudget;
        private LinearLayout ll_totalBudget;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleBudget = itemView.findViewById(R.id.tv_titleBudget);
            totalBudget = itemView.findViewById(R.id.tv_totalBudget);
            ll_totalBudget = itemView.findViewById(R.id.ll_totalBudget);
        }
    }
}
