package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.LoginResponseModel;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText etName, etPhoneNum, etEmail, etPass, etConfirmPass, etCouple, etDate;
    private Button btn_regis;
    private TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.et_name);
        etPhoneNum = findViewById(R.id.et_phoneNumber);
        etEmail = findViewById(R.id.et_emailRegis);
        etPass = findViewById(R.id.et_passwordRegis);
        etConfirmPass = findViewById(R.id.et_passwordConfirm);
        etCouple = findViewById(R.id.et_coupleName);
        etDate = findViewById(R.id.textDateSignUp);
        btn_regis = findViewById(R.id.btn_regis);

        btn_regis.setOnClickListener(v-> {
//            Intent regis = new Intent(SignUpActivity.this, SignInActivity.class);
//            startActivity(regis);

            String nama_user = etName.getText().toString();
            String email_user = etEmail.getText().toString();
            String password_user = etPass.getText().toString();
            String confirmPass = etConfirmPass.getText().toString();
            String nomorhp_user = etPhoneNum.getText().toString();
            String nama_pasangan_user = etCouple.getText().toString();
            String tgl_pernikahan = etDate.getText().toString();

            WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
            Call<LoginResponseModel> call = weddingService.register(nama_user, nomorhp_user, email_user, password_user, nama_pasangan_user, tgl_pernikahan);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    LoginResponseModel loginResponseModel = response.body();
                    if (loginResponseModel.getStatus().equals("success")) {
                        Intent regis = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(regis);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Failed to Sign Up", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });
        });

        TextView textView = findViewById(R.id.tv_signIn);
        textView.setOnClickListener(v -> {
            Intent login = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(login);
        });

        textDate = findViewById(R.id.textDateSignUp);
        ImageView datePicker = findViewById(R.id.datePickerSignUp);
        datePicker.setOnClickListener(v -> {
            showDatePickerDialog();
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(date);
    }
}