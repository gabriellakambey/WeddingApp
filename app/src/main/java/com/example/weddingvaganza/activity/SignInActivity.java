package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.LoginRespondModel;
import com.xwray.passwordview.PasswordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView tv_signUp;
    private EditText et_email;
    private PasswordView et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

        btn_login = findViewById(R.id.btn_login);
        tv_signUp = findViewById(R.id.tv_signUp);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        tv_signUp.setOnClickListener(v-> {
            Intent regis = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(regis);
        });

        btn_login.setOnClickListener(v-> {
            String email_user = et_email.getText().toString();
            String password_user = et_password.getText().toString();

            Call<LoginRespondModel> call = weddingService.login(email_user, password_user);
            call.enqueue(new Callback<LoginRespondModel>() {
                @Override
                public void onResponse(Call<LoginRespondModel> call, Response<LoginRespondModel> response) {
                    LoginRespondModel loginRespondModel = response.body();
                    if (loginRespondModel.getStatus().equals("Success")){
                        Intent login = new Intent(SignInActivity.this, HomeActivity.class);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, "Failed Login", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginRespondModel> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}