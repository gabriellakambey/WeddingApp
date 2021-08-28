package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.CategoryRundownModel;

import java.util.List;


public class CategoryRundownAdapter extends RecyclerView.Adapter<CategoryRundownAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<CategoryRundownModel> categoryRundownModelList;
    private Context context;

    public CategoryRundownAdapter(List<CategoryRundownModel> categoryRundownModelList) {
        this.categoryRundownModelList = categoryRundownModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CategoryRundownAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_category_rundown, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryRundownModel categoryRundownModel = categoryRundownModelList.get(position);

        holder.tvCategory.setText(categoryRundownModel.getTitleCategory());

        // set recycler view list rundown
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvCategory.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(categoryRundownModel.getRundownModelList().size());

        ListRundownAdapter listRundownAdapter = new ListRundownAdapter(categoryRundownModel.getRundownModelList());

        holder.rvCategory.setLayoutManager(layoutManager);
        holder.rvCategory.setAdapter(listRundownAdapter);
        holder.rvCategory.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return categoryRundownModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private RecyclerView rvCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategory = itemView.findViewById(R.id.tv_categoryOnRundown);
            rvCategory = itemView.findViewById(R.id.rv_rundownInCategory);
        }
    }
}
