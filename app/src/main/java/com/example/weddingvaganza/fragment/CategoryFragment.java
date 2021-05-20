package com.example.weddingvaganza.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.activity.AddCategoryActivity;


public class CategoryFragment extends Fragment {

    public static CategoryFragment getInstance(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        LinearLayout addCategory = view.findViewById(R.id.add_category);
        addCategory.setOnClickListener(v -> {
            Intent category = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(category);
        });

        return view;
    }
}