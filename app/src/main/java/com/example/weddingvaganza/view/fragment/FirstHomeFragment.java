package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.view.activity.AddCategoryActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class FirstHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_home, container, false);

        // set couple name
        TextView tvCouple = view.findViewById(R.id.tv_coupleFirstHome);
        String coupleName = Prefs.getString("couple_name", null);
        tvCouple.setText(coupleName);

        // set countdown wedding date
        String weddingDate = Prefs.getString("wedding_date", null);

        CountdownView countdownView = view.findViewById(R.id.cd_firstHome);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String countDate = weddingDate+" 00:00:00";
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


        // button add a list
        Button button = view.findViewById(R.id.btn_makeList);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(intent);
        });

        return view;
    }
}