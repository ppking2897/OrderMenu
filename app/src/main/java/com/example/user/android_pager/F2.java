package com.example.user.android_pager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    private CharSequence [] foodNameEN = {"porknoodle" , "beefnoodle" , "chickennoodle" , "porkrice"
                                         ,"beefrice" , "chickennoodle" , "clamsoup" , "broth" , "liversoup"};
    private String [] foodNameCH = {"肉絲炒麵" , "牛肉炒麵" , "雞肉炒麵" , "豬肉炒飯" , "牛肉炒飯 "
            , "雞肉炒飯" , "蛤仔湯" , "隔間肉湯" , "豬肝湯" };

    private ArrayList<String> foodArray = new ArrayList<>();

    private RecyclerView recyclerView;
    private Adapter adapter;
    int img [] = {R.drawable.b0,R.drawable.b1,R.drawable.b0,R.drawable.b0
            ,R.drawable.b0,R.drawable.b0,R.drawable.b0,R.drawable.b0,R.drawable.b0};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHandler = new UIHandler();
        hashMap = new HashMap();
        foodArray.add(foodNameCH[0]);
        foodArray.add(foodNameCH[1]);
        foodArray.add(foodNameCH[2]);
        foodArray.add(foodNameCH[3]);
        foodArray.add(foodNameCH[4]);
        foodArray.add(foodNameCH[5]);
        foodArray.add(foodNameCH[6]);
        foodArray.add(foodNameCH[7]);
        foodArray.add(foodNameCH[8]);
    }

    private void parseJSON(String json){
        Message mesg = new Message();
        Message mesg2 = new Message();
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
                menuInfo.add(stringNo1);
                data.putCharSequence("menu"+i,menuInfo.get(i).toString());


                jsonArray1 = jsonArray.getJSONArray(i);
                obj = jsonArray1.getJSONObject(1);
                String stringNo2 = obj.getString("Price");
                priceInfo.add(stringNo2);
                data2.putCharSequence("price"+i,priceInfo.get(i).toString());

            }

            mesg.setData(data);
            mesg.what=0;
            uiHandler.sendMessage(mesg);

            mesg2.setData(data2);
            mesg2.what=1;
            uiHandler.sendMessage(mesg2);


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


                        hashMap.put(msg.getData().getCharSequence("menu"+i),foodNameCH[i]);
//                        Log.v("ppking" , "hashMap" + hashMap.toString() );
//                       textViewF2.append("textViewF2:" + hashMap.get(foodNameEN[0])+"\n");

                    }
                    break;
                case 1 :
                    for (int i = 0 ; msg.getData().getCharSequence("price"+i)!=null ; i++ ) {

//                        textViewF21.append("textViewF21 : "+msg.getData().getCharSequence("price"+i)+"\n");
//                        Log.v("ppking" , "textViewF21");
                    }
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f2, container, false);

        //下拉式更新
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
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
                } catch (Exception e) {
                    Log.v("brad", e.toString());
                }
            }
        }.start();

        //----------------RecyclerView------------------


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter = new Adapter(getContext(), img, foodArray));
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
//        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        //------------------END-------------------------
        return view;
    }
}
