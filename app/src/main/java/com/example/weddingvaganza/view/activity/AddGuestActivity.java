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
import com.example.weddingvaganza.model.GuestGroupModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGuestActivity extends AppCompatActivity {
    Button btnBack, btnSave;
    EditText etName, etEmail, etPhoneNum, etAddress;
    private Spinner spinner;
    WeddingService weddingService;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guest);
        spinner = findViewById(R.id.spinner_addGuest);

        // button back
        btnBack = findViewById(R.id.btn_BackAddGuest);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // retrofit spinner
        List<GuestGroupModel> guestGroup = new ArrayList<>();
        ArrayAdapter<GuestGroupModel> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, guestGroup);
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestGroupModel>> call = weddingService.getGuestGroup();

        call.enqueue(new Callback<List<GuestGroupModel>>() {
            @Override
            public void onResponse(Call<List<GuestGroupModel>> call, Response<List<GuestGroupModel>> response) {
                if (response.isSuccessful()) {
                    for (GuestGroupModel post : response.body()) {

                        String group = post.getTitle();
                        int currentId = post.getClassId();
                        GuestGroupModel guestGroupModel = new GuestGroupModel(currentId, group);
                        guestGroup.add(guestGroupModel);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GuestGroupModel>> call, Throwable t) {

            }
        });

        // button save
        etName = findViewById(R.id.et_nameAddGuest);
        etEmail = findViewById(R.id.et_emailAddGuest);
        etPhoneNum = findViewById(R.id.et_phoneNumAddGuest);
        etAddress = findViewById(R.id.et_addressAddGuest);
        btnSave = findViewById(R.id.btn_saveAddGuest);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phoneNumber = etPhoneNum.getText().toString();
            String address = etAddress.getText().toString();

            // get selected item id on spinner
            int selectedId = spinner.getSelectedItemPosition();
            GuestGroupModel getItemId = (GuestGroupModel) spinner.getItemAtPosition(selectedId);
            int groupId = getItemId.getClassId();

            Toast.makeText(this, "Grup Guest dengan id: "+groupId, Toast.LENGTH_SHORT).show();

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