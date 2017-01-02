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
    private TextView textViewF1;
    private UIHandler uiHandler;
    private int DATA_COUNT = 5;
    private PullToRefreshView mPullToRefreshView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHandler = new UIHandler();
//


    }


    private void parseJSON(String json){
        LinkedList accountInfo = new LinkedList<>();
        try{

            JSONObject jsonObject = new JSONArray(json).getJSONObject(0);

            String stringNo1 = jsonObject.getString("Account");
            accountInfo.add(stringNo1);
            String stringNo2 = jsonObject.getString("SeatIdNumber");
            accountInfo.add(stringNo2);
            String stringNo3 = jsonObject.getString("Checkout");
            accountInfo.add(stringNo3);



            Message mesg = new Message();
            Bundle data = new Bundle();
            data.putCharSequence("data0",accountInfo.get(0).toString());
            data.putCharSequence("data1",accountInfo.get(1).toString());
            data.putCharSequence("data2",accountInfo.get(2).toString());
            mesg.setData(data);
            mesg.what=0;
            uiHandler.sendMessage(mesg);


        }catch (Exception e){
            Log.v("ppking", "Error : " + e.toString());
        }
    }

    private class UIHandler extends android.os.Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0 :
                    textViewF1.setText("Accound : "+msg.getData().getCharSequence("data0")+"\n");
                    textViewF1.append("SeatIdNumber : "+msg.getData().getCharSequence("data1")+"\n");
                    textViewF1.append("Checkout : $"+msg.getData().getCharSequence("data2")+"\n");
                    break;
            }
        }
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
        BarDataSet dataSetA = new BarDataSet(getChartData(),
                getString(R.string.chart_title));
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
        final int DATA_COUNT = 6;

        List<BarEntry> chartData = new ArrayList<>();
        //每一個月都有四筆資料

        for(int i=1;i<DATA_COUNT;i++){
            float revenue_US = i*2;  //最上層
            float revenue_JP = i*3;  //倒數三層
            float revenue_KR = i*4;  //倒數二層
            float revenue_Other = i*1; //最下層
            chartData.add(new BarEntry(new float[]{revenue_Other, revenue_KR, revenue_JP, revenue_US}, i-1));
        }
        return chartData;
    }

    private List<String> getLabels(){
        List<String> chartLabels = new ArrayList<>();
        for(int i=0;i<DATA_COUNT;i++){
            chartLabels.add((i+1)+"月");
        }
        return chartLabels;
    }
    }

