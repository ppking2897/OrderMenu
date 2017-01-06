package com.example.user.android_pager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;


public class F3 extends Fragment {
    private RecyclerView recyclerViewNumber1;
    private RecyclerView recyclerViewNumber2;
    private RecyclerView recyclerViewNumber3;

    private Order_Info_Adapter01 order_info_adapter01;
    private Order_Info_Adapter01 order_info_adapter02;
    private Order_Info_Adapter01 order_info_adapter03;

    private FireBase fireBase ;
    private MyhandlerF3 myhandlerF3;
    private TextView foodText;
    private TextView numText;
    private Button seat01 , seat02 ,seat03;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireBase = new FireBase();
        myhandlerF3 = new MyhandlerF3();



        startUpdateTimer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.f3,container,false);

        seat01 = (Button)view.findViewById(R.id.seat01);
        seat01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireBase.orderName01.clear();
                fireBase.orderNumber01.clear();
                fireBase.orderPrice01 = "";

                fireBase.DeleteOrderInfo("01");
            }
        });

        seat02 = (Button)view.findViewById(R.id.seat02);
        seat02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireBase.orderName02.clear();
                fireBase.orderNumber02.clear();
                fireBase.orderPrice02 = "";

                fireBase.DeleteOrderInfo("02");
            }
        });

        seat03 = (Button)view.findViewById(R.id.seat03);
        seat03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fireBase.orderName03.clear();
                fireBase.orderNumber03.clear();
                fireBase.orderPrice03 = "";

                fireBase.DeleteOrderInfo("03");
            }
        });


        fireBase.ReadOrderInfo01("numberseat" , "01");
        fireBase.ReadOrderInfo02("numberseat" , "02");
        fireBase.ReadOrderInfo03("numberseat" , "03");


        recyclerViewNumber1 = (RecyclerView) view.findViewById(R.id.recyclerView_Number1);
        recyclerViewNumber1.setLayoutManager(new LinearLayoutManager(getContext()));



        order_info_adapter01 = new Order_Info_Adapter01(getContext(),fireBase.orderName01,
                                                        fireBase.orderNumber01);

        recyclerViewNumber1.setAdapter(order_info_adapter01);




        recyclerViewNumber2 = (RecyclerView) view.findViewById(R.id.recyclerView_Number2);
        recyclerViewNumber2.setLayoutManager(new LinearLayoutManager(getContext()));


        order_info_adapter02 = new Order_Info_Adapter01(getContext(),fireBase.orderName02,
                fireBase.orderNumber02);

        recyclerViewNumber2.setAdapter(order_info_adapter02);




        recyclerViewNumber3 = (RecyclerView) view.findViewById(R.id.recyclerView_Number3);
        recyclerViewNumber3.setLayoutManager(new LinearLayoutManager(getContext()));


        order_info_adapter03 = new Order_Info_Adapter01(getContext(),fireBase.orderName03,
                fireBase.orderNumber03);

        recyclerViewNumber3.setAdapter(order_info_adapter03);

        return view;
    }

    private void startUpdateTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                myhandlerF3.sendEmptyMessage(0);
            }
        }, 0, 1000);
    }

    public class MyhandlerF3 extends Handler {

        @Override
        public void handleMessage(Message msg) {
            order_info_adapter01.notifyDataSetChanged();
            order_info_adapter02.notifyDataSetChanged();
            order_info_adapter03.notifyDataSetChanged();
        }
    }


}

