package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;

public class InvitedGuestDetail extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_invitedguestdetail, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // close button dialog
        Button close = view.findViewById(R.id.btn_inviteGuestDetail);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        return alertDialog;
    }

}
