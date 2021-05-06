package com.example.weddingvaganza.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.activity.AddScheduleActivity;


public class ScheduleFragment extends Fragment {

    public static ScheduleFragment getInstance(){
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        return scheduleFragment;
    }

    Button btn_addSchedule;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_schedule, container, false);

        btn_addSchedule = view.findViewById(R.id.btn_addSchedule);
        btn_addSchedule.setOnClickListener(v -> {
            Intent add = new Intent(getActivity(), AddScheduleActivity.class);
            startActivity(add);
        });

        return view;
    }
}