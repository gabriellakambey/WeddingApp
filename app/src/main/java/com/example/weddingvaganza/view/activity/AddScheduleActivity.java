package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.responseModel.AddScheduleResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddScheduleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner spinner;
    private Button btnBack;
    private LinearLayout datePicker;
    private TextView textDate;
    EditText etDate, etTitle, etNote;
    private int currentUserId = Prefs.getInt("user_id", 0);
    String status;
    AddScheduleListener listener;
    WeddingService weddingService;
    int monthDate;
    int yearDate;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        spinner = findViewById(R.id.spinner_addSchedule);
        btnBack = findViewById(R.id.btn_BackAddSchedule);

        // button back
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // date picker
        textDate = findViewById(R.id.td_addSchedule);
        datePicker = findViewById(R.id.dp_addSchedule);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        // retrofit spinner
        List<CategoryModel> category = new ArrayList<>();
        ArrayAdapter<CategoryModel> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, category);
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
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

        // button save
        etTitle = findViewById(R.id.et_titleAddSchedule);
        etNote = findViewById(R.id.et_noteAddSchedule);

        Button btnSave = findViewById(R.id.btn_saveAddSchedule);
        btnSave.setOnClickListener(v -> {
            String date = textDate.getText().toString();
            String title = etTitle.getText().toString();
            String note = etNote.getText().toString();

            // get selected item id
            int selectedId = spinner.getSelectedItemPosition();
            CategoryModel getItemId = (CategoryModel) spinner.getItemAtPosition(selectedId);
            int categoryId = getItemId.getCategoryId();

            status  = "unchecked";
            weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
            Call<AddScheduleResponse> responseCall = weddingService.addNewSchedule(date, title, categoryId, note, currentUserId, status, monthDate, yearDate);
            responseCall.enqueue(new Callback<AddScheduleResponse>() {
                @Override
                public void onResponse(Call<AddScheduleResponse> call, Response<AddScheduleResponse> response) {
                    AddScheduleResponse addScheduleResponse = response.body();
                    if (addScheduleResponse.getStatus().equals("success")) {

//                        RelativeLayout relativeLayout = findViewById(R.id.rl_addScheduleLayout);
//                        relativeLayout.setVisibility(View.GONE);
//
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.fl_addScheduleLayout, new ScheduleFragment()).commit();

//                        listener.onAddSchedule();
                        Toast.makeText(AddScheduleActivity.this, "Success add data", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    } else {
                        Toast.makeText(AddScheduleActivity.this, "Failed add data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddScheduleResponse> call, Throwable t) {
                    Toast.makeText(AddScheduleActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        monthDate = month;
        yearDate = year;
        String date = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(date);
    }

    public void setListener(AddScheduleListener listener) {
        this.listener = listener;
    }

    public interface AddScheduleListener {
        void onAddSchedule();
    }
}