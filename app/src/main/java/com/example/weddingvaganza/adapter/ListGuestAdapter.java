package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.view.dialog.AddCategoryDialog;
import com.example.weddingvaganza.view.dialog.GuestDetailDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ListGuestAdapter extends RecyclerView.Adapter<ListGuestAdapter.ViewHolder> {

    private List<GuestModel> guestModels;
    ClickedItem clickedItem;

    public ListGuestAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setGuestModels(List<GuestModel> guestModels) {
        this.guestModels = guestModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_guest, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GuestModel guestModel = guestModels.get(position);
        String guestName = guestModel.getGuestNama();
        holder.textView.setText(guestName);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedGuest(guestModel);
            }
        });

    }

    public interface ClickedItem {
        public void ClickedGuest(GuestModel guestModel);
    }

    @Override
    public int getItemCount() {
        return guestModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public View view;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_listGuest);
            view = itemView.findViewById(R.id.dotStatus);
            cardView = itemView.findViewById(R.id.cv_listGuest);
        }
    }


    public void getFilter(List<GuestModel> filteredList) {
        this.guestModels = filteredList;
        notifyDataSetChanged();
    }
}
