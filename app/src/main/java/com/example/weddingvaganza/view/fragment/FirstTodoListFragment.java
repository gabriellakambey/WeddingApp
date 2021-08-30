package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.view.activity.AddCategoryScheduleActivity;

public class FirstTodoListFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_todo_list, container, false);

        Button btnMakeList = view.findViewById(R.id.btn_makeListTodolist);
        btnMakeList.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddCategoryScheduleActivity.class);
            startActivity(intent);
        });

        return view;
    }
}