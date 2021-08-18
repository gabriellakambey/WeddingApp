package com.example.weddingvaganza.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.ListInvitedGuestAdapter;
import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.GuestModel;
import com.example.weddingvaganza.view.dialog.InvitedGuestDetail;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvitationFragment extends Fragment implements ListInvitedGuestAdapter.ClickedItem {

    int totalInvited;
    TextView textView;
    RecyclerView recyclerView;
    ListInvitedGuestAdapter adapter;
    private WeddingService weddingService;
    private int currentUser = Prefs.getInt("user_id", 0);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitation, container, false);

        // RECYCLER VIEW INVITED GUEST
        recyclerView = view.findViewById(R.id.rv_invitedGuest);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        textView = view.findViewById(R.id.tv_totInvitedGuest);
        invitedGuest();

        return view;
    }

    private void invitedGuest() {
        weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<GuestModel>> call = weddingService.getInvitedGuest("invited", currentUser);
        call.enqueue(new Callback<List<GuestModel>>() {
            @Override
            public void onResponse(Call<List<GuestModel>> call, Response<List<GuestModel>> response) {
                List<GuestModel> guestModels = response.body();
                adapter = new ListInvitedGuestAdapter(getContext(), guestModels);
                totalInvited = guestModels.size();
                adapter.setInvitedGuest(guestModels);
                adapter.clickedInvitedListener(InvitationFragment.this::ClickedInvitedGuest);

                recyclerView.setAdapter(adapter);

                // set total guest invited
                textView.setText(totalInvited + " guests");

            }

            @Override
            public void onFailure(Call<List<GuestModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void ClickedInvitedGuest(GuestModel guestModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("invited selected", guestModel);

        InvitedGuestDetail invitedGuestDetail = new InvitedGuestDetail();

        invitedGuestDetail.setArguments(bundle);

        invitedGuestDetail.show(getFragmentManager(), "detail invited guest");
    }
}

