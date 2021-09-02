package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.responseModel.AddGuestResponse;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGuestActivity extends AppCompatActivity {
    Button btnBack, btnSave;
    EditText etName, etEmail, etPhoneNum, etAddress;
    private Spinner spinner;
    WeddingService weddingService;
    int userId = Prefs.getInt("user_id", 0);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guest);

        // BUTTON BACK
        btnBack = findViewById(R.id.btn_BackAddGuest);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // RETROFIT SPINNER
        spinner = findViewById(R.id.spinner_addGuest);
        spinnerRetrofit();

        // BUTTON SAVE
        etName = findViewById(R.id.et_nameAddGuest);
        etEmail = findViewById(R.id.et_emailAddGuest);
        etPhoneNum = findViewById(R.id.et_phoneNumAddGuest);
        etAddress = findViewById(R.id.et_addressAddGuest);
        btnSave = findViewById(R.id.btn_saveAddGuest);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String noHp = etPhoneNum.getText().toString();
            String alamat = etAddress.getText().toString();
            String status = "not invited";

            if (name.isEmpty() && email.isEmpty() && noHp.isEmpty() && alamat.isEmpty()) {
                Toast.makeText(this, "Field can not empty", Toast.LENGTH_SHORT).show();
            } else if (!Pattern.compile(".{10,13}").matcher(noHp).matches()) {
                Toast.makeText(this, "Enter the correct phone number", Toast.LENGTH_SHORT).show();
            } else {
                onSaveButton(name, email, noHp, alamat, status);
            }

        });
    }

    private void spinnerRetrofit() {
        List<GuestGroupModel> group = new ArrayList<>();
        ArrayAdapter<GuestGroupModel> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, group);
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestGroupModel>> call = weddingService.getGuestGroup();
        call.enqueue(new Callback<List<GuestGroupModel>>() {
            @Override
            public void onResponse(Call<List<GuestGroupModel>> call, Response<List<GuestGroupModel>> response) {
                if (response.isSuccessful()) {
                    for (GuestGroupModel model : response.body()) {
                        String title = model.getTitle();
                        int id = model.getClassId();
                        GuestGroupModel guestGroupModel = new GuestGroupModel(id, title);
                        group.add(guestGroupModel);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GuestGroupModel>> call, Throwable t) {

            }
        });
    }

    private void onSaveButton(String name, String email, String noHp, String alamat, String status) {
        // get selected item id on spinner
        int selectedId = spinner.getSelectedItemPosition();
        GuestGroupModel getItemId = (GuestGroupModel) spinner.getItemAtPosition(selectedId);
        int kelasId = getItemId.getClassId();

        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<AddGuestResponse> call1 = weddingService.addGuest(kelasId, name, noHp, email, userId, alamat, status);
        call1.enqueue(new Callback<AddGuestResponse>() {
            @Override
            public void onResponse(Call<AddGuestResponse> call, Response<AddGuestResponse> response) {
                AddGuestResponse addGuestResponse = response.body();
                if (addGuestResponse.getStatus().equals("success")) {
                    Toast.makeText(AddGuestActivity.this, "Success add guest", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } else {
                    Toast.makeText(AddGuestActivity.this, "Failed add guest", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddGuestResponse> call, Throwable t) {

            }
        });
    }

    private void onBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        }
        else {
            getSupportFragmentManager().popBackStack();
        }
    }
}