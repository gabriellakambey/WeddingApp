package com.example.weddingvaganza.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weddingvaganza.R;
import com.example.weddingvaganza.adapter.PagerAdapter;
import com.example.weddingvaganza.adapter.TodoListAdapter;
import com.google.android.material.tabs.TabLayout;

public class TodoListFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPager viewPager;
    TodoListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        tabLayout = view.findViewById(R.id.tl_todoList);
//        viewPager2 = view.findViewById(R.id.vp_todoList);
        viewPager = view.findViewById(R.id.viewPager);


        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.AddFragment(new CategoryFragment(),"Category");
        adapter.AddFragment(new ScheduleFragment(),"Schedule");
        adapter.AddFragment(new RundownFragment(),"Rundown");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        FragmentManager fm = getFragmentManager();
//        adapter = new TodoListAdapter(fm, getLifecycle());
//        viewPager2.setAdapter(adapter);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Category"));
//        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
//        tabLayout.addTab(tabLayout.newTab().setText("Rundown"));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager2.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
//    }
}