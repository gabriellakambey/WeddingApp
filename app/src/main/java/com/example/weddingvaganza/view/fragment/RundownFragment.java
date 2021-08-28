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
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.CategoryRundownAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryRundownModel;
import com.example.weddingvaganza.view.activity.AddBudgetActivity;
import com.example.weddingvaganza.view.activity.AddRundownActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RundownFragment extends Fragment implements AddRundownActivity.onAddRundown {
    private static final String TAG = "RundownFragment";

    RecyclerView recyclerView;
    CategoryRundownAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rundown, container, false);

        // BUTTON ADD RUNDOWN
        Button btn_addRundown = view.findViewById(R.id.btn_addRundown);
        btn_addRundown.setOnClickListener(v -> {
            AddRundownActivity addRundownActivity = new AddRundownActivity();
            addRundownActivity.setListener(this::refreshRundown);
            Intent intent = new Intent(getActivity(), addRundownActivity.getClass());
            startActivity(intent);
        });

        // RECYCLERVIEW
        recyclerView = view.findViewById(R.id.rv_categoryOnRundown);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getRundownInCategory();

        return view;
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

    @Override
    public void refreshRundown() {
        getRundownInCategory();
    }
}