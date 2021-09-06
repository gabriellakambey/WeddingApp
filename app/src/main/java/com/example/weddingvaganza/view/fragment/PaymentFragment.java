package com.example.weddingvaganza.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.PaymentAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.BudgetModel;
import com.example.weddingvaganza.model.PaymentModel;
import com.example.weddingvaganza.view.activity.GuestListActivity;
import com.example.weddingvaganza.view.dialog.PaidListDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentFragment extends Fragment implements PaymentAdapter.ClickedItem, PaidListDialog.PaidDialogListener {

    private RecyclerView rv_payment;
    private List<BudgetModel> budgetModels;
    PaymentAdapter adapter;
    TextView tvTotalPaid;
    RelativeLayout finalPayment;
    LinearLayout firstPayment;
    int userId = Prefs.getInt("user_id", 0);
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        // PAYMENT VIEW IN CONDITION
        finalPayment = v.findViewById(R.id.rl_finalPayment);
        firstPayment = v.findViewById(R.id.ll_firstPayment);
        getPaymentSize();

        // SET TOTAL PAID
        tvTotalPaid = v.findViewById(R.id.tv_totalBudgetUp);
        setTotalPaid();

        // RECYCLER VIEW LIST PAYMENT
        rv_payment = v.findViewById(R.id.rv_paidItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_payment.setLayoutManager(layoutManager);

        getPaidList();

        return v;

    }

    private void getPaymentSize() {
        Call<List<BudgetModel>> call = weddingService.getBudgetList(userId);
        call.enqueue(new Callback<List<BudgetModel>>() {
            @Override
            public void onResponse(Call<List<BudgetModel>> call, Response<List<BudgetModel>> response) {
                List<BudgetModel> modelList = response.body();
                if (modelList.size() != 0) {
                    finalPayment.setVisibility(View.VISIBLE);
                    firstPayment.setVisibility(View.GONE);
                } else {
                    firstPayment.setVisibility(View.VISIBLE);
                    finalPayment.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BudgetModel>> call, Throwable t) {

            }
        });
    }

    private void setTotalPaid() {
        Call<Integer> call = weddingService.getCostTotal("true", userId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer total = response.body();
                tvTotalPaid.setText("IDR " + total);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void getPaidList() {
        Call<List<BudgetModel>> call = weddingService.getBudgetList(userId);
        call.enqueue(new Callback<List<BudgetModel>>() {
            @Override
            public void onResponse(Call<List<BudgetModel>> call, Response<List<BudgetModel>> response) {
                List<BudgetModel> budgetModels = response.body();
                adapter = new PaymentAdapter(getContext(), budgetModels);
                adapter.setPaymentModels(budgetModels);
                adapter.clickedListener(PaymentFragment.this::ClickedPayment);
                rv_payment.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BudgetModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void ClickedPayment(BudgetModel budgetModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("budget selected", budgetModel);

        PaidListDialog paidListDialog = new PaidListDialog();

        paidListDialog.setArguments(bundle);

        paidListDialog.setListener(this::refreshTotalPaid);

        paidListDialog.show(getActivity().getSupportFragmentManager(), "paid");
    }

    @Override
    public void refreshTotalPaid() {
        getPaidList();
        setTotalPaid();
    }
}