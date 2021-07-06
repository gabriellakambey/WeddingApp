package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.model.GuestGroupModel;

public class GuestListActivity extends AppCompatActivity {
    Button btnBack;
    TextView toolbarTitle;
    GuestGroupModel guestGroupModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        // button back
        btnBack = findViewById(R.id.btn_backGuestList);
        btnBack.setOnClickListener(v -> {
            onBack();
        });

        // set toolbar title
        toolbarTitle = findViewById(R.id.tv_toolbarGuestList);
        Intent intent = getIntent();
        if (intent.getExtras() !=null) {
            guestGroupModel = (GuestGroupModel) intent.getSerializableExtra("data");

            String title = guestGroupModel.getTitle();
            int currentId = guestGroupModel.getClassId();
            toolbarTitle.setText(title);
        }
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