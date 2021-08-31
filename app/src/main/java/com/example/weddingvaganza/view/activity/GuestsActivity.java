package com.example.weddingvaganza.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.pagerAdapter.GuestsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class GuestsActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    GuestsPagerAdapter guestsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);

        tabLayout = findViewById(R.id.tl_guests);
        viewPager2 = findViewById(R.id.vp_guests);

        Button btnBack = findViewById(R.id.btnBackGuests);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });

        FragmentManager fm = getSupportFragmentManager();
        guestsAdapter = new GuestsPagerAdapter(fm, getLifecycle());
        viewPager2.setAdapter(guestsAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Guests List"));
        tabLayout.addTab(tabLayout.newTab().setText("Invitation"));
        tabLayout.addTab(tabLayout.newTab().setText("Guests Book"));

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