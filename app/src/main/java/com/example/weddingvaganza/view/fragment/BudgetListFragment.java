package com.example.weddingvaganza.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListBudgetAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.BudgetModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.view.activity.AddBudgetActivity;
import com.example.weddingvaganza.view.dialog.OurBudgetDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BudgetListFragment extends Fragment implements OurBudgetDialog.OurBudgetDialogListener,AddBudgetActivity.RefreshBudgetListener {

    private RecyclerView rv_budgetList;
    private List<BudgetModel> budgetModels;
    ListBudgetAdapter adapter;
    TextView etTotal, etOurBudget;
    Dialog dialog;
    int userId = Prefs.getInt("user_id", 0);
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_budget_list, container, false);


        // BUTTON ADD BUDGET
        Button btnAdd = v.findViewById(R.id.btn_addBudget);
        btnAdd.setOnClickListener(v1 -> {
            AddBudgetActivity activity = new AddBudgetActivity();
            activity.setListener(this::getBudget);
            Intent n = new Intent(getActivity(), activity.getClass());
            startActivity(n);
        });

        // BUTTON EDIT "OUR BUDGET"
        dialog = new Dialog(getContext());
        ImageView editBudget = v.findViewById(R.id.iv_editBudget);
        editBudget.setOnClickListener(v1 -> {
            openDialog();
        });

        // SET TOTAL BUDGET
        etTotal = v.findViewById(R.id.tv_totalBudgetUp);
        setTotalBudget();

        // SET OUR BUDGET
        etOurBudget = v.findViewById(R.id.tv_ourBudget);
        setOurBudget();


        // RECYCLER VIEW LIST BUDGET
        rv_budgetList = v.findViewById(R.id.rv_budgetItem);
        adapter = new ListBudgetAdapter(getContext(),budgetModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_budgetList.setLayoutManager(layoutManager);

        getBudget();

        return v;
    }

    private void openDialog() {
        OurBudgetDialog ourBudgetDialog = new OurBudgetDialog();

        ourBudgetDialog.setListener((OurBudgetDialog.OurBudgetDialogListener)this);

        ourBudgetDialog.show(getFragmentManager(), "add our budget");
    }

    private void setOurBudget() {
        Call<UserModel> call = weddingService.getOurBudget(userId);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel model = response.body();
                etOurBudget.setText("IDR " + model.getOurBudget());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });

    }

    private void setTotalBudget() {
        Call<Integer> call = weddingService.getBudgetTotal(userId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer total = response.body();
                etTotal.setText("IDR " + total);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void getBudget() {
        Call<List<BudgetModel>> call = weddingService.getBudgetList(userId);
        call.enqueue(new Callback<List<BudgetModel>>() {
            @Override
            public void onResponse(Call<List<BudgetModel>> call, Response<List<BudgetModel>> response) {
                List<BudgetModel> budgetModels = response.body();
                adapter = new ListBudgetAdapter(getContext(), budgetModels);
                adapter.setBudgetModels(budgetModels);
                rv_budgetList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BudgetModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void refreshBudget() {
        getBudget();
    }

    @Override
    public void onAddOurBudget() {
        setOurBudget();
    }
}