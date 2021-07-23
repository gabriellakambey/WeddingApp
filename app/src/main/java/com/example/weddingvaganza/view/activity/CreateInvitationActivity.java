package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.weddingvaganza.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateInvitationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText textDate, timePicker;
    int Hour, Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invitation);

        // button back
        Button btnBack = findViewById(R.id.btn_BackCreateInvitation);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // date picker
        textDate = findViewById(R.id.td_createInvitation);
        LinearLayout datePicker = findViewById(R.id.dp_createInvitation);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        // time picker
        timePicker = findViewById(R.id.et_timeInvitation);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateInvitationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Minute = minute;

                        String time = Hour + ":" + Minute;
                        SimpleDateFormat f24jam = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24jam.parse(time);
                            SimpleDateFormat f12jam = new SimpleDateFormat("hh:mm aa");
                            timePicker.setText(f12jam.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(Hour,Minute);
                timePickerDialog.show();
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
        String date = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(date);
    }
}