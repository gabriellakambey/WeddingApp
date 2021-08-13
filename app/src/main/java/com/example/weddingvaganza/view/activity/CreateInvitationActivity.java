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
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.weddingvaganza.R;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateInvitationActivity extends AppCompatActivity {

    int Hour, Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invitation);

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_BackCreateInvitation);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // SET THE WEDDING DATE
        String weddingDate = Prefs.getString("wedding_date", null);
        TextView textDate = findViewById(R.id.td_createInvitation);
        textDate.setText(weddingDate);

        // TIME PICKER
        TextView timeSet = findViewById(R.id.tv_timeInvitation);
        LinearLayout timePicker = findViewById(R.id.tp_timeInvitation);
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

                            timeSet.setText(f12jam.format(date));
                            timeSet.setTextColor(Color.parseColor("#2C3E57"));

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

}