package com.example.weddingvaganza.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weddingvaganza.R;

public class ListCategoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String items[] = new String[] {"Prewedding","Blessing of Church","Wedding Ceremony"};
        ListView listView = (ListView) view.findViewById(R.id.lv_listCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0)
        {
            Toast.makeText(getActivity(), "Prewedding", Toast.LENGTH_SHORT).show();
        }
        if(position==1)
        {
            Toast.makeText(getActivity(), "Blessing of Church", Toast.LENGTH_SHORT).show();
        }
        if(position==2)
        {
            Toast.makeText(getActivity(), "Wedding Ceremony", Toast.LENGTH_SHORT).show();
        }
    }
}