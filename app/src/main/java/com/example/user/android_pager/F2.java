package com.example.user.android_pager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yalantis.phoenix.PullToRefreshView;

import java.util.Timer;
import java.util.TimerTask;


public class F2 extends Fragment  {

    private RecyclerView recyclerViewNoodle;
    private RecyclerView recyclerViewRice;
    private RecyclerView recyclerViewSoup;

    private Adapter adapter;
    private Adapter1 adapter1;
    private Adapter2 adapter2;
    private FireBase fireBase;
    public static boolean isDelete;
    private Myhandler myhandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireBase = new FireBase();
        myhandler = new Myhandler();
        startUpdateTimer();

    }


    //-----定義一個回傳list的方法  並定義在別處定義一個App的型別類別
//    private List<App> getApps(){
//        List<App> apps = new ArrayList<>();
//        apps.add(new App("豚骨拉麵","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15781843_1462626693747912_723476130_n.png?alt=media&token=dc0eba5f-c9db-4828-8a47-c17ea67cf43f"));
//        apps.add(new App("醬油拉麵","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15731701_1462626697081245_596702805_n.png?alt=media&token=6cb992a7-f8ed-45e9-ab00-9aff281bc7af"));
//        apps.add(new App("味噌拉麵","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15731240_1462626700414578_395190317_n.png?alt=media&token=68d0c4ca-bc69-4476-a9c0-7934b9c35a19"));
//        apps.add(new App("豬排定食","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15731097_1462626633747918_1420253165_n.jpg?alt=media&token=d2a88e8d-91a3-40a4-9290-37f241997e2e"));
//        apps.add(new App("牛小排定食","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15731174_1462626640414584_479793837_n.jpg?alt=media&token=bb2c8ad4-8e3f-4811-b37a-d6564a7a46c1"));
//        apps.add(new App("秋刀魚定食","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15731581_1462626637081251_455999719_n.jpg?alt=media&token=b9da6a0a-ba2c-44c0-bcb6-38bbf9faa940"));
//        apps.add(new App("玉米濃湯","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15723933_1462626587081256_1565692789_n.png?alt=media&token=5f0e8f15-2575-4a3d-bfa0-e09e27435c6a"));
//        apps.add(new App("番茄濃湯","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15749560_1462626593747922_476309376_n.png?alt=media&token=584bd9f6-8ab6-41a3-8601-8c58d98b81f5"));
//        apps.add(new App("南瓜濃湯","https://firebasestorage.googleapis.com/v0/b/menumaneger.appspot.com/o/15750291_1462626597081255_486750815_n.png?alt=media&token=3c4dcc88-185a-4736-b129-5204c0e1f39f"));
//        return apps;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f2, container, false);


        //----------------RecyclerView------------------
//        recyclerViewTitle = (RecyclerView) view.findViewById(R.id.recyclerView_Title);
//        recyclerViewTitle.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewNoodle = (RecyclerView) view.findViewById(R.id.recyclerView_Noodle);
        recyclerViewNoodle.setLayoutManager(new GridLayoutManager(getContext(), 3));


        recyclerViewRice = (RecyclerView) view.findViewById(R.id.recyclerView_Rice);
        recyclerViewRice.setLayoutManager(new GridLayoutManager(getContext(), 3));
//
        recyclerViewSoup = (RecyclerView) view.findViewById(R.id.recyclerView_Soup);
        recyclerViewSoup.setLayoutManager(new GridLayoutManager(getContext(), 3));

        //----------------------------------------------
        //------將資料丟入調變器內去分配-------

        //fireBase.ReadBase("menu");
        fireBase.ReadFoodBase("foodinfo");

        adapter = new Adapter(getContext(), fireBase.pathNoodle, fireBase.foodNoodle, fireBase.priceNoodle);
        recyclerViewNoodle.setAdapter(adapter);

        adapter1 = new Adapter1(getContext(), fireBase.pathRice, fireBase.foodRice, fireBase.priceRice);
        recyclerViewRice.setAdapter(adapter1);
//
        adapter2 = new Adapter2(getContext(), fireBase.pathSoup, fireBase.foodSoup, fireBase.priceSoup);
        recyclerViewSoup.setAdapter(adapter2);
        //----------------------------------------
        //---------右上角ADD功能
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog showDialog = new ShowDialog();
                showDialog.showAddDialog(getContext());

            }
        });
        view.findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "請選擇要刪除的項目", Toast.LENGTH_SHORT).show();
                isDelete = true;
                //Log.v("ppking" , "F2_isdelete : " + isDelete);
            }
        });
        //------------------END-------------------------
        return view;
    }

    private void startUpdateTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                myhandler.sendEmptyMessage(0);
            }
        }, 0, 1000);
    }

    public class Myhandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        }
    }
}
