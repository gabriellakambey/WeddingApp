package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.view.activity.CreateInvitationActivity;
import com.example.weddingvaganza.view.activity.ScheduleFromCategoryActivity;


public class InvitationFirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitation_first, container, false);

        Button button = view.findViewById(R.id.btn_createInvit);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateInvitationActivity.class);
            startActivity(intent);
        });

        return view;
    }


}