package com.example.weddingvaganza.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weddingvaganza.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cn.iwgang.countdownview.CountdownView;

public class HomeFragment extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CountdownView countdownView = view.findViewById(R.id.countDown);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String countDate = "31-05-2021 00:00:00";
        Date now = new Date();

        try {
            //formatting from String to Date
            Date date = sdf.parse(countDate);
            long currentTime = now.getTime();
            long theDate = date.getTime();
            long countDownToTheDate = theDate - currentTime;

            countdownView.start(countDownToTheDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }
}