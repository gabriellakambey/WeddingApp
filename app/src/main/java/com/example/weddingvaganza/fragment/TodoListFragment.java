package com.example.weddingvaganza.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weddingvaganza.R;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class TodoListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        TabLayout tl_list = view.findViewById(R.id.tl_list);
        ViewPager vp_list = view.findViewById(R.id.vp_list);

        MainAdapter adapter = new MainAdapter(getFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.addFragment(CategoryFragment.getInstance(), "Category");
                adapter.addFragment(ScheduleFragment.getInstance(), "Schedule");
                adapter.addFragment(RundownFragment.getInstance(), "Rundown Event");

                vp_list.setAdapter(adapter);
                tl_list.setupWithViewPager(vp_list);
            }
        });

        return view;
    }

    private class MainAdapter extends FragmentPagerAdapter {
        List<String> stringList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }

        public void addFragment(Fragment fragment,String title){
            stringList.add(title);
            fragmentList.add(fragment);
        }
    }
}