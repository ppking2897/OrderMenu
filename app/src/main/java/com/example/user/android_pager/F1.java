package com.example.user.android_pager;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
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


public class F1 extends Fragment {
    private int DATA_COUNT = 7;
    private PullToRefreshView mPullToRefreshView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f1,container,false);

        //下拉式更新
        mPullToRefreshView = (PullToRefreshView)view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                },1000);
            }
        });
        // 長方曲線圖表示
        BarChart chart_bar = (BarChart)view.findViewById(R.id.chart_bar);
        chart_bar.setData(getBarData());

        return view;
    }


    //曲線圖的設定

    private BarData getBarData(){
        BarDataSet dataSetA = new BarDataSet(getChartData(), getString(R.string.chart_title));
        //設定顏色
        dataSetA.setColors(getChartColors());
        //設定顯示字串
        dataSetA.setStackLabels(getStackLabels());

        List<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSetA); // add the datasets

        return new BarData(getLabels(), dataSets);
    }

    private String[] getStackLabels(){
        return new String[]{getString(R.string.chart_label_week1),
                getString(R.string.chart_label_week2),
                getString(R.string.chart_label_week3),
                getString(R.string.chart_label_week4)};
    }

    private int[] getChartColors() {
        int[] colors = new int[]{getResourceColor(R.color.chart_color_Others),
                getResourceColor(R.color.chart_color_KR),
                getResourceColor(R.color.chart_color_JP)
                ,getResourceColor(R.color.chart_color_US) };
        return colors;
    }
    private int getResourceColor(int resID){
        return getResources().getColor(resID);
    }

    // 每一個資料點就是一個Entry(y, x)，其中y的型態是float，x為int。
    // 以下範產生出(0,0), (1,2), (2,4), (3,6), (4,8)五個點

    private List<BarEntry> getChartData(){
        //final int DATA_COUNT = 8;

        List<BarEntry> chartData = new ArrayList<>();
        //每一個月都有四筆資料


        float revenue_US1 = 2000;  //最上層
        float revenue_JP1 = 4000;  //倒數三層
        float revenue_KR1 = 5000;  //倒數二層
        float revenue_Other1 = 1000; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US1, revenue_JP1, revenue_KR1, revenue_Other1}, 0));


        float revenue_US2 = 1000;  //最上層
        float revenue_JP2 = 3000;  //倒數三層
        float revenue_KR2 = 4000;  //倒數二層
        float revenue_Other2 = 4600; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US2, revenue_JP2, revenue_KR2, revenue_Other2}, 1));

        float revenue_US3 = 500;  //最上層
        float revenue_JP3 = 1000;  //倒數三層
        float revenue_KR3 = 2000;  //倒數二層
        float revenue_Other3 = 4000; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US3, revenue_JP3, revenue_KR3, revenue_Other3}, 2));

        float revenue_US4 = 1000;  //最上層
        float revenue_JP4 = 2000;  //倒數三層
        float revenue_KR4 = 3000;  //倒數二層
        float revenue_Other4 = 5000; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US4, revenue_JP4, revenue_KR4, revenue_Other4}, 3));

        float revenue_US5 = 700;  //最上層
        float revenue_JP5 = 2000;  //倒數三層
        float revenue_KR5 = 3500;  //倒數二層
        float revenue_Other5 = 5500; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US5, revenue_JP5, revenue_KR5, revenue_Other5}, 4));

        float revenue_US6 = 900;  //最上層
        float revenue_JP6 = 1500;  //倒數三層
        float revenue_KR6 = 3500;  //倒數二層
        float revenue_Other6 = 4000; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US6, revenue_JP6, revenue_KR6, revenue_Other6}, 5));

        float revenue_US7 = 1000;  //最上層
        float revenue_JP7 = 3000;  //倒數三層
        float revenue_KR7 = 4000;  //倒數二層
        float revenue_Other7 = 4800; //最下層
        chartData.add(new BarEntry(new float[]{revenue_US7, revenue_JP7, revenue_KR7, revenue_Other7}, 6));





        return chartData;
    }

    private List<String> getLabels(){
        List<String> chartLabels = new ArrayList<>();

        chartLabels.add("Mon");
        chartLabels.add("Tues");
        chartLabels.add("Wen");
        chartLabels.add("Thur");
        chartLabels.add("Fri");
        chartLabels.add("Sat");
        chartLabels.add("Sun");


        return chartLabels;
    }
    }

