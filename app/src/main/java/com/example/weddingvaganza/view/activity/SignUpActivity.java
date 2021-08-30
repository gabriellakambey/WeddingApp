package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.LoginResponseModel;

import java.util.Calendar;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText etName, etPhoneNum, etEmail, etPass, etConfirmPass, etCouple, etDate;
    TextView valName, valPhoneNum, valEmail, valPass, valConfirmPass, valCouple, valDate;
    String name, phoneNum, email, pass, confirmPass, couple, date;
    String datePicker;
    LinearLayout llDate;
    private TextView textDate;
    int budget = 0;

    @SuppressLint({"CutPasteId", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        valName = findViewById(R.id.validate_name);
        valPhoneNum = findViewById(R.id.validate_phoneNum);
        valEmail = findViewById(R.id.validate_emailRegis);
        valPass = findViewById(R.id.validate_password);
        valConfirmPass = findViewById(R.id.validate_confirmPass);
        valCouple = findViewById(R.id.validate_coupleName);
        valDate = findViewById(R.id.validate_wedDate);

        llDate = findViewById(R.id.ll_dateSignUp);
        etName = findViewById(R.id.et_name);
        etPhoneNum = findViewById(R.id.et_phoneNumber);
        etEmail = findViewById(R.id.et_emailRegis);
        etPass = findViewById(R.id.et_passwordRegis);
        etConfirmPass = findViewById(R.id.et_passwordConfirm);
        etCouple = findViewById(R.id.et_coupleName);
        etDate = findViewById(R.id.textDateSignUp);

        // BUTTON REGIS
        Button btn_regis = findViewById(R.id.btn_regis);
        btn_regis.setOnClickListener(v -> {

            if (validateUserName(etName) && validatePhoneNum(etPhoneNum) && validateEmail(etEmail) &&
                    validatePassword(etPass) && validateConfirmPass(etConfirmPass, pass) &&
                    validateCouple(etCouple) && validateWeddingDate(etDate)) {
                onButtonRegisClicked();
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();

            } else {
                validateUserName(etName);
                validatePhoneNum(etPhoneNum);
                validateEmail(etEmail);
                validatePassword(etPass);
                validateConfirmPass(etConfirmPass, pass);
                validateCouple(etCouple);
                validateWeddingDate(etDate);

                Toast.makeText(this, "Invalid data register", Toast.LENGTH_SHORT).show();
            }


        });

        // BUTTON LOGIN
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

    private void onButtonRegisClicked() {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<LoginResponseModel> call = weddingService.register(name, phoneNum, email, pass, couple, date, budget);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                LoginResponseModel loginResponseModel = response.body();
                if (loginResponseModel.getStatus().equals("success")) {
                    Intent regis = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(regis);
                    finish();
                    Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Failed to Sign Up", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateWeddingDate(EditText etDate) {
        date = etDate.getText().toString();

        if (date.isEmpty()) {
            valDate.setText("* Field can not be empty");
            valDate.setVisibility(View.VISIBLE);
            llDate.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else if (!Pattern.compile("^" + "(?=.*[/])" + ".{8,10}" + "$").matcher(date).matches()) {
            valDate.setText("* Wrong date format");
            valDate.setVisibility(View.VISIBLE);
            llDate.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else {
            valDate.setVisibility(View.GONE);
            llDate.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;

        }

    }

    private boolean validateCouple(EditText etCouple) {
        couple = etCouple.getText().toString();

        if (couple.isEmpty()) {
            valCouple.setText("* Field can not be empty");
            valCouple.setVisibility(View.VISIBLE);
            etCouple.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else {
            valCouple.setVisibility(View.GONE);
            etCouple.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;

        }
    }

    private boolean validateConfirmPass(EditText etConfirmPass, String pass) {
        confirmPass = etConfirmPass.getText().toString();

        if (confirmPass.isEmpty()) {
            valConfirmPass.setText("* Field can not be empty");
            valConfirmPass.setVisibility(View.VISIBLE);
            etConfirmPass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else if (confirmPass.equals(pass)) {
            valConfirmPass.setVisibility(View.GONE);
            etConfirmPass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;

        } else {
            valConfirmPass.setText("* Password not match");
            valConfirmPass.setVisibility(View.VISIBLE);
            etConfirmPass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        }
    }

    private boolean validatePassword(EditText etPass) {
        pass = etPass.getText().toString();

        if (pass.isEmpty()) {
            valPass.setText("* Field can not be empty");
            valPass.setVisibility(View.VISIBLE);
            etPass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } if (Pattern.compile(".{8,}").matcher(pass).matches()) {
            valPass.setVisibility(View.GONE);
            etPass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;

        } else {
            valPass.setText("* Password is too weak");
            valPass.setVisibility(View.VISIBLE);
            etPass.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        }
    }

    private boolean validatePhoneNum(EditText etPhoneNum) {
        phoneNum = etPhoneNum.getText().toString();

        if (phoneNum.isEmpty()) {
            valPhoneNum.setText("* Field can not be empty");
            valPhoneNum.setVisibility(View.VISIBLE);
            etPhoneNum.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else if (!Pattern.compile(".{10,13}").matcher(phoneNum).matches()) {
            valPhoneNum.setText("* Enter the correct phone number");
            valPhoneNum.setVisibility(View.VISIBLE);
            etPhoneNum.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else {
            valPhoneNum.setVisibility(View.GONE);
            etPhoneNum.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;

        }

    }

    private boolean validateEmail(EditText etEmail) {
        email = etEmail.getText().toString();

        if (email.isEmpty()) {
            valEmail.setText("* Field can not be empty");
            valEmail.setVisibility(View.VISIBLE);
            etEmail.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valEmail.setText("* Enter the correct email format");
            valEmail.setVisibility(View.VISIBLE);
            etEmail.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;

        } else {
            valEmail.setVisibility(View.GONE);
            etEmail.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;
        }

    }

    private boolean validateUserName(EditText etName) {
        name = etName.getText().toString();
        if (name.isEmpty()) {
            valName.setText("* Field can not be empty");
            valName.setVisibility(View.VISIBLE);
            etName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_error));
            return false;
        } else {
            valName.setVisibility(View.GONE);
            etName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.round_champagne));
            return true;
        }
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
        month = month + 1;
        datePicker = dayOfMonth + "/" + month + "/" + year;
        textDate.setText(datePicker);
    }
}