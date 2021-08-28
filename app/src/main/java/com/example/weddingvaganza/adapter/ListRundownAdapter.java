package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.RundownModel;

import java.util.List;

public class ListRundownAdapter extends RecyclerView.Adapter<ListRundownAdapter.ViewHolder> {

    private List<RundownModel> rundownModelList;
    private Context context;

    public ListRundownAdapter(List<RundownModel> rundownModelList) {
        this.rundownModelList = rundownModelList;
        notifyDataSetChanged();
    }

    public void setData(List<RundownModel> rundownModelList) {
        this.rundownModelList = rundownModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_rundown, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RundownModel rundownModel = rundownModelList.get(position);

        holder.tvTime.setText(rundownModel.getTime());
        holder.tvTitle.setText(rundownModel.getRundownTitle());
    }

    @Override
    public int getItemCount() {
        return rundownModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTime, tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.tv_timeRundown);
            tvTitle = itemView.findViewById(R.id.tv_titleRundown);
        }
    }
}
