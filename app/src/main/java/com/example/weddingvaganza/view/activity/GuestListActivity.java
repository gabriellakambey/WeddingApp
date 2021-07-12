package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListGuestAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.example.weddingvaganza.model.GuestModel;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestListActivity extends AppCompatActivity {
    Button btnBack;
    TextView toolbarTitle;
    EditText searchView;
    CharSequence search="";
    GuestGroupModel guestGroupModel;
    ListGuestAdapter adapter;
    RecyclerView recyclerView;
    private WeddingService weddingService;
    int currentGroup;
    private int currentUser = Prefs.getInt("user_id", 0);

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
            currentGroup = guestGroupModel.getClassId();
            toolbarTitle.setText(title);
        }

        // search guest
        searchView = findViewById(R.id.src_guestList);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s);
                search = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // recyclerview guest
        recyclerView = findViewById(R.id.rv_guestList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        adapter = new ListGuestAdapter();

        getGuest();
    }

    private void getGuest() {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestModel>> call = weddingService.getGuest(currentGroup, currentUser);
        call.enqueue(new Callback<List<GuestModel>>() {
            @Override
            public void onResponse(Call<List<GuestModel>> call, Response<List<GuestModel>> response) {
                List<GuestModel> guestModels = response.body();
                adapter = new ListGuestAdapter(guestModels);
                recyclerView.setAdapter(adapter);

//                if (response.isSuccessful()) {
//                    List<GuestModel> guestModels = response.body();
//                    adapter.setData(guestModels);
//                    recyclerView.setAdapter(adapter);
//                }
            }

            @Override
            public void onFailure(Call<List<GuestModel>> call, Throwable t) {

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