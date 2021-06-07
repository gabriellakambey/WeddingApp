package com.example.weddingvaganza.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weddingvaganza.view.fragment.BudgetListFragment;
import com.example.weddingvaganza.view.fragment.PaymentFragment;

public class BudgetPagerAdapter extends FragmentStateAdapter {
    public BudgetPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1 :
                return new PaymentFragment();
        }
        return new BudgetListFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
