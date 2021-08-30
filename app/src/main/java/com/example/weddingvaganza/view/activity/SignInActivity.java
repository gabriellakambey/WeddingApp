package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.LoginResponseModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.view.activity.HomeActivity;
import com.example.weddingvaganza.view.activity.SignUpActivity;
import com.pixplicity.easyprefs.library.Prefs;
import com.xwray.passwordview.PasswordView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private EditText et_email;
    private PasswordView et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // BUTTON SIGN UP
        TextView tv_signUp = findViewById(R.id.tv_signUp);
        tv_signUp.setOnClickListener(v-> {
            Intent regis = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(regis);
        });

        // BUTTON LOGIN
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v-> {
            onButtonLoginClicked();
        });
    }

    private void onButtonLoginClicked() {
        String email_user = et_email.getText().toString();
        String password_user = et_password.getText().toString();

        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<LoginResponseModel> call = weddingService.login(email_user, password_user);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                LoginResponseModel loginRespondModel = response.body();
                if (loginRespondModel.getStatus().equals("success")){

                    int activeUserId = loginRespondModel.getUserModel().getUserId();
                    String activeUserCouple = loginRespondModel.getUserModel().getUserCouple();
                    String activeCoupleDate = loginRespondModel.getUserModel().getTglPernikahan();

                    Prefs.putInt("user_id", activeUserId);
                    Prefs.putString("couple_name", activeUserCouple);
                    Prefs.putString("wedding_date", activeCoupleDate);

                    Intent login = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(login);
                    finish();

                    Toast.makeText(SignInActivity.this, "Success Login", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(SignInActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}