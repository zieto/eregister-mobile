package com.umk.diary.schedules;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.umk.diary.R;

import java.util.ArrayList;

public class Monday extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.frag_layout,container,false);

        ArrayList<String> data = new ArrayList<>();
        data.add("test");
        data.add("test2");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_activated_1, data);

        ListView lvData = view.findViewById(R.id.listView);
        lvData.setAdapter(adapter);

        return view;
    }
}
