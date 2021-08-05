package com.example.weddingvaganza.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.BudgetRecyclerViewAdapter;
import com.example.weddingvaganza.model.BudgetModel;

import java.util.ArrayList;
import java.util.List;


public class BudgetListFragment extends Fragment {

    View v;
    private RecyclerView rv_budgetList;
    private List<BudgetModel> budgetModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_budget_list, container, false);
        rv_budgetList = v.findViewById(R.id.rv_budgetItem);
        BudgetRecyclerViewAdapter recyclerViewAdapter = new BudgetRecyclerViewAdapter(getContext(),budgetModels);
        rv_budgetList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_budgetList.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        budgetModels = new ArrayList<>();
        budgetModels.add(new BudgetModel("Example Title Budget",2000000));
        budgetModels.add(new BudgetModel("Example Title Budget",2000000));
        budgetModels.add(new BudgetModel("Example Title Budget",2000000));
        budgetModels.add(new BudgetModel("Example Title Budget",2000000));
        budgetModels.add(new BudgetModel("Example Title Budget",2000000));
        budgetModels.add(new BudgetModel("Example Title Budget",2000000));
    }
}