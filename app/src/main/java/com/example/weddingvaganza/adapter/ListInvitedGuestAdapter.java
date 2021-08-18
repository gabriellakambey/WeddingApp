package com.example.weddingvaganza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.GuestModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListInvitedGuestAdapter extends RecyclerView.Adapter<ListInvitedGuestAdapter.ViewHolder> {

    private Context context;
    private List<GuestModel> guestModels;
    ClickedItem clickedItem;

    public ListInvitedGuestAdapter(Context context, List<GuestModel> guestModels) {
        this.context = context;
        this.guestModels = guestModels;
    }

    public void clickedInvitedListener(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setInvitedGuest(List<GuestModel> guestModels) {
        this.guestModels = guestModels;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_invited_guest, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        GuestModel guestModel = guestModels.get(position);

        String guestName = guestModel.getGuestNama();
        holder.tvName.setText(guestName);

        int guestGroupId = guestModel.getGuestId();
        if (guestGroupId == 5) {
            holder.tvGroup.setText("Bride's Family");
        } if (guestGroupId == 6) {
            holder.tvGroup.setText("Groom's Family");
        } if (guestGroupId == 7) {
            holder.tvGroup.setText("Bride's Friend");
        } if (guestGroupId == 8) {
            holder.tvGroup.setText("Groom's Friend");
        }

        holder.cvListInvited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedInvitedGuest(guestModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return guestModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvGroup;
        public CardView cvListInvited;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_listInvitedGuest);
            tvGroup = itemView.findViewById(R.id.tv_invitedGuestGroup);
            cvListInvited = itemView.findViewById(R.id.cv_listInvitedGuest);
        }
    }

    public interface ClickedItem {
        public void ClickedInvitedGuest(GuestModel guestModel);
    }
}
