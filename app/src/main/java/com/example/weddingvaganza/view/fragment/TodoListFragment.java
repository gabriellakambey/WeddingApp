package com.example.weddingvaganza.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.pagerAdapter.TodoListPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TodoListFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TodoListPagerAdapter pagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        viewPager2 = view.findViewById(R.id.vp_todoList);
        tabLayout = view.findViewById(R.id.tl_todoList);

        // viewpager2
        FragmentManager fm = getFragmentManager();
        pagerAdapter = new TodoListPagerAdapter(fm, getLifecycle());
        viewPager2.setAdapter(pagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Category"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.addTab(tabLayout.newTab().setText("Rundown"));

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


        return view;
    }

}