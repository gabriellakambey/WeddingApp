package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weddingvaganza.R;

import java.util.Calendar;

public class AddCategoryActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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