package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListCategoryAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.view.activity.ScheduleFromCategoryActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCategoryFragment extends Fragment implements ListCategoryAdapter.ClickedItem{
    RecyclerView recyclerView;
    ListCategoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_category, container, false);

        recyclerView = view.findViewById(R.id.rv_listCategory1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new ListCategoryAdapter(this::ClickedCategory);

        getAllCategory();

        return view;
    }

    private void getAllCategory() {
        int currentUserId = Prefs.getInt("user_id", 0);
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<CategoryModel>> call = weddingService.getCategory(currentUserId);
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {

                if (response.isSuccessful()){
                    List<CategoryModel> categoryModels = response.body();
                    adapter.setData(categoryModels);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void ClickedCategory(CategoryModel categoryModel) {

        startActivity(new Intent(getActivity(), ScheduleFromCategoryActivity.class).putExtra("data", categoryModel));

    }
}