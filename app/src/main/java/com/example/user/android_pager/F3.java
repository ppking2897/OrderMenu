package com.example.user.android_pager;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.yalantis.phoenix.PullToRefreshView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;


public class F3 extends Fragment {
    private RecyclerView recyclerViewNumber1;
    private RecyclerView recyclerViewNumber2;
    private RecyclerView recyclerViewNumber3;

    private Order_Info_Adapter01 order_info_adapter01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f3,container,false);


        recyclerViewNumber1 = (RecyclerView) view.findViewById(R.id.recyclerView_Number1);
        recyclerViewNumber1.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("SSSS");
        list.add("SSSS");
        list.add("SSSS");
        list.add("SSSS");
        list.add("SSSS");
        list.add("SSSS");
        list.add("SSSS");

        list1.add("XXXX");
        list1.add("XXXX");
        list1.add("XXXX");
        list1.add("XXXX");
        list1.add("XXXX");
        list1.add("XXXX");
        list1.add("XXXX");

        order_info_adapter01 = new Order_Info_Adapter01(getContext(),list,list1);

        recyclerViewNumber1.setAdapter(order_info_adapter01);


        recyclerViewNumber2 = (RecyclerView) view.findViewById(R.id.recyclerView_Number2);
        recyclerViewNumber2.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewNumber3 = (RecyclerView) view.findViewById(R.id.recyclerView_Number3);
        recyclerViewNumber3.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


}

