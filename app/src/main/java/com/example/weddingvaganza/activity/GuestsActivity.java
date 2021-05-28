package com.example.weddingvaganza.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.weddingvaganza.R;
import com.google.android.material.tabs.TabLayout;

public class GuestsActivity extends AppCompatActivity {
    private TabLayout tv_guests;
    private ViewPager vp_guests;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}