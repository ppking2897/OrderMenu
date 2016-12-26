package com.example.user.android_pager;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class F2 extends Fragment {
    private PullToRefreshView mPullToRefreshView;
    private TextView textViewF2,textViewF21;
    private UIHandler uiHandler;
    private HashMap hashMap;
    private CharSequence [] foodNameEN = {"porknoodle"};
    private String [] foodNameCH = {"肉絲炒麵" , "牛肉炒麵" , "機肉炒麵" , "豬肉炒飯" , "牛肉炒飯 "
            , "雞肉炒飯" , "蛤仔湯" , "隔間肉湯" , "豬肝湯" };
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHandler = new UIHandler();


    }

    private void parseJSON(String json){
        Message mesg = new Message();
        Bundle data = new Bundle();
        Bundle data2 = new Bundle();
        LinkedList menuInfo = new LinkedList<>();
        LinkedList priceInfo = new LinkedList<>();
        try{

            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0 ; i < jsonArray.length(); i++) {
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                JSONObject obj = jsonArray1.getJSONObject(0);


                String stringNo1 = obj.getString("FoodName");
                Log.v("ppking" , "stringNo1 : " +stringNo1);
                Log.v("ppking" , "jsonArray.length() : " +jsonArray.length());
                menuInfo.add(stringNo1);
                data.putCharSequence("menu"+i,menuInfo.get(i).toString());

                jsonArray1 = jsonArray.getJSONArray(i);
                obj = jsonArray1.getJSONObject(1);


                String stringNo2 = obj.getString("Price");
                priceInfo.add(stringNo2);
                Log.v("ppking" , "stringNo2 : " +data2);
                data2.putCharSequence("price"+i,priceInfo.get(i).toString());

            }


            //data.putCharSequence("data1",accountInfo.get(1).toString());
            mesg.setData(data);
            mesg.what=0;
            uiHandler.sendMessage(mesg);

            mesg.setData(data2);
            mesg.what=1;
            uiHandler.sendMessage(mesg);


//            for(int i = 0 ; i < jsonArray.length(); i++) {
//                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
//                JSONObject obj = jsonArray1.getJSONObject(1);
//                String stringNo2 = obj.getString("Price");
//                priceInfo.add(stringNo2);
//                Log.v("ppking" , "stringNo2 : " +data2);
//                data2.putCharSequence("price"+i,priceInfo.get(i).toString());
//            }
//            mesg.setData(data2);
//            mesg.what=1;
//            uiHandler.sendMessage(mesg);


        }catch (Exception e){
            Log.v("ppking", "ErrorF2 : " + e.toString());
        }
    }

    private class UIHandler extends android.os.Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0 :
                    for (int i = 0 ; msg.getData().getCharSequence("menu"+i)!=null ; i++ ) {

                        textViewF2.append("textViewF2 : "+msg.getData().getCharSequence("menu"+i)+"\n");

                    }
                    break;
                case 1 :
                    for (int i = 0 ; msg.getData().getCharSequence("price"+i)!=null ; i++ ) {

                        textViewF21.append("textViewF21 : "+msg.getData().getCharSequence("price"+i)+"\n");

                    }
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f2,container,false);

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

        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://android-test-db-ppking2897.c9users.io/DataBase/MenuQuery02.php");
                    HttpURLConnection conn =
                            (HttpURLConnection) url.openConnection();
                    conn.connect();
                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(conn.getInputStream()));
                    String line = reader.readLine();
                    reader.close();

                    parseJSON(line);
                }catch(Exception e){
                    Log.v("brad", e.toString());
                }
            }
        }.start();
        textViewF2 = (TextView)view.findViewById(R.id.f2Text);
        textViewF21 = (TextView)view.findViewById(R.id.f2Text02);
        return view;
    }



}
