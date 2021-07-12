package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ListGuestAdapter extends RecyclerView.Adapter<ListGuestAdapter.ViewHolder> {

    private List<GuestModel> guestModels;
    private List<GuestModel> filteredDataList;

    public ListGuestAdapter(List<GuestModel> guestModels) {
        this.guestModels = guestModels;
        this.filteredDataList = guestModels;
    }

    public void setData(List<GuestModel> guestModels) {
        this.guestModels = guestModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_guest, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        // contoh item on click dari movie app
//        viewHolder.cardView.setOnClickListener(view -> {
//            Intent intent = new Intent(parent.getContext(), DetailActivity.class);
//            MovieModel movieModel = new MovieModel();
//            movieModel.setId(movieModels.get(viewHolder.getAdapterPosition()).getId());
//            movieModel.setOriginal_title(movieModels.get(viewHolder.getAdapterPosition()).getOriginal_title());
//
//            intent.putExtra(EXTRA_MOVIE, movieModel);
//            parent.getContext().startActivity(intent);
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GuestModel guestModel = guestModels.get(position);
        String guestName = guestModel.getGuestNama();
        holder.textView.setText(guestName);

    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_listGuest);
            view = itemView.findViewById(R.id.dotStatus);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String key = constraint.toString();
                if (key.isEmpty()) {
                    filteredDataList = guestModels;
                }
                else {
                    List<GuestModel> listFiltered = new ArrayList<>();
                    for (GuestModel row : guestModels) {
                        if (row.getGuestNama().toLowerCase().contains(key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }

                    filteredDataList = listFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredDataList = (List<GuestModel>)results.values;
                notifyDataSetChanged();

            }
        };
    }
}
