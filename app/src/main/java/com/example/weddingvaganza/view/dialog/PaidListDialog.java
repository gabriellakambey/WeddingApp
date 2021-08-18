package com.example.weddingvaganza.view.dialog;

import android.app.Dialog;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.BudgetModel;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaidListDialog extends AppCompatDialogFragment {

    AlertDialog alertDialog;
    BudgetModel budgetModel, updateBudgetModel;
    TextView tvTitle, tvCost, tvNote;
    EditText etPaid;
    LinearLayout linearLayout;
    Button btnChange, btnPaid, btnCancel;
    Integer costUpdate;
    String title, note;
    int cost, budgetId;
    PaidDialogListener listener;
    int userId = Prefs.getInt("user_id", 0);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_paidlist, null);

        builder.setView(view);

        alertDialog = builder.create();

        // CLOSE BUTTON DIALOG
        ImageView close = view.findViewById(R.id.iv_closeDialogCategory);
        close.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        // SET DATA
        budgetModel = this.getArguments().getParcelable("budget selected");

        budgetId = budgetModel.getBudgetId();
        title = budgetModel.getTitleBudget();
        cost = budgetModel.getCostBudget();
        note = budgetModel.getNote();

        tvTitle = view.findViewById(R.id.tv_titlePaidDetail);
        tvTitle.setText(title);

        tvCost = view.findViewById(R.id.tv_addCostEstimate);
        tvCost.setText("IDR " + cost);

        tvNote = view.findViewById(R.id.tv_addNotes);
        tvNote.setText(note);

        // BUTTON CHANGE COST
        linearLayout = view.findViewById(R.id.ll_changeCost);
        btnChange = view.findViewById(R.id.btn_changeCostEstimate);
        btnChange.setOnClickListener(v -> {
            linearLayout.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
            btnChange.setVisibility(View.GONE);
        });

        // BUTTON CANCEL
        btnCancel = view.findViewById(R.id.btn_cancelCostEstimate);
        btnCancel.setOnClickListener(v -> {
            linearLayout.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            btnChange.setVisibility(View.VISIBLE);
        });

        // BUTTON PAID
        etPaid = view.findViewById(R.id.et_changeCost);
        btnPaid = view.findViewById(R.id.btn_paid);
        btnPaid.setOnClickListener(v -> {

            if (linearLayout.getVisibility() == View.VISIBLE) {
                costUpdate = Integer.parseInt(etPaid.getText().toString());
            } else {
                costUpdate = budgetModel.getPaid();
            }
//            Toast.makeText(getContext(), "ini datanya: " + costUpdate, Toast.LENGTH_SHORT).show();

            updateBudgetModel = new BudgetModel(budgetModel.getBudgetId(), title, cost, note, "true", costUpdate, userId);

            updateStatusBudget();

            alertDialog.dismiss();
        });

        return alertDialog;
    }

    private void updateStatusBudget() {
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<BudgetModel> call = weddingService.updateStatusBudget(budgetId, updateBudgetModel);
        call.enqueue(new Callback<BudgetModel>() {
            @Override
            public void onResponse(Call<BudgetModel> call, Response<BudgetModel> response) {
                listener.refreshTotalPaid();
            }

            @Override
            public void onFailure(Call<BudgetModel> call, Throwable t) {

            }
        });
    }

    public interface PaidDialogListener {
        public void refreshTotalPaid();
    }

    public void setListener (PaidDialogListener listener) {
        this.listener = listener;
    }
}
