package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.InvitationModel;
import com.example.weddingvaganza.view.dialog.AddCategoryDialog;
import com.example.weddingvaganza.view.dialog.SuccessInvitationDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvitationTemplateActivity extends AppCompatActivity {

    CardView cvTemplate1, cvTemplate2;
    Button btnComplete;
    List<InvitationModel> invitationModels;
    InvitationModel getInvitationData, updateInvitationModel;

    // var for invitation data
    int invitationId, categoryId, invId;
    String groomName, groomFather, groomMother, brideName, brideFather, brideMother, date, time, location, note, template;
    double longitude, latitude;
    String coba;

    // api needed
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
    int currentUserId = Prefs.getInt("user_id", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_template);

        getInvitationId();

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_backInvitationTemplate);
        btnBack.setOnClickListener(v -> {
            deleteInvitationData();
        });

        // CLICKED TEMPLATE
        cvTemplate1 = findViewById(R.id.cv_template1);
        cvTemplate2 = findViewById(R.id.cv_template2);
        clickedTemplate();


        // BUTTON COMPLETE INVITATION
        btnComplete = findViewById(R.id.btn_completeInvitation);
        btnComplete.setOnClickListener(v -> {
            updateDataInvitation();

        });
    }

    private void deleteInvitationData() {
        Call<InvitationModel> call = weddingService.deleteInvitation(invitationId);
        call.enqueue(new Callback<InvitationModel>() {
            @Override
            public void onResponse(Call<InvitationModel> call, Response<InvitationModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(InvitationTemplateActivity.this, "invitation id; " + invId + " lokasi: "+coba, Toast.LENGTH_SHORT).show();
//                    onBack();
                }
            }

            @Override
            public void onFailure(Call<InvitationModel> call, Throwable t) {

            }
        });
    }

    private void updateDataInvitation() {
        getDataInvitation();

        updateInvitationModel = new InvitationModel(invitationId, groomName, groomFather, groomMother,
                brideName, brideFather, brideMother, categoryId, date, time, location, longitude, latitude,
                note, currentUserId, template);

        Call<InvitationModel> call = weddingService.updateTemplate(invitationId, updateInvitationModel);
        call.enqueue(new Callback<InvitationModel>() {
            @Override
            public void onResponse(Call<InvitationModel> call, Response<InvitationModel> response) {
                if (response.isSuccessful()){
                    openDialog();
                } else {
                    Toast.makeText(InvitationTemplateActivity.this, "Failed complete your invitation", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InvitationModel> call, Throwable t) {
                Toast.makeText(InvitationTemplateActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataInvitation() {
//        getInvitationId();

        getInvitationData = getIntent().getParcelableExtra("invitation model");

        invitationModels = new ArrayList<>();
        invitationModels.add(getInvitationData);
        for (InvitationModel model : invitationModels) {
            categoryId = model.getIdCategory();
        }

        groomName = getInvitationData.getGroomsName();
        groomFather = getInvitationData.getGroomsFather();
        groomMother = getInvitationData.getGroomsMother();
        brideName = getInvitationData.getBridesName();
        brideFather = getInvitationData.getBridesFather();
        brideMother = getInvitationData.getBridesMother();
        date = getInvitationData.getDateInvitation();
        time = getInvitationData.getTimeInvitation();
        location = getInvitationData.getTitleLocation();
        longitude = getInvitationData.getLongitude();
        latitude = getInvitationData.getLatitude();
        note = getInvitationData.getNoteInvitation();
        template = "Default Template";

    }

    private void getInvitationId() {
        Call<List<InvitationModel>> call = weddingService.getInvitation(currentUserId);
        call.enqueue(new Callback<List<InvitationModel>>() {
            @Override
            public void onResponse(Call<List<InvitationModel>> call, Response<List<InvitationModel>> response) {
                List<InvitationModel> modelList = response.body();
                InvitationModel data = modelList.get(0);
                List<InvitationModel> modelList1 = new ArrayList<>();
                modelList1.add(data);
                for (InvitationModel model : modelList1) {
                    invId = model.getIdInvitation();
                    coba = model.getTitleLocation();
                }



//                if (response.isSuccessful()) {
//
//
////                    for (InvitationModel model : modelList) {
//////                        invitationId = model.getIdInvitation();
////                    }
//                } else {
//                    Toast.makeText(InvitationTemplateActivity.this, "can't get invitation id", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<List<InvitationModel>> call, Throwable t) {
                Toast.makeText(InvitationTemplateActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void openDialog() {
        SuccessInvitationDialog successInvitationDialog = new SuccessInvitationDialog();
        successInvitationDialog.show(getSupportFragmentManager(), "success invitation");
    }

    private void clickedTemplate() {
        // clicked template 1
        cvTemplate1.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(InvitationTemplateActivity.this, R.style.BottomSheetDialogTheme);

            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, findViewById(R.id.ll_bottomSheetDialog));

            // button choose template in bottom sheet dialog
            bottomSheetView.findViewById(R.id.btn_chooseBottomDialog).setOnClickListener(v1 -> {
                Toast.makeText(this, "Choosing this template", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                cvTemplate1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.champagne));
                cvTemplate2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            });

            // button close in bottom sheet dialog
            bottomSheetView.findViewById(R.id.btn_closeBottomDialog).setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
                cvTemplate1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });

        // clicked template 2
        cvTemplate2.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(InvitationTemplateActivity.this, R.style.BottomSheetDialogTheme);

            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_dialog, findViewById(R.id.ll_bottomSheetDialog));

            // button choose template in bottom sheet dialog
            bottomSheetView.findViewById(R.id.btn_chooseBottomDialog).setOnClickListener(v1 -> {
                Toast.makeText(this, "Choosing this template", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                cvTemplate2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.champagne));
                cvTemplate1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            });

            // button close in bottom sheet dialog
            bottomSheetView.findViewById(R.id.btn_closeBottomDialog).setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
                cvTemplate2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    private void onBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}