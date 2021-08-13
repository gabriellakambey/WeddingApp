package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListGuestAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.view.dialog.GuestDetailDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestListActivity extends AppCompatActivity implements ListGuestAdapter.ClickedItem, GuestDetailDialog.DetailDialogListener {
    Button btnBack;
    TextView toolbarTitle, totalGuest;
    EditText searchView;
    GuestGroupModel guestGroupModel;
    ListGuestAdapter adapter;
    RecyclerView recyclerView;
    List<GuestModel> guestModels;
    private WeddingService weddingService;
    int currentGroup, selectedGuest;
    private int totGuest;
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
                if (searchView.getText().toString().trim().equals("")){
                    getGuest();
                } else {
                    searchGuestName(s.toString());
                }
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

        getGuest();

    }

    private void searchGuestName(String text) {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestModel>> call = weddingService.findGuestName(currentGroup, text);
        call.enqueue(new Callback<List<GuestModel>>() {
            @Override
            public void onResponse(Call<List<GuestModel>> call, Response<List<GuestModel>> response) {
                List<GuestModel> guests = response.body();
                adapter = new ListGuestAdapter(GuestListActivity.this, guests);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GuestModel>> call, Throwable t) {

            }
        });
    }

    private void getGuest() {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestModel>> call = weddingService.getGuest(currentGroup, currentUser);
        call.enqueue(new Callback<List<GuestModel>>() {
            @Override
            public void onResponse(Call<List<GuestModel>> call, Response<List<GuestModel>> response) {
                List<GuestModel> guestModels = response.body();
                adapter = new ListGuestAdapter(GuestListActivity.this, guestModels);
                totGuest = guestModels.size();
                adapter.setGuestModels(guestModels);
                adapter.clickedListener(GuestListActivity.this::ClickedGuest);

                recyclerView.setAdapter(adapter);

                // set total guest on list
                if (totGuest != 0) {
                    String total = totGuest + " guests";

                    totalGuest = findViewById(R.id.tv_totGuests);
                    totalGuest.setText(total);
                } else {
                    LinearLayout totalGuestList = findViewById(R.id.totalGuestList);
                    totalGuestList.setVisibility(View.GONE);

                    TextView textView = findViewById(R.id.tv_noGuestList);
                    textView.setVisibility(View.VISIBLE);
                }

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

    @Override
    public void ClickedGuest(GuestModel guestModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("guest selected", guestModel);

        GuestDetailDialog guestDetailDialog = new GuestDetailDialog();

        guestDetailDialog.setArguments(bundle);

        guestDetailDialog.setListener(this::closeDialog);

        guestDetailDialog.show(getSupportFragmentManager(), "detail guest");

    }

    @Override
    public void closeDialog() {
        getGuest();
    }
}