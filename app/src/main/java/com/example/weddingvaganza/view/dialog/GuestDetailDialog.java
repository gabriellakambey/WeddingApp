package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.GuestModel;

import org.jetbrains.annotations.NotNull;

public class GuestDetailDialog extends AppCompatDialogFragment {

    GuestModel guestModel;
    TextView guestName, status, email, phoneNumber, address;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_guestdetail, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // close button dialog
        Button close = view.findViewById(R.id.btn_closeGuestDetail);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // invite button dialog
        Button invite = view.findViewById(R.id.btn_inviteGuestDetail);
        invite.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // set detail data
        guestModel = this.getArguments().getParcelable("guest selected");

        guestName = view.findViewById(R.id.tv_nameGuestDetail);
        status = view.findViewById(R.id.tv_statusGuestDetail);
        email = view.findViewById(R.id.tv_mailGuestDetail);
        phoneNumber = view.findViewById(R.id.tv_phoneGuestDetail);
        address = view.findViewById(R.id.tv_addressGuestDetail);

        guestName.setText(guestModel.getGuestNama());
        status.setText("Not Invited");
        email.setText(guestModel.getGuestEmail());
        phoneNumber.setText(guestModel.getGuestNoHp());
        address.setText(guestModel.getHomeAddress());

        return alertDialog;
    }

}
