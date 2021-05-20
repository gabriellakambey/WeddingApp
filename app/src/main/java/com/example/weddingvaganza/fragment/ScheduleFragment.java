package com.example.weddingvaganza.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

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
import com.example.weddingvaganza.activity.AddScheduleActivity;

import java.util.Calendar;


public class ScheduleFragment extends DialogFragment {

    private TextView monthSchedule;
    private TextView yearSchedule;
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
        monthSchedule = view.findViewById(R.id.monthSchedule);
        yearSchedule = view.findViewById(R.id.yearSchedule);

        calendarSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SelectedDateFragment();
                dialogFragment.show(getFragmentManager(), "date picker");
            }
        });

        return view;
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