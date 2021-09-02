package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.example.weddingvaganza.model.GuestStatusModel;
import com.example.weddingvaganza.model.GuestUpdateModel;
import com.example.weddingvaganza.model.InvitationModel;
import com.example.weddingvaganza.view.activity.GuestListActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestDetailDialog extends AppCompatDialogFragment {

    GuestModel guestModel;
    TextView guestName, status, email, phoneNumber, address;
    int guestId, currentGroup, invitationSize;
    LinearLayout llEnable, llDisable;
    GuestUpdateModel guestUpdateModel;
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
    DetailDialogListener listener;
    AlertDialog alertDialog;
    int currentUserId = Prefs.getInt("user_id", 0);
    Button close1, invite, close;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_guestdetail, null);

        builder.setView(view);

        alertDialog = builder.create();

        // SET DETAIL DATA
        guestModel = this.getArguments().getParcelable("guest selected");
        List<GuestModel> model = new ArrayList<>();
        model.add(guestModel);
        for (GuestModel post : model) {
            currentGroup = post.getClassId();
        }

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

        // INVITE BUTTON IN CONDITION
        llEnable = view.findViewById(R.id.ll_enableInvite);
        llDisable = view.findViewById(R.id.ll_disableInvite);
        close1 = view.findViewById(R.id.btn_closeGuestDetail1);
        invite = view.findViewById(R.id.btn_inviteGuestDetail);
        close = view.findViewById(R.id.btn_closeGuestDetail);
        getInvitationSize();

        return alertDialog;
    }

    private void getInvitationSize() {
        Call<List<InvitationModel>> call = weddingService.getInvitation(currentUserId);
        call.enqueue(new Callback<List<InvitationModel>>() {
            @Override
            public void onResponse(Call<List<InvitationModel>> call, Response<List<InvitationModel>> response) {
                List<InvitationModel> invitationModel = response.body();
                if (invitationModel.size() == 0) {
                    llDisable.setVisibility(View.VISIBLE);
                    llEnable.setVisibility(View.GONE);
                    close1.setOnClickListener(v -> {
                        alertDialog.dismiss();
                    });

                } else {
                    llEnable.setVisibility(View.VISIBLE);
                    llDisable.setVisibility(View.GONE);


                    invite.setOnClickListener(v1 -> {
                        getGuestById();
                        alertDialog.dismiss();
                    });

                    close.setOnClickListener(v -> {
                        alertDialog.dismiss();
                    });

                }
            }

            @Override
            public void onFailure(Call<List<InvitationModel>> call, Throwable t) {

            }
        });
    }

    private void getGuestById() {
        Call<GuestModel> call = weddingService.getGuestById(guestId);
        call.enqueue(new Callback<GuestModel>() {
            @Override
            public void onResponse(Call<GuestModel> call, Response<GuestModel> response) {
                GuestModel data = response.body();
                if (response.isSuccessful()) {
                    String status = "invited";
                    guestUpdateModel = new GuestUpdateModel(data.getGuestId(), data.getClassId(), data.getGuestNama(),
                            data.getGuestNoHp(), data.getGuestEmail(), data.getUserId(), data.getHomeAddress(), status);

                    updateStatusInvited();
                }

            }

            @Override
            public void onFailure(Call<GuestModel> call, Throwable t) {

            }
        });
    }

    private void updateStatusInvited() {
        Call<GuestUpdateModel> call = weddingService.putStatus(guestId, guestUpdateModel);
        call.enqueue(new Callback<GuestUpdateModel>() {
            @Override
            public void onResponse(Call<GuestUpdateModel> call, Response<GuestUpdateModel> response) {
                if (response.isSuccessful()) {
                    listener.closeDialog();
//                    Toast.makeText(getContext(), "Guest Invited", Toast.LENGTH_SHORT).show();
                } else {
                    listener.closeDialog();
//                    Toast.makeText(getContext(), "Can't invite guest", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GuestUpdateModel> call, Throwable t) {

            }
        });
    }

    public interface DetailDialogListener {
        public void closeDialog ();
    }

    public void setListener (DetailDialogListener listener) {
        this.listener = listener;
    }

}
