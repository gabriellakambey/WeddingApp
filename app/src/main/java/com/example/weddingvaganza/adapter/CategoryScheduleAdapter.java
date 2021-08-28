package com.example.weddingvaganza.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.CategoryScheduleModel;

import java.util.List;

public class CategoryScheduleAdapter extends RecyclerView.Adapter<CategoryScheduleAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<CategoryScheduleModel> categoryScheduleModelList;

    public CategoryScheduleAdapter(List<CategoryScheduleModel> categoryScheduleModelList) {
        this.categoryScheduleModelList = categoryScheduleModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_category_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryScheduleAdapter.ViewHolder holder, int position) {
        CategoryScheduleModel categoryScheduleModel = categoryScheduleModelList.get(position);
        String catTitle = categoryScheduleModel.getTitleCategory();
        int categoryId = categoryScheduleModel.getCategoryId();

        holder.tvCategory.setText(catTitle);


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
