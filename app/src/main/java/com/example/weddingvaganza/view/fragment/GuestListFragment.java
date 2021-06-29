package com.example.weddingvaganza.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.GuestGroupAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.GuestGroupModel;
import com.example.weddingvaganza.view.activity.AddGuestActivity;
import com.example.weddingvaganza.view.activity.GuestListActivity;
import com.example.weddingvaganza.view.activity.HomeActivity;
import com.example.weddingvaganza.view.activity.SignInActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestListFragment extends Fragment implements GuestGroupAdapter.ClickedItem{
    LinearLayout addGuest;
    RecyclerView recyclerView;
    GuestGroupAdapter adapter;
    WeddingService weddingService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guest_list, container, false);

        // button add guest
        addGuest = view.findViewById(R.id.add_guest);
        addGuest.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddGuestActivity.class);
            startActivity(intent);
        });

        // recycler list guest group
        recyclerView = view.findViewById(R.id.rv_listGuestGroup);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new GuestGroupAdapter(this::ClickedGuestGroup);

        getListGuestGroup();

        return view;
    }

    private void getListGuestGroup() {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestGroupModel>> call = weddingService.getGuestGroup();
        call.enqueue(new Callback<List<GuestGroupModel>>() {
            @Override
            public void onResponse(Call<List<GuestGroupModel>> call, Response<List<GuestGroupModel>> response) {
                if (response.isSuccessful()) {
                    List<GuestGroupModel> guestGroupModels = response.body();
                    adapter.setData(guestGroupModels);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<GuestGroupModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void ClickedGuestGroup(GuestGroupModel guestGroupModel) {
        startActivity(new Intent(getActivity(), GuestListActivity.class).putExtra("data", guestGroupModel));
    }
}