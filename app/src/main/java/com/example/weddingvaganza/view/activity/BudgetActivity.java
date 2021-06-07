package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.BudgetPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class BudgetActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    BudgetPagerAdapter budgetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        tabLayout = findViewById(R.id.tl_budget);
        viewPager2 = findViewById(R.id.vp_budget);

        FragmentManager fm = getSupportFragmentManager();
        budgetAdapter = new BudgetPagerAdapter(fm, getLifecycle());
        viewPager2.setAdapter(budgetAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Budget List"));
        tabLayout.addTab(tabLayout.newTab().setText("Payment"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}