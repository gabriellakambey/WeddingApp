package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListCategoryAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.AddCategoryResponse;
import com.example.weddingvaganza.model.AddScheduleResponse;
import com.example.weddingvaganza.model.CategoryModel;
import com.example.weddingvaganza.view.activity.HomeActivity;
import com.example.weddingvaganza.view.fragment.CategoryFragment;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryDialog extends AppCompatDialogFragment {
    EditText etCatTitle;
    Button save;
    ImageView close;
    private CategoryDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addcategory, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // close button dialog
        close = view.findViewById(R.id.iv_closeDialogCategory);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // save button dialog (add category button)
        etCatTitle = view.findViewById(R.id.et_addCategory);
        save = view.findViewById(R.id.btn_addCategory);
        save.setOnClickListener(v -> {
            String title = etCatTitle.getText().toString();
            int currentUserId = Prefs.getInt("user_id", 0);

            WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
            Call<AddCategoryResponse> call = weddingService.addCategory(title, currentUserId);
            call.enqueue(new Callback<AddCategoryResponse>() {
                @Override
                public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                    AddCategoryResponse addCategoryResponse = response.body();
                    if (addCategoryResponse.getStatus().equals("success")) {
                        List<CategoryModel> newCategoryList = new ArrayList<>();
                        newCategoryList.add(addCategoryResponse.getCategoryModel());

                        listener.onAddCategory();

                        Toast.makeText(getContext(), "Success Add Category", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

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

        return alertDialog;
    }

    public void setListener(CategoryDialogListener listener) {
        this.listener = listener;
    }

    public interface CategoryDialogListener {
        void onAddCategory ();
    }
}
