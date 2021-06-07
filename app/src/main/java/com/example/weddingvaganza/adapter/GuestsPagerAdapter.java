package com.example.weddingvaganza.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weddingvaganza.view.fragment.GuestListFragment;
import com.example.weddingvaganza.view.fragment.GuestsBookFragment;
import com.example.weddingvaganza.view.fragment.InvitationFragment;

public class GuestsPagerAdapter extends FragmentStateAdapter {
    public GuestsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1 :
                return new InvitationFragment();
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
