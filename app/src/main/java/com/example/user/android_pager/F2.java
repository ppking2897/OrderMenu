package com.example.user.android_pager;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class F2 extends Fragment {
    private PullToRefreshView mPullToRefreshView;
    private TextView textViewF2;
    private UIHandler uiHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHandler = new UIHandler();

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
            Log.v("ppking", "ErrorF2 : " + e.toString());
        }
    }

    private class UIHandler extends android.os.Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0 :
                    textViewF2.setText("Accound : "+msg.getData().getCharSequence("data0")+"\n");
                    textViewF2.append("SeatIdNumber : "+msg.getData().getCharSequence("data1")+"\n");
                    textViewF2.append("Checkout : $"+msg.getData().getCharSequence("data2")+"\n");
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
                    //?後面為POST過去要查詢的資料名稱
                    MultipartUtility mu = new MultipartUtility("https://android-test-db-ppking2897.c9users.io/DataBase/AccountQuery02.php?accountId=ppking0802", "UTF-8");
                    List<String> ret = mu.finish();

                    parseJSON(ret.toString());
                    Log.v("ppking", ret.toString());



                } catch (Exception e) {
                    Log.v("ppking", "DB Error:" + e.toString());
                }
            }
        }.start();
        textViewF2 = (TextView)view.findViewById(R.id.f2Text);

        return view;
    }



}
