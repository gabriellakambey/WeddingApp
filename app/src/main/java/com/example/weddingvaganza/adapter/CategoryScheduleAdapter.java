package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.CategoryScheduleModel;

import java.util.List;

public class CategoryScheduleAdapter extends RecyclerView.Adapter<CategoryScheduleAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<CategoryScheduleModel> categoryScheduleModelList;
    private Context context;

    public CategoryScheduleAdapter(List<CategoryScheduleModel> categoryScheduleModelList) {
        this.categoryScheduleModelList = categoryScheduleModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_category_schedule, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryScheduleAdapter.ViewHolder holder, int position) {
        CategoryScheduleModel categoryScheduleModel = categoryScheduleModelList.get(position);

        holder.tvCategory.setText(categoryScheduleModel.getTitleCategory());

        // set recycler view list schedule
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvSchedule.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(categoryScheduleModel.getScheduleModels().size());

        ListScheduleAdapter listScheduleAdapter = new ListScheduleAdapter(categoryScheduleModel.getScheduleModels());

        holder.rvSchedule.setLayoutManager(layoutManager);
        holder.rvSchedule.setAdapter(listScheduleAdapter);
        holder.rvSchedule.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return categoryScheduleModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        RecyclerView rvSchedule;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_categoryOnSchedule);
            rvSchedule = itemView.findViewById(R.id.rv_listSchedule);
        }
    }
}
