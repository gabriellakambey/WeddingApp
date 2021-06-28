package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.GuestGroupModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GuestGroupAdapter extends RecyclerView.Adapter<GuestGroupAdapter.ViewHolder> {
    private List<GuestGroupModel> guestGroupModels;
    private Context context;
    ClickedItem listener;

    public GuestGroupAdapter(ClickedItem listener) {
        this.listener = listener;
    }

    public void setData(List<GuestGroupModel> guestGroupModels) {
        this.guestGroupModels = guestGroupModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new GuestGroupAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_guest_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GuestGroupModel guestGroupModel = guestGroupModels.get(position);
        String title = guestGroupModel.getKelas();
        int currentId = guestGroupModel.getClassId();

        holder.textView.setText(title);
        holder.imageView.setOnClickListener(v -> {
            listener.ClickedGuestGroup(guestGroupModel);
        });

    }

    public interface ClickedItem {
        public void ClickedGuestGroup(GuestGroupModel guestGroupModel);
    }

    @Override
    public int getItemCount() {
        return guestGroupModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_listGuestGroup);
            imageView = itemView.findViewById(R.id.iv_moreGuestGroup);
        }
    }
}
