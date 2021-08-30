package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.CategoryRundownAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryRundownModel;
import com.example.weddingvaganza.model.RundownModel;
import com.example.weddingvaganza.view.activity.AddBudgetActivity;
import com.example.weddingvaganza.view.activity.AddRundownActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class RundownFragment extends Fragment {
    private static final String TAG = "RundownFragment";

    int currentUserId = Prefs.getInt("user_id", 0);

    RecyclerView recyclerView;
    CategoryRundownAdapter adapter;
    LinearLayout firstRundown;
    RelativeLayout finalRundown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rundown, container, false);

        // RUNDOWN VIEW IN CONDITION
        firstRundown = view.findViewById(R.id.ll_firstRundown);
        finalRundown = view.findViewById(R.id.rl_finalRundown);
        getRundownSize();

        // BUTTON CREATE RUNDOWN
        Button btnCreate = view.findViewById(R.id.btn_createRundown);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddRundownActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // BUTTON ADD RUNDOWN
        Button btn_addRundown = view.findViewById(R.id.btn_addRundown);
        btn_addRundown.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddRundownActivity.class);
            startActivityForResult(intent, 1);

        });

        // RECYCLERVIEW
        recyclerView = view.findViewById(R.id.rv_categoryOnRundown);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getRundownInCategory();

        return view;
    }

    private void getRundownSize() {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<RundownModel>> call = weddingService.getRundown(currentUserId);
        call.enqueue(new Callback<List<RundownModel>>() {
            @Override
            public void onResponse(Call<List<RundownModel>> call, Response<List<RundownModel>> response) {
                List<RundownModel> rundownModels = response.body();
                if (rundownModels.size() != 0) {
                    finalRundown.setVisibility(View.VISIBLE);
                    firstRundown.setVisibility(View.GONE);
                } else {
                    finalRundown.setVisibility(View.GONE);
                    firstRundown.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<RundownModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                getRundownInCategory();
                getRundownSize();
            }
        }
    }

    private void getRundownInCategory() {
        Log.d(TAG, "getRundownInCategory: getting rundown category");
        int currentUserId = Prefs.getInt("user_id", 0);

        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<CategoryRundownModel>> call = weddingService.getRundownCategory(currentUserId);
        call.enqueue(new Callback<List<CategoryRundownModel>>() {
            @Override
            public void onResponse(Call<List<CategoryRundownModel>> call, Response<List<CategoryRundownModel>> response) {
                if (response.isSuccessful()) {
                    List<CategoryRundownModel> categoryRundownModelList = response.body();
                    adapter = new CategoryRundownAdapter(categoryRundownModelList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Get data Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryRundownModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}