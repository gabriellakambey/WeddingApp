package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.ScheduleModel;
import com.example.weddingvaganza.view.activity.BudgetActivity;
import com.example.weddingvaganza.view.activity.GuestsActivity;
import com.example.weddingvaganza.view.activity.VendorActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalHomeFragment extends Fragment {
    private ImageButton ibGuests, ibBudget, ibVendor;
    private TextView tvTotTask, tvPresentase, tvCekTask;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_home, container, false);

        // set couple name
        TextView tvCouple = view.findViewById(R.id.tv_coupleFinalHome);
        String coupleName = Prefs.getString("couple_name", null);
        tvCouple.setText(coupleName);

        // set count down wedding date
        String weddingDate = Prefs.getString("wedding_date", null);

        CountdownView countdownView = view.findViewById(R.id.cd_finalHome);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String countDate = weddingDate + " 00:00:00";
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

        // wedding progress
        tvTotTask = view.findViewById(R.id.tv_totTask);
        tvPresentase = view.findViewById(R.id.tv_progress_bar);
        tvCekTask = view.findViewById(R.id.tv_cekTask);
        progressBar = view.findViewById(R.id.progress_bar);

        int currentUserId = Prefs.getInt("user_id", 0);
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<ScheduleModel>> call = weddingService.getSchedule(currentUserId);
        call.enqueue(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                List<ScheduleModel> scheduleModels = response.body();
                int totalTask = scheduleModels.size();
                int totCekTask = 1;
                int presentaseTask  = (totCekTask * 100)/totalTask;
                progressBar.setProgress(presentaseTask);

                String presentase = presentaseTask + "%";
                tvPresentase.setText(presentase);

                String tot = String.valueOf(totalTask);
                tvTotTask.setText(tot);

                String cek = String.valueOf(totCekTask);
                tvCekTask.setText(cek);

                Toast.makeText(getContext(), "Total task: "+totalTask, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

        // menu guests
        ibGuests = view.findViewById(R.id.ib_guests);
        ibGuests.setOnClickListener(v -> {
            Intent guests = new Intent(getActivity(), GuestsActivity.class);
            startActivity(guests);
        });

        // menu budget
        ibBudget = view.findViewById(R.id.ib_budget);
        ibBudget.setOnClickListener(v -> {
            Intent budget = new Intent(getActivity(), BudgetActivity.class);
            startActivity(budget);
        });

        // menu vendor
        ibVendor = view.findViewById(R.id.ib_vendor);
        ibVendor.setOnClickListener(v -> {
            Intent vendor = new Intent(getActivity(), VendorActivity.class);
            startActivity(vendor);
        });

        return view;
    }
}