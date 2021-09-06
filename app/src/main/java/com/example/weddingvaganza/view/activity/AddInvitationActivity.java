package com.example.weddingvaganza.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.InvitationModel;
import com.example.weddingvaganza.model.responseModel.AddInvitationResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInvitationActivity extends AppCompatActivity {

    private static final String TAG = "AddInvitationActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    public boolean isServicesOk() {
        Log.d(TAG, "isServicesOk: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(AddInvitationActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServicesOk: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOk: an error occured but can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(AddInvitationActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    int Hour, Minute;
    Spinner spinner;
    WeddingService weddingService;
    EditText etGroomName, etGroomFather, etGroomMother, etBrideName, etBrideFather, etBrideMother, etLocation, etNote;
    String titleLocation, date;
    double latitude, longitude;
    Button btnSave;

    int userId = Prefs.getInt("user_id", 0);
    String weddingDate = Prefs.getString("wedding_date", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invitation);

        // OPEN MAP BUTTON
        if (isServicesOk()) {
//            ImageView ivMaps = findViewById(R.id.iv_maps);
            LinearLayout ivMaps = findViewById(R.id.ll_maps);
            ivMaps.setOnClickListener(v -> {
                Intent intent = new Intent(AddInvitationActivity.this, MapsActivity.class);
                startActivityForResult(intent, 1);
            });
        }

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_BackCreateInvitation);
        btnBack.setOnClickListener(v1 -> {
            onBack();
        });

        // SPINNER RETROFIT
        spinner = findViewById(R.id.spinner_invitation);
        List<CategoryModel> categoryModels = new ArrayList<>();
        ArrayAdapter<CategoryModel> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, categoryModels);
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<CategoryModel>> call = weddingService.getCategory(userId);
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    for (CategoryModel model : response.body()) {
                        String title = model.getCategoryTitle();
                        int id = model.getCategoryId();
                        CategoryModel categoryModel = new CategoryModel(id, title);
                        categoryModels.add(categoryModel);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });

        // SET THE WEDDING DATE
        TextView textDate = findViewById(R.id.td_createInvitation);
        textDate.setText(weddingDate);

        // TIME PICKER
        TextView timeSet = findViewById(R.id.tv_timeInvitation);
        LinearLayout timePicker = findViewById(R.id.tp_timeInvitation);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddInvitationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Minute = minute;

                        String time = Hour + ":" + Minute;
                        SimpleDateFormat f24jam = new SimpleDateFormat("HH:mm");
                        try {
                            Date mDate = f24jam.parse(time);
                            SimpleDateFormat f12jam = new SimpleDateFormat("hh:mm aa");

                            date = f12jam.format(mDate);

                            timeSet.setText(date);
                            timeSet.setTextColor(Color.parseColor("#2C3E57"));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(Hour, Minute);
                timePickerDialog.show();
            }
        });

        // BUTTON SAVE
        etGroomName = findViewById(R.id.et_groomName);
        etGroomFather = findViewById(R.id.et_groomFather);
        etGroomMother = findViewById(R.id.et_groomMother);
        etBrideName = findViewById(R.id.et_brideName);
        etBrideFather = findViewById(R.id.et_brideFather);
        etBrideMother = findViewById(R.id.et_brideMother);
        etNote = findViewById(R.id.et_noteInvitation);

        btnSave = findViewById(R.id.btn_nextInvitation);
        btnSave.setOnClickListener(v -> {
            String grooms = etGroomName.getText().toString();
            String groomsFather = etGroomFather.getText().toString();
            String groomsMother = etGroomMother.getText().toString();
            String brides = etBrideName.getText().toString();
            String bridesFather = etBrideFather.getText().toString();
            String bridesMother = etBrideMother.getText().toString();
            String note = etNote.getText().toString();
            String template = "null";

            if (grooms.isEmpty() && groomsFather.isEmpty() && groomsMother.isEmpty() && brides.isEmpty() && bridesFather.isEmpty() && bridesMother.isEmpty() && note.isEmpty() && titleLocation == null) {
                Toast.makeText(this, "Field can not empty", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                addInvitationData(grooms, groomsFather, groomsMother, brides, bridesFather, bridesMother, note, template);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                titleLocation = data.getStringExtra("title location");
                latitude = data.getDoubleExtra("latitude location", 0);
                longitude = data.getDoubleExtra("longitude location", 0);

                etLocation = findViewById(R.id.et_locationInvitation);
                etLocation.setText(titleLocation);
            }
        }
    }

    private void addInvitationData(String grooms, String groomsFather, String groomsMother, String brides, String bridesFather, String bridesMother, String note, String template) {

        // get category id in spinner
        int selectedId = spinner.getSelectedItemPosition();
        CategoryModel getItemId = (CategoryModel) spinner.getItemAtPosition(selectedId);
        int category = getItemId.getCategoryId();

        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<AddInvitationResponse> call1 = weddingService.postInvitation(grooms, groomsFather, groomsMother,
                brides, bridesFather, bridesMother, category, weddingDate, date, titleLocation, longitude, latitude,
                note, userId, template);
        call1.enqueue(new Callback<AddInvitationResponse>() {
            @Override
            public void onResponse(Call<AddInvitationResponse> call, Response<AddInvitationResponse> response) {
                AddInvitationResponse addInvitationResponse = response.body();

                if (addInvitationResponse.getStatus().equals("success")) {

                    Toast.makeText(AddInvitationActivity.this, "Success add data", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddInvitationActivity.this, InvitationTemplateActivity.class);
                    intent.putExtra("category id", category);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddInvitationActivity.this, "Failed add data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddInvitationResponse> call, Throwable t) {
                Toast.makeText(AddInvitationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
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