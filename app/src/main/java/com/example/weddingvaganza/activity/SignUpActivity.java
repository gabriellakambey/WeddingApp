package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.weddingvaganza.R;

public class SignUpActivity extends AppCompatActivity {
    private Button btn_regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_regis = findViewById(R.id.btn_regis);

        btn_regis.setOnClickListener(v-> {
            Intent regis = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(regis);
        });
    }
}