package com.example.weddingvaganza.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListBudgetAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.BudgetModel;
import com.example.weddingvaganza.model.UserModel;
import com.example.weddingvaganza.view.activity.AddBudgetActivity;
import com.example.weddingvaganza.view.activity.AddRundownActivity;
import com.example.weddingvaganza.view.dialog.OurBudgetDialog;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class BudgetListFragment extends Fragment implements OurBudgetDialog.OurBudgetDialogListener {

    private RecyclerView rv_budgetList;
    private List<BudgetModel> budgetModels;
    ListBudgetAdapter adapter;
    TextView etTotal, etOurBudget;
    Dialog dialog;
    int userId = Prefs.getInt("user_id", 0);
    WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);

    RelativeLayout finalBudget;
    LinearLayout firstBudget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_budget_list, container, false);

        // BUDGET VIEW IN CONDITION
        finalBudget = v.findViewById(R.id.rl_finalBudget);
        firstBudget = v.findViewById(R.id.ll_firstBudget);
        getBudgetSize();

        // BUTTON ADD CREATE BUDGET
        Button btnCreate = v.findViewById(R.id.btn_createBudget);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddBudgetActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // BUTTON ADD BUDGET
        Button btnAdd = v.findViewById(R.id.btn_addBudget);
        btnAdd.setOnClickListener(v1 -> {
            Intent intent = new Intent(getContext(), AddBudgetActivity.class);
            startActivityForResult(intent, 1);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_budgetList.setLayoutManager(layoutManager);

        getBudget();

        return v;
    }

    private void getBudgetSize() {
        Call<List<BudgetModel>> call = weddingService.getBudgetList(userId);
        call.enqueue(new Callback<List<BudgetModel>>() {
            @Override
            public void onResponse(Call<List<BudgetModel>> call, Response<List<BudgetModel>> response) {
                List<BudgetModel> modelList = response.body();
                if (modelList.size() != 0) {
                    finalBudget.setVisibility(View.VISIBLE);
                    firstBudget.setVisibility(View.GONE);
                } else {
                    firstBudget.setVisibility(View.VISIBLE);
                    finalBudget.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BudgetModel>> call, Throwable t) {

            }
        });
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                getBudget();
                getBudgetSize();
                setTotalBudget();
            }
        }
    }

    @Override
    public void onAddOurBudget() {
        setOurBudget();
    }
}