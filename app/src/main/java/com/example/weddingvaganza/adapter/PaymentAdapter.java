package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.BudgetModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.model.PaymentModel;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder>{

    Context context;
    List<BudgetModel> budgetModels;
    ClickedItem clickedItem;

    public PaymentAdapter(Context context, List<BudgetModel> budgetModels){
        this.context = context;
        this.budgetModels = budgetModels;
    }

    public void clickedListener (ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setPaymentModels (List<BudgetModel> budgetModels) {
        this.budgetModels = budgetModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.paid_list_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.MyViewHolder holder, int position) {
        BudgetModel budgetModel = budgetModels.get(position);

        String title = budgetModel.getTitleBudget();
        int estimate = budgetModel.getCostBudget();
        int paid = budgetModel.getPaid();

        holder.titlePaid.setText(title);
        holder.estimateBudget.setText("IDR " + estimate);

        // set paid text color
        String status = budgetModel.getStatus();
        if (status.equals("true")) {
            holder.totalPaid.setText("IDR " + paid);
            holder.totalPaid.setTextColor(Color.BLUE);
        } else {
            holder.totalPaid.setText("IDR " + paid);
            holder.totalPaid.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedPayment(budgetModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return budgetModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titlePaid;
        TextView estimateBudget;
        TextView totalPaid;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titlePaid = itemView.findViewById(R.id.tv_titlePaid);
            estimateBudget = itemView.findViewById(R.id.tv_estimateBudget);
            totalPaid = itemView.findViewById(R.id.tv_totalPaid);
            cardView = itemView.findViewById(R.id.cv_itemBudget);
        }
    }

    public interface ClickedItem {
        public void ClickedPayment(BudgetModel budgetModel);
    }
}
