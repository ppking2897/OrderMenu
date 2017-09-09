package com.example.user.android_pager;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;


//測試
//測測
//快點測

//再測

public class MainActivity extends AppCompatActivity {
    FireBase fireBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(MainActivity.this)
                .add(R.string.titleA, F1.class)
                .add(R.string.titleC, F3.class)
                .add(R.string.titleB, F2.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        fireBase = new FireBase();
        fireBase.ReadFoodBase("foodinfo");
    }
}
