package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.responseModel.AddRundownResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weddingvaganza.R.*;

public class AddRundownActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnBack;
    private EditText etTitle, etPersonIC, etNote;
    int Hour, Minute;
    String timeRundown;
    int currentUserId = Prefs.getInt("user_id", 0);
    TextView tvTimeRundown;
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_add_rundown);

        // Button Back
        btnBack = findViewById(id.btn_BackAddRundown);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // Set time
        tvTimeRundown = findViewById(id.tv_timeAddRundown);
        LinearLayout timePicker = findViewById(id.timeAddRundown);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddRundownActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Minute = minute;

                        String time = Hour + ":" + Minute;
                        SimpleDateFormat f24jam = new SimpleDateFormat("HH:mm");
//                        timeRundown = time;
                        try {
                            Date date = f24jam.parse(time);
                            SimpleDateFormat f12jam = new SimpleDateFormat("hh:mm aa");

                            timeRundown = f24jam.format(date);

                            tvTimeRundown.setText(f12jam.format(date));
                            tvTimeRundown.setTextColor(Color.parseColor("#2C3E57"));

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

        // retrofit spinner
        spinner = findViewById(id.spinner_addRundown);
        List<CategoryModel> category = new ArrayList<>();
        ArrayAdapter<CategoryModel> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, category);

        Call<List<CategoryModel>> call = weddingService.getCategory(currentUserId);
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    for (CategoryModel post : response.body()) {

                        String title = post.getCategoryTitle();
                        int id = post.getCategoryId();
                        CategoryModel categoryModel = new CategoryModel(id, title);
                        category.add(categoryModel);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });

        // BUTTON SAVE
        etTitle = findViewById(id.et_titleAddRundown);
        etPersonIC = findViewById(id.et_personAddRundown);
        etNote = findViewById(id.et_noteAddRundown);

        Button btnSave = findViewById(id.btn_saveAddRundown);
        btnSave.setOnClickListener(v -> {
            onButtonSave();
        });

    }

    private void onButtonSave() {
        String title = etTitle.getText().toString();
        String pj = etPersonIC.getText().toString();
        String note = etNote.getText().toString();
        String status = "false";

        // get selected item id
        int selectedId = spinner.getSelectedItemPosition();
        CategoryModel getItemId = (CategoryModel) spinner.getItemAtPosition(selectedId);
        int categoryId = getItemId.getCategoryId();

        Call<AddRundownResponse> call1 = weddingService.addRundown(timeRundown, title, categoryId, note, pj, currentUserId, status);
        call1.enqueue(new Callback<AddRundownResponse>() {
            @Override
            public void onResponse(Call<AddRundownResponse> call, Response<AddRundownResponse> response) {
                AddRundownResponse addRundownResponse = response.body();
                if (addRundownResponse.getStatus().equals("success")) {
                    Toast.makeText(AddRundownActivity.this, "Success add rundown", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddRundownActivity.this, "Failed add rundown", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddRundownResponse> call, Throwable t) {
                Toast.makeText(AddRundownActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
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