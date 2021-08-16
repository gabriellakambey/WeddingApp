package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.BudgetModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.model.PaymentModel;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder>{

    Context context;
    private List<PaymentModel> paymentModels;
    ClickedItem clickedItem;

    public PaymentAdapter(Context context, List<PaymentModel> paymentModels){
        this.context = context;
        this.paymentModels = paymentModels;
    }

    public void clickedListener (ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setPaymentModels (List<PaymentModel> paymentModels) {
        this.paymentModels = paymentModels;
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

        holder.titlePaid.setText(paymentModels.get(position).getTitlePaid());
        holder.totalPaid.setText(paymentModels.get(position).getTotalPaid());
        holder.estimateBudget.setText(paymentModels.get(position).getIdBudget());
        holder.cardView.setOnClickListener(v -> {
            //DIALOG FRAGMENT
        });
    }

    @Override
    public int getItemCount() {
        return paymentModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titlePaid;
        TextView totalPaid;
        TextView estimateBudget;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titlePaid = itemView.findViewById(R.id.tv_titlePaid);
            totalPaid = itemView.findViewById(R.id.tv_totalPaid);
            estimateBudget = itemView.findViewById(R.id.tv_estimateBudget);
            cardView = itemView.findViewById(R.id.cv_itemBudget);
        }
    }

    public interface ClickedItem {
        public void ClickedPayment(BudgetModel budgetModel);
    }
}
