package com.example.weddingvaganza.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.activity.AddRundownActivity;


public class RundownFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rundown, container, false);

        Button btn_addRundown = view.findViewById(R.id.btn_addRundown);
        btn_addRundown.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddRundownActivity.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule,menu);

        // hide item share
        menu.findItem(R.id.schedule_share).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.schedule_print) {
            Toast.makeText(getActivity(), "Print", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}