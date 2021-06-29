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
}