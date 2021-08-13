package com.example.weddingvaganza.adapter;

import android.content.Context;
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

public class ListBudgetAdapter extends RecyclerView.Adapter<ListBudgetAdapter.MyViewHolder> {

    List<BudgetModel> budgetModels;
    Context context;

    public ListBudgetAdapter(Context context, List<BudgetModel> budgetModels){
        this.context = context;
        this.budgetModels = budgetModels;
    }

    public void setBudgetModels(List<BudgetModel> budgetModels) {
        this.budgetModels = budgetModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListBudgetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.budget_list_item,parent,false);
        MyViewHolder viewHolder =new MyViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListBudgetAdapter.MyViewHolder holder, int position) {
        BudgetModel budgetModel = budgetModels.get(position);

        String title = budgetModel.getTitleBudget();
        int cost = budgetModel.getCostBudget();

        holder.titleBudget.setText(title);
        holder.totalBudget.setText("IDR " + cost);

    }

    @Override
    public int getItemCount() {
        return budgetModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleBudget, totalBudget;
        LinearLayout ll_totalBudget;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleBudget = itemView.findViewById(R.id.tv_titleBudget);
            totalBudget = itemView.findViewById(R.id.tv_totalBudget);
            ll_totalBudget = itemView.findViewById(R.id.ll_totalBudget);
        }
    }
}
