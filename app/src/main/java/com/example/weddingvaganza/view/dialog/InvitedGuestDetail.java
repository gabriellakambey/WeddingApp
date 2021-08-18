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

import java.util.List;

public class InvitedGuestDetail extends AppCompatDialogFragment {

    TextView tvName, tvEmail, tvPhoneNum, tvAddress;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_invitedguestdetail, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // CLOSE BUTTON DIALOG
        Button close = view.findViewById(R.id.btn_backInvitedDetail);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // SET DATA DETAIL GUEST
        GuestModel guestModel = this.getArguments().getParcelable("invited selected");

        tvName = view.findViewById(R.id.tv_nameInvitedDetail);
        tvName.setText(guestModel.getGuestNama());

        tvEmail = view.findViewById(R.id.tv_mailInvitedDetail);
        tvEmail.setText(guestModel.getGuestEmail());

        tvPhoneNum = view.findViewById(R.id.tv_phoneInvitedDetail);
        tvPhoneNum.setText(guestModel.getGuestNoHp());

        tvAddress = view.findViewById(R.id.tv_addressInvitedDetail);
        tvAddress.setText(guestModel.getHomeAddress());

        return alertDialog;
    }

}
