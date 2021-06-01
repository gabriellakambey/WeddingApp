package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.fragment.CategoryFragment;
import com.example.weddingvaganza.model.AddCategoryResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText etCatTitle, etScheduleTitle, etDate, etNote;
    private TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Button btnBackCategory = findViewById(R.id.btnBackCategory);

        btnBackCategory.setOnClickListener(v -> {
            onBack();
        });

        textDate = findViewById(R.id.textDateCategory);
        ImageView datePicker = findViewById(R.id.datePickerCategory);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        etCatTitle = findViewById(R.id.et_categoryTitle);
        etScheduleTitle = findViewById(R.id.et_scheduleTitleCat);
        etDate = findViewById(R.id.textDateCategory);
        etNote = findViewById(R.id.et_noteCategory);

        Button btnSave = findViewById(R.id.btn_saveCategory);
        btnSave.setOnClickListener(v -> {
            String title = etCatTitle.getText().toString();
            String schedule = etScheduleTitle.getText().toString();
            String date = etDate.getText().toString();
            String note = etNote.getText().toString();

            WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
            Call<AddCategoryResponse> call = weddingService.addCategory(title, schedule, date, note);
            call.enqueue(new Callback<AddCategoryResponse>() {
                @Override
                public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                    AddCategoryResponse addCategoryResponse = response.body();
                    if (addCategoryResponse.getStatus().equals("success")) {
                        Intent intent = new Intent(AddCategoryActivity.this, CategoryFragment.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddCategoryActivity.this, "Failed Add Category", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                    Toast.makeText(AddCategoryActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        });
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
        String date = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(date);
    }
}