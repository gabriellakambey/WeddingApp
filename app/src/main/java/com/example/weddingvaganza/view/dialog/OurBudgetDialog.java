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
import com.example.weddingvaganza.model.OurBudgetModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.model.responseModel.AddCategoryResponse;
import com.pixplicity.easyprefs.library.Prefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurBudgetDialog extends AppCompatDialogFragment {
    EditText etBudget;
    Button save;
    ImageView close;
    OurBudgetDialogListener listener;
    OurBudgetModel ourBudgetModel;
    int ourBudget;
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
    int currentUserId = Prefs.getInt("user_id", 0);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ourbudget, null);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();

        // BUTTON CLOSE
        close = view.findViewById(R.id.iv_closeDialogOurBudget);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // BUTTON DONE
        etBudget = view.findViewById(R.id.et_addOurBudget);
        save = view.findViewById(R.id.btn_addOurBudget);
        save.setOnClickListener(v -> {
            ourBudget = Integer.parseInt(etBudget.getText().toString());

            getUser();

            alertDialog.dismiss();


        });

        return alertDialog;
    }

    private void getUser() {
        Call<UserModel> call = weddingService.getOurBudget(currentUserId);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();

                ourBudgetModel = new OurBudgetModel(userModel.getUserId(), userModel.getUserName(),
                        userModel.getUserEmail(), userModel.getUserPassword(), userModel.getUserCouple(),
                        userModel.getTglPernikahan(), userModel.getNomorHp(), ourBudget);

                updateOurBudget();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    private void updateOurBudget() {
        Call<OurBudgetModel> call = weddingService.updateOurBudget(currentUserId, ourBudgetModel);
        call.enqueue(new Callback<OurBudgetModel>() {
            @Override
            public void onResponse(Call<OurBudgetModel> call, Response<OurBudgetModel> response) {
                listener.onAddOurBudget();
            }

            @Override
            public void onFailure(Call<OurBudgetModel> call, Throwable t) {

            }
        });
    }

    public void setListener(OurBudgetDialogListener listener) {
        this.listener = listener;
    }

    public interface OurBudgetDialogListener {
        void onAddOurBudget();
    }
}
