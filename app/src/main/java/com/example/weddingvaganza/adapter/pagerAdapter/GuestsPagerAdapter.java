package com.example.weddingvaganza.adapter.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weddingvaganza.api.WeddingApi;
import com.example.weddingvaganza.api.WeddingService;
import com.example.weddingvaganza.model.InvitationModel;
import com.example.weddingvaganza.view.fragment.GuestListFragment;
import com.example.weddingvaganza.view.fragment.GuestsBookFragment;
import com.example.weddingvaganza.view.fragment.InvitationFirstFragment;
import com.example.weddingvaganza.view.fragment.InvitationFragment;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuestsPagerAdapter extends FragmentStateAdapter {

    List<InvitationModel> invitationModels;
    int currentUserId = Prefs.getInt("user_id", 0);

    public GuestsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

        // GET DATA INVITATION
        WeddingService weddingService = WeddingApi.getRetrofit().create(WeddingService.class);
        Call<List<InvitationModel>> call1 = weddingService.getInvitation(currentUserId);
        call1.enqueue(new Callback<List<InvitationModel>>() {
            @Override
            public void onResponse(Call<List<InvitationModel>> call, Response<List<InvitationModel>> response) {
                invitationModels = response.body();
            }

            @Override
            public void onFailure(Call<List<InvitationModel>> call, Throwable t) {

            }
        });

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 1 :
                if (invitationModels.size() != 0) {
                    return new InvitationFragment();
                } else {
                    return new InvitationFirstFragment();
                }

            case 2 :
                return new GuestsBookFragment();
        }
        return new GuestListFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
