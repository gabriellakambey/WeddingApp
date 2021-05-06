package com.example.weddingvaganza.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weddingvaganza.R;


public class RundownFragment extends Fragment {

    public static RundownFragment getInstance(){
        RundownFragment rundownFragment = new RundownFragment();
        return rundownFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rundown, container, false);
    }
}