package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.model.GuestStatusModel;
import com.example.weddingvaganza.model.ScheduleModel;
import com.example.weddingvaganza.model.ScheduleStatusModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.view.activity.BudgetActivity;
import com.example.weddingvaganza.view.activity.GuestsActivity;
import com.example.weddingvaganza.view.activity.InvitationTemplateActivity;
import com.example.weddingvaganza.view.activity.VendorActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class FinalHomeFragment extends Fragment {
    ImageButton ibGuests, ibBudget, ibVendor;
    TextView tvTotTask, tvPresentase, tvCekTask;
    TextView tvTotGuestList, tvTotGuestInvited, tvTotGuestCheckIn;
    TextView tvBudget, tvCost;
    ProgressBar progressBar;
    int currentUserId = Prefs.getInt("user_id", 0);
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_home, container, false);

        // SET COUPLE NAME
        TextView tvCouple = view.findViewById(R.id.tv_coupleFinalHome);
        String coupleName = Prefs.getString("couple_name", null);
        tvCouple.setText(coupleName);

        // SET COUNT DOWN WEDDING DATE
        String weddingDate = Prefs.getString("wedding_date", null);

        LinearLayout beforeWedding = view.findViewById(R.id.ll_onCountDown);
        LinearLayout onWeddingDay = view.findViewById(R.id.ll_afterCountDown);

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

//            if (countDownToTheDate != 0) {
//                beforeWedding.setVisibility(View.VISIBLE);
//                onWeddingDay.setVisibility(View.GONE);
//
//            } else {
//                onWeddingDay.setVisibility(View.VISIBLE);
//                beforeWedding.setVisibility(View.GONE);
//
//            }

        } catch (ParseException e) {
            e.printStackTrace();
        }




        // menu guests
        ibGuests = view.findViewById(R.id.ib_guests);
        ibGuests.setOnClickListener(v -> {
            Intent guests = new Intent(getActivity(), GuestsActivity.class);
            startActivityForResult(guests, 1);
        });

        // menu budget
        ibBudget = view.findViewById(R.id.ib_budget);
        ibBudget.setOnClickListener(v -> {
            Intent budget = new Intent(getActivity(), BudgetActivity.class);
            startActivityForResult(budget, 1);
        });

        // menu vendor
        ibVendor = view.findViewById(R.id.ib_vendor);
        ibVendor.setOnClickListener(v -> {
            Intent vendor = new Intent(getActivity(), VendorActivity.class);
            startActivity(vendor);
        });

        // WEDDING PROGRESS
        tvTotTask = view.findViewById(R.id.tv_totTask);
        tvPresentase = view.findViewById(R.id.tv_progress_bar);
        tvCekTask = view.findViewById(R.id.tv_cekTask);
        progressBar = view.findViewById(R.id.progress_bar);
        onWeddingProgress();

        // GUEST INFO
        tvTotGuestList = view.findViewById(R.id.tv_totGuestList);
        tvTotGuestInvited = view.findViewById(R.id.tv_totGuestInvited);
        tvTotGuestCheckIn = view.findViewById(R.id.tv_totGuestCheckIn);
        getGuestInfo();

        // OUR BUDGET INFO
        tvBudget = view.findViewById(R.id.tv_our_budget);
        getOurBudget();

        // COST INFO
        tvCost = view.findViewById(R.id.tv_cost);
        getCostInfo();

        return view;
    }

    private void onWeddingProgress() {
        List<ScheduleStatusModel> scheduleStatusModels = new ArrayList<>();
        Call<List<ScheduleModel>> call = weddingService.getSchedule(currentUserId);
        call.enqueue(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, Response<List<ScheduleModel>> response) {
                List<ScheduleModel> scheduleModels = response.body();

                // count checked status
                for (ScheduleModel model : scheduleModels) {
                    String statusChecked = model.getStatus();
                    if (statusChecked.equals("checked")) {
                        ScheduleStatusModel statusModel = new ScheduleStatusModel(statusChecked);
                        scheduleStatusModels.add(statusModel);
                    }
                }

                int totalTask = scheduleModels.size();
                int totCekTask = scheduleStatusModels.size();
                int presentaseTask  = (totCekTask * 100)/totalTask;
                progressBar.setProgress(presentaseTask);

                String presentase = presentaseTask + "%";
                tvPresentase.setText(presentase);

                String tot = String.valueOf(totalTask);
                tvTotTask.setText(tot);

                String cek = String.valueOf(totCekTask);
                tvCekTask.setText(cek);
            }

            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getGuestInfo() {
        List<GuestStatusModel> guestStatusModels = new ArrayList<>();
        Call<List<GuestModel>> call1 = weddingService.getGuestByUserId(currentUserId);
        call1.enqueue(new Callback<List<GuestModel>>() {
            @Override
            public void onResponse(Call<List<GuestModel>> call, Response<List<GuestModel>> response) {
                List<GuestModel> guestModels = response.body();

                // count invited status
                for (GuestModel model : guestModels) {
                    String statusInvited = model.getStatus();
                    if (statusInvited.equals("invited")) {
                        GuestStatusModel guestStatus = new GuestStatusModel(statusInvited);
                        guestStatusModels.add(guestStatus);
                    }
                }

                String totalGuest = String.valueOf(guestModels.size());
                tvTotGuestList.setText(totalGuest);

                String totalInvited = String.valueOf(guestStatusModels.size());
                tvTotGuestInvited.setText(totalInvited);

                // nnti rubah data totalCheckIn
//                String totalCheckIn = String.valueOf(guestStatusModels.size());
                String totalCheckIn = "0";
                tvTotGuestCheckIn.setText(totalCheckIn);
            }

            @Override
            public void onFailure(Call<List<GuestModel>> call, Throwable t) {

            }
        });
    }

    private void getCostInfo() {
        Call<Integer> call3 = weddingService.getCostTotal("true", currentUserId);
        call3.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer totalCost = response.body();

                if (Math.abs(totalCost / 1000000) >= 1) {
                    tvCost.setText("IDR " + (totalCost / 1000000) + "M");
                } else if (Math.abs(totalCost / 1000) >= 1) {
                    tvCost.setText("IDR " + (totalCost / 1000) + "K");
                } else {
                    tvCost.setText("IDR " + totalCost);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void getOurBudget() {
        Call<UserModel> modelCall = weddingService.getOurBudget(currentUserId);
        modelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                int ourBudget = userModel.getOurBudget();

                if (Math.abs(ourBudget / 1000000) >= 1) {
                    tvBudget.setText("IDR " + (ourBudget / 1000000) + "M");
                } else if (Math.abs(ourBudget / 1000) >= 1) {
                    tvBudget.setText("IDR " + (ourBudget / 1000) + "K");
                } else {
                    tvBudget.setText("IDR " + ourBudget);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                getOurBudget();
                getCostInfo();
                getGuestInfo();
            }
        }
    }
}