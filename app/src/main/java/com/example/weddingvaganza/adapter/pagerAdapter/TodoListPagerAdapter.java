package com.example.weddingvaganza.adapter.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weddingvaganza.view.fragment.CategoryFragment;
import com.example.weddingvaganza.view.fragment.RundownFragment;
import com.example.weddingvaganza.view.fragment.ScheduleFragment;

public class TodoListPagerAdapter extends FragmentStateAdapter {

    public TodoListPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new ScheduleFragment();
            case 2:
                return new RundownFragment();

        }
        return new CategoryFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
