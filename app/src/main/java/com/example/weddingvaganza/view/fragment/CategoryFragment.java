package com.example.weddingvaganza.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.AddCategoryResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.view.activity.AddCategoryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Dialog dialog;
    List<CategoryModel> categoryModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        dialog = new Dialog(getContext());
        LinearLayout addCategory = view.findViewById(R.id.add_category);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategoryDialog();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // show fragment when data exist
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<CategoryModel>> call = weddingService.getCategory();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                List<CategoryModel> categoryModels = response.body();
                if (categoryModels.size()!=0) {
                    Toast.makeText(getContext(), "Data available", Toast.LENGTH_SHORT).show();
                    fragment = new ListCategoryFragment();
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fl_categoryEvent, fragment);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });

        //
    }

    // enable toolbar menu in this fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_schedule,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.schedule_print) {
            Toast.makeText(getActivity(), "Print", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void addCategoryDialog() {
        dialog.setContentView(R.layout.dialog_addcategory);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // button close dialog
        ImageView close = dialog.findViewById(R.id.iv_closeDialogCategory);
        close.setOnClickListener(v -> {
            dialog.dismiss();
        });

        // button add category
        EditText etCatTitle = dialog.findViewById(R.id.et_addCategory);
        Button save = dialog.findViewById(R.id.btn_addCategory);
        save.setOnClickListener(v -> {
            String title = etCatTitle.getText().toString();

            WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
            Call<AddCategoryResponse> call = weddingService.addCategory(title);
            call.enqueue(new Callback<AddCategoryResponse>() {
                @Override
                public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                    AddCategoryResponse addCategoryResponse = response.body();
                    if (addCategoryResponse.getStatus().equals("success")) {
                        Toast.makeText(getContext(), "Success Add Category", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed Add Category", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        });


        dialog.show();
    }
}