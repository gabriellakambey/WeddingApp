package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.view.activity.BudgetActivity;
import com.example.weddingvaganza.view.activity.GuestsActivity;
import com.example.weddingvaganza.view.activity.VendorActivity;

public class FinalHomeFragment extends Fragment {
    private ImageButton ibGuests, ibBudget, ibVendor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final_home, container, false);

        ibGuests = view.findViewById(R.id.ib_guests);
        ibGuests.setOnClickListener(v -> {
            Intent guests = new Intent(getActivity(), GuestsActivity.class);
            startActivity(guests);
        });

        ibBudget = view.findViewById(R.id.ib_budget);
        ibBudget.setOnClickListener(v -> {
            Intent budget = new Intent(getActivity(), BudgetActivity.class);
            startActivity(budget);
        });

        ibVendor = view.findViewById(R.id.ib_vendor);
        ibVendor.setOnClickListener(v -> {
            Intent vendor = new Intent(getActivity(), VendorActivity.class);
            startActivity(vendor);
        });

        return view;
    }
}