package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryScheduleModel;
import com.example.weddingvaganza.model.ScheduleModel;
import com.example.weddingvaganza.model.ScheduleUpdateModel;
import com.example.weddingvaganza.model.schedulebyid.ScheduleByIdModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryScheduleAdapter extends RecyclerView.Adapter<CategoryScheduleAdapter.ViewHolder> implements ListScheduleAdapter.ListScheduleCallback {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<CategoryScheduleModel> categoryScheduleModelList;
    private Context context;
    ListScheduleAdapter listScheduleAdapter;
    ScheduleUpdateModel scheduleUpdateModel;
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

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

        listScheduleAdapter = new ListScheduleAdapter(categoryScheduleModel.getScheduleModels());
        listScheduleAdapter.setListener(this::onChecked);

        holder.rvSchedule.setLayoutManager(layoutManager);
        holder.rvSchedule.setAdapter(listScheduleAdapter);
        holder.rvSchedule.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return categoryScheduleModelList.size();
    }

    @Override
    public void onChecked(int scheduleId, Boolean isChecked) {
        getScheduleById(scheduleId, isChecked);
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

    private void getScheduleById(int scheduleId, Boolean isChecked) {
        Call<ScheduleByIdModel> call = weddingService.getScheduleById(scheduleId);
        call.enqueue(new Callback<ScheduleByIdModel>() {
            @Override
            public void onResponse(Call<ScheduleByIdModel> call, Response<ScheduleByIdModel> response) {
                ScheduleByIdModel data = response.body();
                String status = isChecked ? "checked" : "unchecked";
                scheduleUpdateModel = new ScheduleUpdateModel(scheduleId, data.getDate(), data.getTitle(), data.getFkCategoryId().getCategoryId(), data.getNote(), data.getFkUserId().getUserId(), status, data.getMonth(), data.getYear());
                updateStatus(scheduleId);
            }

            @Override
            public void onFailure(Call<ScheduleByIdModel> call, Throwable t) {

            }
        });
    }

    private void updateStatus(int scheduleId) {
        Call<ScheduleModel> call = weddingService.updateSchedule(scheduleId, scheduleUpdateModel);
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {

            }
        });
    }


}
