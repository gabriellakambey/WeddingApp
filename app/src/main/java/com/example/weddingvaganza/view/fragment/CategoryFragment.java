package com.example.weddingvaganza.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListCategoryAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.AddCategoryResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.view.activity.ScheduleFromCategoryActivity;
import com.example.weddingvaganza.view.dialog.AddCategoryDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment implements AddCategoryDialog.CategoryDialogListener, ListCategoryAdapter.ClickedItem {

    Dialog dialog;
    ListCategoryAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // open dialog
        dialog = new Dialog(getContext());
        LinearLayout addCategory = view.findViewById(R.id.add_category);
        addCategory.setOnClickListener(v -> {
            openDialog();
        });

        // recyclerview list category
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
                if (response.isSuccessful()) {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void openDialog() {
        AddCategoryDialog addCategoryDialog = new AddCategoryDialog();

        addCategoryDialog.setListener((AddCategoryDialog.CategoryDialogListener)this);

        addCategoryDialog.show(getFragmentManager(), "add category");
    }

    @Override
    public void onAddCategory() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ClickedCategory(CategoryModel categoryModel) {
        startActivity(new Intent(getActivity(), ScheduleFromCategoryActivity.class).putExtra("data", categoryModel));
    }
}