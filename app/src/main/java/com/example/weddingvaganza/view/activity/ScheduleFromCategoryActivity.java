package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListScheduleAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.ScheduleModel;
import com.example.weddingvaganza.model.UpdateScheduleModel;
import com.example.weddingvaganza.model.schedulebyid.ScheduleByIdModel;
import com.example.weddingvaganza.view.dialog.AddCategoryDialog;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFromCategoryActivity extends AppCompatActivity implements ListScheduleAdapter.ListScheduleCallback, AddScheduleInCategoryActivity.ScheduleCategoryCallBack {

    TextView categoryTitle, monthYear;
    Button btnAdd, btnBack;
    ImageView calendar;
    RecyclerView recyclerView;
    CategoryModel categoryModel;
    ListScheduleAdapter adapter;
    int currentCategoryId;
    String currentCategoryTitle;
    ScheduleModel scheduleById;
    UpdateScheduleModel updateScheduleModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_from_category);

        // BUTTON BACK
        btnBack = findViewById(R.id.btn_backSchedFrmCat);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // BUTTON ADD SCHEDULE
        btnAdd = findViewById(R.id.btn_addSchdFrmCat);
        btnAdd.setOnClickListener(v -> {
            AddScheduleInCategoryActivity activity = new AddScheduleInCategoryActivity();
            activity.setListenerCallback(this::onAddScheduleInCategory);

            Intent intent = new Intent(this, AddScheduleInCategoryActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("category id", currentCategoryId);
            extras.putString("category title", currentCategoryTitle);
            intent.putExtras(extras);
            startActivity(intent);
        });

        // SET TOOLBAR TITLE
        categoryTitle = findViewById(R.id.tv_toolbarCatTitle);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            categoryModel = (CategoryModel) intent.getSerializableExtra("data");
            currentCategoryId = categoryModel.getCategoryId();

            currentCategoryTitle = categoryModel.getCategoryTitle();
            categoryTitle.setText(currentCategoryTitle);

        }

        // set current month and year
        monthYear = findViewById(R.id.tv_dateSchdFrmCat);
        Calendar currentMonthYear = Calendar.getInstance();
        int currentMonth = currentMonthYear.get(Calendar.MONTH);
        currentMonth = currentMonth + 1;
        int currentYear = currentMonthYear.get(Calendar.YEAR);
        monthYear.setText(makeMonthYearString(currentMonth, currentYear));

        // month year picker dialog
        calendar = findViewById(R.id.iv_calendarSchdFrmCat);
        calendar.setOnClickListener(v -> {
            final Calendar today = Calendar.getInstance();
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH);

            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(this,
                    new MonthPickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(int selectedMonth, int selectedYear) {
                            selectedMonth = selectedMonth + 1;
                            String selected = makeMonthYearString(selectedMonth, selectedYear);
                            monthYear.setText(selected);
                        }
                    }, year, month);

            builder.setActivatedMonth(month)
                    .setMinYear(year - 1)
                    .setActivatedYear(year)
                    .setMaxYear(2030)
                    .setTitle("Select Month and Year")
                    .build()
                    .show();
        });

        // recyclerview schedule
        recyclerView = findViewById(R.id.rv_schdFrmCat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new ListScheduleAdapter();

        getSchedule();

    }

    private void getSchedule() {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<ScheduleModel>> call = weddingService.getScheduleByCategory(currentCategoryId);
        call.enqueue(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                if (response.isSuccessful()) {
                    List<ScheduleModel> scheduleModels = response.body();
                    adapter.setData(scheduleModels);
                    adapter.setListener(ScheduleFromCategoryActivity.this::onChecked);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {

            }
        });

    }

    private void getScheduleById(int scheduleId, Boolean isChecked) {

        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleByIdModel> call1 = weddingService.getScheduleById(scheduleId);
        call1.enqueue(new Callback<ScheduleByIdModel>() {
            @Override
            public void onResponse(Call<ScheduleByIdModel> call, Response<ScheduleByIdModel> response) {
                ScheduleByIdModel data = response.body();
                String status = isChecked ? "checked" : "unchecked";
                updateScheduleModel = new UpdateScheduleModel(scheduleId, data.getDate(), data.getTitle(), data.getFkCategoryId().getCategoryId(), data.getNote(), data.getFkUserId().getUserId(), status, data.getMonth(), data.getYear());
                updateStatus(scheduleId);
            }

            @Override
            public void onFailure(Call<ScheduleByIdModel> call, Throwable t) {

            }
        });
    }

    private void updateStatus(Integer scheduleId) {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<ScheduleModel> call = weddingService.updateSchedule(scheduleId, updateScheduleModel);
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {
                Toast.makeText(ScheduleFromCategoryActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {

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

    private String makeMonthYearString(int selectedMonth, int selectedYear) {
        return getMonthFormat(selectedMonth) + " " + selectedYear;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "January";
        if (month == 2)
            return "February";
        if (month == 3)
            return "March";
        if (month == 4)
            return "April";
        if (month == 5)
            return "May";
        if (month == 6)
            return "June";
        if (month == 7)
            return "July";
        if (month == 8)
            return "August";
        if (month == 9)
            return "September";
        if (month == 10)
            return "October";
        if (month == 11)
            return "November";
        if (month == 12)
            return "December";

        return "January";
    }

    @Override
    public void onChecked(int scheduleId, Boolean isChecked) {
        getScheduleById(scheduleId, isChecked);
    }

    @Override
    public void onAddScheduleInCategory() {
        getSchedule();
    }
}