package com.example.user.android_pager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yalantis.phoenix.PullToRefreshView;



public class F2 extends Fragment {
    private PullToRefreshView mPullToRefreshView;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private FireBase fireBase;
    public boolean isDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireBase = new FireBase();
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

        //下拉式更新
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });



        //----------------RecyclerView------------------
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //----------------------------------------------
        //------將資料丟入調變器內去分配-------

        //fireBase.ReadBase("menu");
        fireBase.ReadFoodBase("menuinfo");

        //List<App> apps = getApps();
        adapter = new Adapter(getContext(),fireBase.path , fireBase.food , fireBase.price);


        recyclerView.setAdapter(adapter);
        //----------------------------------------
        //---------右上角ADD功能
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ShowDialog showDialog =new ShowDialog();
                showDialog.showAddDialog(getContext());

            }
        });
        view.findViewById(R.id.del).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"請選擇要刪除的項目",Toast.LENGTH_SHORT).show();
                isDelete =true;
            }
        });
        //------------------END-------------------------
        return view;
    }

}
