package com.example.user.android_pager;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FireBase {
//    FirebaseDatabase databaseFood = FirebaseDatabase.getInstance();
//    FirebaseDatabase databasePrice = FirebaseDatabase.getInstance();
//    FirebaseDatabase databasePath = FirebaseDatabase.getInstance();
    private FirebaseDatabase databaseTest = FirebaseDatabase.getInstance();
    private FirebaseDatabase databaseWrite = FirebaseDatabase.getInstance();
    private FirebaseDatabase databaseDelete = FirebaseDatabase.getInstance();

//    DatabaseReference myRefFood;
//    DatabaseReference myRefPrice;
//    DatabaseReference myRefPath;
    private DatabaseReference myRefTest;
    private DatabaseReference myRefWrite;
    private DatabaseReference myRefDelete;

    List<String> food =new ArrayList<>();
    List<String> price = new ArrayList<>();
    List<String> path = new ArrayList<>();
    static List<String> key ;

    Map<String,Object> child01;
    Map<String,Object> child02;

    private String DBName;
    private String DBPath;
    private String DBPrice;

    private long count;
    private boolean isupdate = false;


//    public void ReadBase(final String name){
//        myRefFood = databaseFood.getReference(name);
//        food.clear();
//        myRefFood.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 0;
//                //判斷是不是有多出新的一筆資料，如果有全部清掉再丟資料進去
//                if (dataSnapshot.getChildrenCount()!=food.size()){
//                    food.clear();
//                }
//                //for each 到list food 裡面呈現字體
//                for(DataSnapshot nameFood :dataSnapshot.getChildren()){
////                    Log.v("ppking", "dataSnapshot" + dataSnapshot.getChildrenCount());
//
//                    food.add(i,nameFood.getValue(String.class));
//                    i++;
//                }
//                //從firebase 讀取完名稱之後  再讀取價格
//                ReadBasePrice("price");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("ppking", "Failed to read value.", databaseError.toException());
//            }
//        });
//    }
//
//    public void ReadBasePrice(final String name){
//        myRefPrice = databasePrice.getReference(name);
//        price.clear();
//        myRefPrice.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 0;
//                //判斷是不是有多出新的一筆資料，如果有全部清掉再丟資料進去
//                if (dataSnapshot.getChildrenCount()!=price.size()){
//                    price.clear();
//                }
//                for(DataSnapshot namePrice :dataSnapshot.getChildren()){
////                    Log.v("ppking", "dataSnapshot" + dataSnapshot.getChildrenCount());
//
//                    price.add(i,namePrice.getValue(String.class));
////                    Log.v("ppking" , "price : " + price );
//                    i++;
//                }
//                ReadPicturePath("picturepath");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("ppking", "Failed to read Price.", databaseError.toException());
//            }
//        });
//
//    }
//
//    public void ReadPicturePath(final String name){
//        myRefPath = databasePath.getReference(name);
//        path.clear();
//        myRefPath.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int i = 0;
//                //判斷是不是有多出新的一筆資料，如果有全部清掉再丟資料進去
//                if (dataSnapshot.getChildrenCount()!=path.size()){
//                    path.clear();
//                }
//                for(DataSnapshot namePrice :dataSnapshot.getChildren()){
//                    path.add(i,namePrice.getValue(String.class));
////                    Log.v("ppking" , "FireBase path" + path);
//                    i++;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("ppking", "Failed to read Path.", databaseError.toException());
//            }
//        });
//    }

    public void ReadFoodBase(final String name){
        myRefTest =databaseTest.getReference(name);
        myRefTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                key = new ArrayList<>();
                if(dataSnapshot.getChildrenCount()!=key.size()){
                    food.clear();
                    path.clear();
                    price.clear();
                }
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    key.add(child.getKey());
                    Log.v("ppking" , "key" +key);
                }
                for (int i = 0; i < key.size(); i++) {
                    food.add(dataSnapshot.child(key.get(i)).child("name").getValue().toString());
                    path.add(dataSnapshot.child(key.get(i)).child("path").getValue().toString());
                    price.add(dataSnapshot.child(key.get(i)).child("price").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void WriteFoodBase(String name , String path , String price) {
        this.DBName = name;
        this.DBPath = path;
        this.DBPrice = price;
        myRefWrite = databaseWrite.getReference("menuinfo");
        child01 = new HashMap<>();
        child02 = new HashMap<>();
        isupdate = false;


        myRefWrite.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(!isupdate) {
                        int i = Integer.parseInt(key.get(key.size()-1));
                        count = dataSnapshot.getChildrenCount();
                        child02.put("name", DBName);
                        child02.put("path", DBPath);
                        child02.put("price", DBPrice);
                        child01.put(""+(i+1) , child02);
                        Log.v("ppking" ,"key.size"+key.get(key.size()-1 ));
                        myRefWrite.updateChildren(child01);
                        isupdate = true;
                    }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void DeleteData(int itemPosition){
        myRefDelete = databaseDelete.getReference("menuinfo");
        Log.v("ppking" , "Delete"+myRefDelete.child(""+itemPosition));
        Log.v("ppking", "delete key" + key.get(itemPosition));
        myRefDelete.child(key.get(itemPosition)).removeValue();
    }
}
