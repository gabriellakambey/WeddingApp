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
import com.example.weddingvaganza.model.PaymentModel;

import org.w3c.dom.Text;

import java.util.List;

public class PaymentRecyclerViewAdapter extends RecyclerView.Adapter<PaymentRecyclerViewAdapter.MyViewHolder>{

    private List<PaymentModel> paymentModels;

    public PaymentRecyclerViewAdapter(Context context, List<PaymentModel> paymentModels){
        this.paymentModels = paymentModels;
    }

    @NonNull
    @Override
    public PaymentRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.paid_list_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.titlePaid.setText(paymentModels.get(position).getTitlePaid());
        holder.totalPaid.setText(paymentModels.get(position).getTotalPaid());
        holder.totalBudget.setText(paymentModels.get(position).getIdBudget());
        holder.linearLayout.setOnClickListener(v -> {
            //DIALOG FRAGMENT
        });
    }

    @Override
    public int getItemCount() {
        return paymentModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context = itemView.getContext();
        private TextView titlePaid;
        private TextView totalPaid;
        private TextView totalBudget;
        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titlePaid = itemView.findViewById(R.id.tv_titlePaid);
            totalPaid = itemView.findViewById(R.id.tv_totalPaid);
            totalBudget = itemView.findViewById(R.id.tv_totalBudget);
            linearLayout = itemView.findViewById(R.id.ll_totalPaid);
        }
    }
}
