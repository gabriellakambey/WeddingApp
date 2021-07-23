package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.ScheduleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weddingvaganza.R.color.*;

public class ListScheduleAdapter extends RecyclerView.Adapter<ListScheduleAdapter.ViewHolder>{

    private List<ScheduleModel> schedule;
    private Context context;
    int checkedId;

    public void setData(List<ScheduleModel> schedule) {
        this.schedule = schedule;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ListScheduleAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_schedule,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleModel scheduleModel = schedule.get(position);
        String title = scheduleModel.getTitleSchedule();
        checkedId = scheduleModel.getScheduleId();
        holder.checkBox.setText(title);
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_listSchedule);
            checkBox.setOnClickListener(v -> {
                if (checkBox.isChecked()) {
                    checkBox.setTextColor(itemView.getResources().getColor(gold));
                    updateStatus();
                } else {
                    checkBox.setTextColor(itemView.getResources().getColor(navy));
                }
            });
        }
    }

    private void updateStatus() {
        ScheduleModel scheduleModel = new ScheduleModel("checked");

        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleModel> call = weddingService.updateSchedule(checkedId,scheduleModel);
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {

            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {

            }
        });
    }
}
