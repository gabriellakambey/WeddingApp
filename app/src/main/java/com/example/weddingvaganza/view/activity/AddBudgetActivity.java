package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.responseModel.AddBudgetResponse;
import com.pixplicity.easyprefs.library.Prefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBudgetActivity extends AppCompatActivity {

    int userId = Prefs.getInt("user_id", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        // BUTTON BACK
        Button btnBack = findViewById(R.id.btn_BackAddBudget);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // BUTTON SAVE
        EditText etTitle = findViewById(R.id.et_titleAddBudget);
        EditText etCost = findViewById(R.id.et_costAddBudget);
        EditText etNote = findViewById(R.id.et_noteAddBudget);

        Button btnSave = findViewById(R.id.btn_saveAddBudget);
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String mCost = etCost.getText().toString();
            String note = etNote.getText().toString();
            Integer cost = Integer.parseInt(mCost);

            if (title.isEmpty() && mCost.isEmpty() && note.isEmpty()) {
                Toast.makeText(this, "Field can not empty", Toast.LENGTH_SHORT).show();
            } else {
                onAddBudget(title, cost, note);
//                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void onAddBudget(String title, int cost, String note) {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<AddBudgetResponse> call = weddingService.addBudget(title, cost, note, "false", cost, userId);
        call.enqueue(new Callback<AddBudgetResponse>() {
            @Override
            public void onResponse(Call<AddBudgetResponse> call, Response<AddBudgetResponse> response) {
                AddBudgetResponse addBudgetResponse = response.body();
                if (addBudgetResponse.getStatus().equals("success")) {
                    Toast.makeText(AddBudgetActivity.this, "Success add budget", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AddBudgetResponse> call, Throwable t) {
                Toast.makeText(AddBudgetActivity.this, "Failed add budget", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0){
            super.onBackPressed();
        }
        else {
            getSupportFragmentManager().popBackStack();
        }
    }

}