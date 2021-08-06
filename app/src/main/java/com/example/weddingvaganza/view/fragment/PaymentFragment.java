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
import com.example.weddingvaganza.adapter.PaymentRecyclerViewAdapter;
import com.example.weddingvaganza.model.PaymentModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<PaymentModel> paymentModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_payment, container, false);
        recyclerView = v.findViewById(R.id.rv_paidItem);
        PaymentRecyclerViewAdapter recyclerViewAdapter = new PaymentRecyclerViewAdapter(getContext(),paymentModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        paymentModels = new ArrayList<>();
        paymentModels.add(new PaymentModel("Example Payment Title",2000000,2000000));
        paymentModels.add(new PaymentModel("Example Payment Title",2000000,2000000));
        paymentModels.add(new PaymentModel("Example Payment Title",2000000,2000000));
        paymentModels.add(new PaymentModel("Example Payment Title",2000000,2000000));
        paymentModels.add(new PaymentModel("Example Payment Title",2000000,2000000));
    }
}