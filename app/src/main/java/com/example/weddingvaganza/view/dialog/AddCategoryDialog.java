package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.responseModel.AddCategoryResponse;
import com.pixplicity.easyprefs.library.Prefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryDialog extends AppCompatDialogFragment {
    EditText etCatTitle;
    Button save;
    ImageView close;
    private CategoryDialogListener listener;
    int currentUserId = Prefs.getInt("user_id", 0);

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

            if (!title.isEmpty()) {
                onAddCategory(alertDialog, title);
            } else {
                Toast.makeText(getActivity(), "Field can not empty", Toast.LENGTH_SHORT).show();
            }

        });

        return alertDialog;
    }

    private void onAddCategory(AlertDialog alertDialog, String title) {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<AddCategoryResponse> call = weddingService.addCategory(title, currentUserId);
        call.enqueue(new Callback<AddCategoryResponse>() {
            @Override
            public void onResponse(Call<AddCategoryResponse> call, Response<AddCategoryResponse> response) {
                AddCategoryResponse addCategoryResponse = response.body();
                if (addCategoryResponse.getStatus().equals("success")) {

                    listener.onAddCategory();
                    alertDialog.dismiss();
                    Toast.makeText(getActivity(), "Success Add Category", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Failed Add Category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCategoryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setListener(CategoryDialogListener listener) {
        this.listener = listener;
    }

    public interface CategoryDialogListener {
        void onAddCategory ();
    }
}
