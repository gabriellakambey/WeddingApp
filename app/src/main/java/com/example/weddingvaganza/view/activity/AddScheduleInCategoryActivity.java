package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.responseModel.AddScheduleResponse;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddScheduleInCategoryActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    int monthDate, yearDate;
    EditText etTitle, etNote;
    TextView textDate;
    LinearLayout datePicker;
    String status, categoryTitle, date;
    int categoryId;
    int currentUserId = Prefs.getInt("user_id", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_in_category);

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_BackAddScheduleInCategory);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // DATE PICKER
        textDate = findViewById(R.id.td_addScheduleInCategory);
        datePicker = findViewById(R.id.dp_addScheduleInCategory);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        // GET CURRENT CATEGORY ID & TITLE
        categoryId = getIntent().getIntExtra("category id", 0);
        categoryTitle = getIntent().getStringExtra("category title");

        // SET DEFAULT CATEGORY EVENT
        TextView currentCategoryTitle = findViewById(R.id.tv_currentCategory);
        currentCategoryTitle.setText(categoryTitle);

        // BUTTON SAVE
        etTitle = findViewById(R.id.et_titleAddScheduleInCategory);
        etNote = findViewById(R.id.et_noteAddScheduleInCategory);

        Button btnSave = findViewById(R.id.btn_saveAddScheduleInCategory);
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String note = etNote.getText().toString();
            status  = "unchecked";

            if (title.isEmpty() && note.isEmpty()) {
                Toast.makeText(this, "Field can not empty", Toast.LENGTH_SHORT).show();
            } else {
                onAddSchedule(title, note);
            }

        });

    }

    private void onAddSchedule(String title, String note) {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<AddScheduleResponse> call = weddingService.addNewSchedule(date, title, categoryId, note, currentUserId, status, monthDate, yearDate);
        call.enqueue(new Callback<AddScheduleResponse>() {
            @Override
            public void onResponse(Call<AddScheduleResponse> call, Response<AddScheduleResponse> response) {
                AddScheduleResponse addScheduleResponse = response.body();
                if (addScheduleResponse.getStatus().equals("success")) {
                    Toast.makeText(AddScheduleInCategoryActivity.this, "Success add schedule", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(AddScheduleInCategoryActivity.this, "Failed add data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddScheduleResponse> call, Throwable t) {

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

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        monthDate = month;
        yearDate = year;
        date = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(date);
        textDate.setTextColor(ContextCompat.getColor(this, R.color.navy));
    }

}