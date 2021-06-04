package com.example.weddingvaganza.view.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.view.activity.AddScheduleActivity;

import java.util.Calendar;

import static android.content.ContentValues.TAG;


public class ScheduleFragment extends DialogFragment {

    private TextView monthSchedule;
    private ImageView calendarSchedule;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_schedule, container, false);

        Button btn_addSchedule = view.findViewById(R.id.btn_addSchedule);
        btn_addSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
            startActivity(intent);
        });


        calendarSchedule = view.findViewById(R.id.calendarSchedule);
        monthSchedule = view.findViewById(R.id.tv_dateSchedule);

        calendarSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, dateSetListener, year, month, day);

                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                Log.d(TAG, "onDateSet: mm yyyy: " + day + "/" + month + "/" + year);

                String date = makeDateString (day, month, year);
                monthSchedule.setText(date);

            }
        };

        return view;
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + year;
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

    public class SelectedDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            populateSetDate(year, month+1, dayOfMonth);
        }

        public void populateSetDate(int year, int month, int dayOfMonth) {
            monthSchedule.setText(dayOfMonth + "/" + month + "/" + year);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.schedule_print) {
            Toast.makeText(getActivity(), "Print", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.schedule_share) {
            Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}