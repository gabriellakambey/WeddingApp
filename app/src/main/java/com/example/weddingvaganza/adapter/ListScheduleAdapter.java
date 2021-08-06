package com.example.weddingvaganza.adapter;

import android.annotation.SuppressLint;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.ScheduleModel;
import com.google.gson.annotations.SerializedName;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weddingvaganza.R.color.*;

public class ListScheduleAdapter extends RecyclerView.Adapter<ListScheduleAdapter.ViewHolder>{

    private List<ScheduleModel> schedule;
    private Context context;
    
    int scheduleId, idCategory, userId, month, year;
    String dateSchedule, titleSchedule, noteSchedule, statusSchedule;
    ScheduleModel scheduleModel;
    WeddingService weddingService;

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

        scheduleId = scheduleModel.getScheduleId();
        statusSchedule = scheduleModel.getStatus();
        titleSchedule = scheduleModel.getTitleSchedule();
        holder.checkBox.setText(titleSchedule);

        if (statusSchedule.equals("checked")) {
            holder.checkBox.setChecked(true);
            holder.checkBox.setTextColor(ContextCompat.getColor(context, gold));
        } if (!statusSchedule.equals("unchecked")){
            holder.checkBox.setChecked(false);
            holder.checkBox.setTextColor(ContextCompat.getColor(context, navy));
        }

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
                    updateStatusChecked();
                } else {
                    checkBox.setTextColor(itemView.getResources().getColor(navy));
                    updateStatusFalse();
                }
            });

        }
    }

    private void updateStatusChecked() {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleModel> call1 = weddingService.getScheduleById(scheduleId);
        call1.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {
                ScheduleModel scheduleModel1 = response.body();
                assert scheduleModel1 != null;
                dateSchedule = scheduleModel1.getDateSchedule();
                idCategory = scheduleModel1.getIdCategory();
                noteSchedule = scheduleModel1.getNoteSchedule();
                userId = scheduleModel1.getUserId();
                month = scheduleModel1.getMonth();
                year = scheduleModel1.getYear();
            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {

            }
        });

        scheduleModel = new ScheduleModel(scheduleId, dateSchedule, titleSchedule, idCategory, noteSchedule, userId, "checked", month, year);

        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleModel> call = weddingService.updateSchedule(scheduleId, scheduleModel);
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {

            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {

            }
        });
    }

    private void updateStatusFalse() {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleModel> call1 = weddingService.getScheduleById(scheduleId);
        call1.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {
                ScheduleModel scheduleModel1 = response.body();
                assert scheduleModel1 != null;
                dateSchedule = scheduleModel1.getDateSchedule();
                idCategory = scheduleModel1.getIdCategory();
                noteSchedule = scheduleModel1.getNoteSchedule();
                userId = scheduleModel1.getUserId();
                month = scheduleModel1.getMonth();
                year = scheduleModel1.getYear();
            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {

            }
        });

        ScheduleModel scheduleModel = new ScheduleModel(scheduleId, dateSchedule, titleSchedule, idCategory, noteSchedule, userId, "unchecked", month, year);

        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleModel> call = weddingService.updateSchedule(scheduleId, scheduleModel);
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
