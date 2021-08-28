package com.example.weddingvaganza.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.view.activity.GuestsActivity;
import com.example.weddingvaganza.view.activity.HomeActivity;

public class SuccessInvitationDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_success_invitation, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // BUTTON LATER
        Button btnLater = view.findViewById(R.id.btn_laterSuccessDialog);
        btnLater.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), HomeActivity.class));
            alertDialog.dismiss();
        });

        // BUTTON INVITE FRIENDS
        Button btnInviteFriends = view.findViewById(R.id.btn_inviteFriendSuccessDialog);
        btnInviteFriends.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), GuestsActivity.class));
            alertDialog.dismiss();
        });

        return alertDialog;
    }
}
