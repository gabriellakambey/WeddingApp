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
import com.example.weddingvaganza.model.UpdateScheduleModel;
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
    private ListScheduleCallback listScheduleCallback;
    
    int scheduleId, idCategory, userId, month, year;
    String dateSchedule, titleSchedule, noteSchedule, statusSchedule;
    ScheduleModel scheduleModel;
    WeddingService weddingService;

    public void setData(List<ScheduleModel> schedule) {
        this.schedule = schedule;
        notifyDataSetChanged();
    }

    public void setListener(ListScheduleCallback listScheduleCallback){
        this.listScheduleCallback = listScheduleCallback;
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
        } else{
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
                    listScheduleCallback.onChecked(schedule.get(getAdapterPosition()).getScheduleId(), checkBox.isChecked());
                } else {
                    checkBox.setTextColor(itemView.getResources().getColor(navy));
                    listScheduleCallback.onChecked(schedule.get(getAdapterPosition()).getScheduleId(), checkBox.isChecked());
                }
            });

        }
    }

    public interface ListScheduleCallback{
        public void onChecked(int scheduleId, Boolean isChecked);
    }




}
