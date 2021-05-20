package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weddingvaganza.R;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button btn_regis;
    private TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_regis = findViewById(R.id.btn_regis);

        btn_regis.setOnClickListener(v-> {
            Intent regis = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(regis);
        });

        TextView textView = findViewById(R.id.tv_signIn);
        textView.setOnClickListener(v -> {
            Intent login = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(login);
        });

        textDate = findViewById(R.id.textDateSignUp);
        ImageView datePicker = findViewById(R.id.datePickerSignUp);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(date);
    }
}