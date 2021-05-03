package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.weddingvaganza.R;

public class SignInActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView tv_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_login = findViewById(R.id.btn_login);
        tv_signUp = findViewById(R.id.tv_signUp);

        btn_login.setOnClickListener(v-> {
            Intent login = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(login);
        });

        tv_signUp.setOnClickListener(v-> {
            Intent regis = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(regis);
        });
    }
}