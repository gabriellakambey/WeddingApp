package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.view.activity.SignInActivity;
import com.pixplicity.easyprefs.library.Prefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    TextView tvUserName, tvCouple, tvWeddingDate, tvEmail, tvPhoneNum;
    int currentUserId = Prefs.getInt("user_id", 0);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // BUTTON LOGOUT
        LinearLayout btnLogout = view.findViewById(R.id.ll_buttonLogout);
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getActivity(), "You're logged  out", Toast.LENGTH_SHORT).show();
        });


        // SET DATA USER
        tvUserName = view.findViewById(R.id.tv_userNameProfile);
        tvCouple = view.findViewById(R.id.tv_userCoupleProfile);
        tvWeddingDate = view.findViewById(R.id.tv_weddingDateProfile);
        tvEmail = view.findViewById(R.id.tv_emailProfile);
        tvPhoneNum = view.findViewById(R.id.tv_phoneNumProfile);
        getUserData();

        return view;
    }

    private void getUserData() {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<UserModel> call = weddingService.getUser(currentUserId);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel model = response.body();
                tvUserName.setText(model.getUserName());
                tvCouple.setText(model.getUserCouple());
                tvWeddingDate.setText(model.getTglPernikahan());
                tvEmail.setText(model.getUserEmail());
                tvPhoneNum.setText(model.getNomorHp());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}