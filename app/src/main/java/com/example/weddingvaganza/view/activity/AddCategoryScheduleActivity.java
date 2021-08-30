package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.model.responseModel.AddCategoryResponse;
import com.example.weddingvaganza.model.responseModel.AddScheduleResponse;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryScheduleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // api needed
    private final String TAG = "AddCategoryScheduleActivity";
    int currentUserId = Prefs.getInt("user_id", 0);
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    // vars
    LinearLayout llSchedule;
    EditText etCategory, etTitle, etNote;
    String category, date, title, note, status;
    Button btnNext;
    TextView tvCategory, tvDate;
    int categoryId, monthDate, yearDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_schedule);

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_BackAddCategorySchedule);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // BUTTON NEXT
        llSchedule = findViewById(R.id.ll_addSchedule);
        tvCategory = findViewById(R.id.tv_titleCategory);
        etCategory = findViewById(R.id.et_titleCategory);

        btnNext = findViewById(R.id.btn_saveCategory);
        btnNext.setOnClickListener(v -> {
            category = etCategory.getText().toString();
            if (!category.isEmpty()) {
                postCategory();
            } else {
                Toast.makeText(this, "Field can not empty", Toast.LENGTH_SHORT).show();
            }
        });

        // DATE PICKER
        LinearLayout datePicker = findViewById(R.id.dp_addScheduleFirst);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        // BUTTON SAVE
        tvDate = findViewById(R.id.td_addScheduleFirst);
        etTitle = findViewById(R.id.et_titleAddScheduleFirst);
        etNote = findViewById(R.id.et_noteAddScheduleFirst);

        Button btnSave = findViewById(R.id.btn_saveAddScheduleFirst);
        btnSave.setOnClickListener(v -> {
            title = etTitle.getText().toString();
            note = etNote.getText().toString();
            status = "unchecked";

            if (date != null && !title.isEmpty() && !note.isEmpty()) {
                Log.d(TAG, "onCreate: " + categoryId);
                Toast.makeText(this, "date: " + date + " title: " + title + " note: " + note + " id; " + categoryId, Toast.LENGTH_SHORT).show();
                postSchedule();
            } else if (date == null) {
                Toast.makeText(this, "Field can not be empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Field can not be empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void postSchedule() {
        Call<AddScheduleResponse> call = weddingService.addNewSchedule(date, title, categoryId, note, currentUserId, status, monthDate, yearDate);
        call.enqueue(new Callback<AddScheduleResponse>() {
            @Override
            public void onResponse(Call<AddScheduleResponse> call, Response<AddScheduleResponse> response) {
                AddScheduleResponse addScheduleResponse = response.body();
                if (addScheduleResponse.getStatus().equals("success")) {
                    Toast.makeText(AddCategoryScheduleActivity.this, "Success add category schedule", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCategoryScheduleActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddCategoryScheduleActivity.this, "Failed add category schedule", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddScheduleResponse> call, Throwable t) {
                Toast.makeText(AddCategoryScheduleActivity.this, "Server error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void postCategory() {
        Call<AddCategoryResponse> call = weddingService.addCategory(category, currentUserId);
        call.enqueue(new Callback<AddCategoryResponse>() {
            @Override
            public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                AddCategoryResponse addCategoryResponse = response.body();
                if (addCategoryResponse.getStatus().equals("success")) {
                    getCategoryId();

                    etCategory.setVisibility(View.GONE);
                    tvCategory.setVisibility(View.VISIBLE);
                    tvCategory.setText(AddCategoryScheduleActivity.this.category);
                    llSchedule.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.GONE);
                } else {
                    Toast.makeText(AddCategoryScheduleActivity.this, "Failed add category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                Toast.makeText(AddCategoryScheduleActivity.this, "Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCategoryId() {
        Call<List<CategoryModel>> call = weddingService.getCategory(currentUserId);
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if (response.isSuccessful()) {
                    List<CategoryModel> categoryModelList = response.body();
                    if (categoryModelList.size() != 0) {
                        CategoryModel data = categoryModelList.get(0);
                        categoryId = data.getCategoryId();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

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
        tvDate.setText(date);
        tvDate.setTextColor(ContextCompat.getColor(this, R.color.navy));
    }
}