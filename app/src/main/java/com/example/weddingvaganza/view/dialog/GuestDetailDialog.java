package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.model.UpdateGuestModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestDetailDialog extends AppCompatDialogFragment {

    GuestModel guestModel;
    TextView guestName, status, email, phoneNumber, address;
    int guestId;
    UpdateGuestModel updateGuestModel;
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_guestdetail, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // CLOSE BUTTON DIALOG
        Button close = view.findViewById(R.id.btn_closeGuestDetail);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // INVITE BUTTON
        Button invite = view.findViewById(R.id.btn_inviteGuestDetail);
        invite.setOnClickListener(v -> {
            String status = "invited";
            updateGuestModel = new UpdateGuestModel(guestId, guestModel.getClassId(), guestModel.getGuestNama(),
                    guestModel.getGuestNoHp(),guestModel.getGuestEmail(), guestModel.getUserId(),
                    guestModel.getHomeAddress(), status);
            updateStatusInvited();
            alertDialog.dismiss();
        });

        // SET DETAIL DATA
        guestModel = this.getArguments().getParcelable("guest selected");

        guestName = view.findViewById(R.id.tv_nameGuestDetail);
        status = view.findViewById(R.id.tv_statusGuestDetail);
        email = view.findViewById(R.id.tv_mailGuestDetail);
        phoneNumber = view.findViewById(R.id.tv_phoneGuestDetail);
        address = view.findViewById(R.id.tv_addressGuestDetail);

        guestId = guestModel.getGuestId();
        guestName.setText(guestModel.getGuestNama());
        status.setText(guestModel.getStatus());
        email.setText(guestModel.getGuestEmail());
        phoneNumber.setText(guestModel.getGuestNoHp());
        address.setText(guestModel.getHomeAddress());

        return alertDialog;
    }

    private void updateStatusInvited() {
        Call<GuestModel> call = weddingService.putStatus(guestId, updateGuestModel);
        call.enqueue(new Callback<GuestModel>() {
            @Override
            public void onResponse(Call<GuestModel> call, Response<GuestModel> response) {
                Toast.makeText(getContext(), "Guest Invited", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GuestModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error Invite Guest", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
