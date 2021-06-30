package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListScheduleAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.ScheduleModel;
import com.pixplicity.easyprefs.library.Prefs;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFromCategoryActivity extends AppCompatActivity {

    TextView categoryTitle, monthYear;
    Button btnAdd, btnBack;
    ImageView calendar;
    RecyclerView recyclerView;
    CategoryModel categoryModel;
    ListScheduleAdapter adapter;
    int currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_from_category);

        // button back
        btnBack = findViewById(R.id.btn_backSchedFrmCat);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // button add schedule
        btnAdd = findViewById(R.id.btn_addSchdFrmCat);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddScheduleActivity.class);
            startActivity(intent);
        });

        // set toolbar title
        categoryTitle = findViewById(R.id.tv_toolbarCatTitle);
        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            categoryModel = (CategoryModel) intent.getSerializableExtra("data");

            String title = categoryModel.getCategoryTitle();
            currentCategory = categoryModel.getCategoryId();
            categoryTitle.setText(title);
        }

        // set current month and year
        monthYear = findViewById(R.id.tv_dateSchdFrmCat);
        Calendar currentMonthYear = Calendar.getInstance();
        int currentMonth = currentMonthYear.get(Calendar.MONTH);
        currentMonth = currentMonth +1;
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
                            String selected = makeMonthYearString (selectedMonth, selectedYear);
                            monthYear.setText(selected);
                        }
                    }, year, month);

            builder.setActivatedMonth(month)
                    .setMinYear(year-1)
                    .setActivatedYear(year)
                    .setMaxYear(2030)
                    .setTitle("Select Month and Year")
                    .build()
                    .show();
        });

        // recyclerview
        recyclerView = findViewById(R.id.rv_schdFrmCat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new ListScheduleAdapter();

        getSchedule();

    }

    private void getSchedule() {
        int currentUserId = Prefs.getInt("user_id", 0);
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<ScheduleModel>> call = weddingService.getAllSchedule();
        call.enqueue(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                if (response.isSuccessful()) {
                    List<ScheduleModel> scheduleModels = response.body();
                    adapter.setData(scheduleModels);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {

            }
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

    private String makeMonthYearString(int selectedMonth, int selectedYear) {
        return getMonthFormat(selectedMonth) + " " + selectedYear;
    }

    private String getMonthFormat(int month) {
        if (month==1)
            return "January";
        if (month==2)
            return "February";
        if (month==3)
            return "March";
        if (month==4)
            return "April";
        if (month==5)
            return "May";
        if (month==6)
            return "June";
        if (month==7)
            return "July";
        if (month==8)
            return "August";
        if (month==9)
            return "September";
        if (month==10)
            return "October";
        if (month==11)
            return "November";
        if (month==12)
            return "December";

        return "January";
    }
}