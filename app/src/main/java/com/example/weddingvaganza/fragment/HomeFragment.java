package com.example.weddingvaganza.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weddingvaganza.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textView = view.findViewById(R.id.count_down);

        // timer duration
        long duration = TimeUnit.MINUTES.toMillis(3);

        // countdown timer
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // convert milisecond to minute and second
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                ,TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                ,TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                //convert string on text view
                textView.setText(sDuration);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        return view;
    }
}