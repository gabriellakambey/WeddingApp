package com.example.weddingvaganza.view.activity;

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
    private int userId = Prefs.getInt("user_id", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invitation);

        // OPEN MAP BUTTON
        if (isServicesOk()) {
            ImageView ivMaps = findViewById(R.id.iv_maps);
            ivMaps.setOnClickListener(v -> {
                startActivity(new Intent(AddInvitationActivity.this, MapsActivity.class));
            });
        }

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_BackCreateInvitation);
        btnBack.setOnClickListener(v -> {
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
        String weddingDate = Prefs.getString("wedding_date", null);
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
                        ,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Minute = minute;

                        String time = Hour + ":" + Minute;
                        SimpleDateFormat f24jam = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24jam.parse(time);
                            SimpleDateFormat f12jam = new SimpleDateFormat("hh:mm aa");

                            timeSet.setText(f12jam.format(date));
                            timeSet.setTextColor(Color.parseColor("#2C3E57"));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(Hour,Minute);
                timePickerDialog.show();
            }
        });

        // BUTTON SAVE
        EditText location = findViewById(R.id.et_locationInvitation);
        location.setText(getIntent().getStringExtra("title location"));

    }

    private void onBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0){
            super.onBackPressed();
        }
        else {
            getSupportFragmentManager().popBackStack();
        }
    }

}