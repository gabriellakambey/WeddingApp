package com.example.weddingvaganza.view.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.CategoryScheduleAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.model.CategoryScheduleModel;
import com.example.weddingvaganza.view.activity.AddScheduleActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;

import static android.content.ContentValues.TAG;


public class ScheduleFragment extends DialogFragment implements AddScheduleActivity.AddScheduleListener {

    private TextView monthSchedule;
    private ImageView calendarSchedule;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    RecyclerView recyclerView;
    CategoryScheduleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_schedule, container, false);

        // recycler view category with schedule
        recyclerView = view.findViewById(R.id.rv_categoryOnSchedule);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        getCategory();

        // button add schedule
        Button btn_addSchedule = view.findViewById(R.id.btn_addSchedule);
        btn_addSchedule.setOnClickListener(v -> {
//            AddScheduleActivity addScheduleActivity = new AddScheduleActivity();
//            addScheduleActivity.setListener((AddScheduleActivity.AddScheduleListener)this);

            Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
            startActivity(intent);
        });

        // show dialog calendar date picker
        monthSchedule = view.findViewById(R.id.tv_dateSchedule);
        Calendar currentMonthYear = Calendar.getInstance();
        int currentMonth = currentMonthYear.get(Calendar.MONTH);
        currentMonth = currentMonth +1;
        int currentYear = currentMonthYear.get(Calendar.YEAR);
        monthSchedule.setText(makeMonthYearString(currentMonth, currentYear));

        calendarSchedule = view.findViewById(R.id.calendarSchedule);
        calendarSchedule.setOnClickListener(v -> {
            final Calendar today = Calendar.getInstance();
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH);

            MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getContext(),
                    new MonthPickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(int selectedMonth, int selectedYear) {
                            selectedMonth = selectedMonth + 1;
                            String selected = makeMonthYearString (selectedMonth, selectedYear);
                            monthSchedule.setText(selected);
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

        return view;
    }

    private void getCategory() {
        int currentUserId = Prefs.getInt("user_id", 0);
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<CategoryModel>> call = weddingService.getCategory(currentUserId);
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

    @Override
    public void onAddSchedule() {
//        getCategory();
    }
}