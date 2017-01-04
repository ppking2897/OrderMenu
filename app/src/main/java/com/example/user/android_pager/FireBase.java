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

    List<String> foodNoodle =new ArrayList<>();
    List<String> foodRice =new ArrayList<>();
    List<String> foodSoup =new ArrayList<>();



    List<String> priceNoodle = new ArrayList<>();
    List<String> priceRice = new ArrayList<>();
    List<String> priceSoup = new ArrayList<>();

    List<String> pathNoodle = new ArrayList<>();
    List<String> pathRice = new ArrayList<>();
    List<String> pathSoup = new ArrayList<>();

    static List<String> key ;
    static List<String> keyNoodle ;
    static List<String> keyRice ;
    static List<String> keySoup ;

    Map<String,Object> child01;
    Map<String,Object> child02;

    Map<String,Object> menuKey;

    private String DBName;
    private String DBPath;
    private String DBPrice;
    private String selectItem;

    private long count;
    private boolean isupdate = false;

    private int intkey;


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
                keyNoodle = new ArrayList<>();
                keyRice = new ArrayList<>();
                keySoup = new ArrayList<>();
                if(dataSnapshot.getChildrenCount()!=key.size()){
                    foodNoodle.clear();
                    foodRice.clear();
                    foodSoup.clear();

                    pathNoodle.clear();
                    pathRice.clear();
                    pathSoup.clear();

                    priceNoodle.clear();
                    priceRice.clear();
                    priceSoup.clear();
                }
                for (DataSnapshot type : dataSnapshot.getChildren()) {
                    key.add(type.getKey());

                }

                for(DataSnapshot childNoodle : dataSnapshot.child(key.get(0)).getChildren()){
                    keyNoodle.add(childNoodle.getKey());
//                    Log.v("ppking" , "keyNoodle : " +keyNoodle);
                }
                for(DataSnapshot childRice : dataSnapshot.child(key.get(1)).getChildren()){
                    keyRice.add(childRice.getKey());
//                    Log.v("ppking" , "childRice : " +keyRice);
                }
                for(DataSnapshot childSoup : dataSnapshot.child(key.get(2)).getChildren()){
                    keySoup.add(childSoup.getKey());
//                    Log.v("ppking" , "keyNoodle : " +keySoup);
                }

                //麵類 (key.get(0))
                for (int j=0 ; j < keyNoodle.size() ; j++) {
                    foodNoodle.add(dataSnapshot.child(key.get(0)).child(keyNoodle.get(j)).child("name").getValue().toString());
                    pathNoodle.add(dataSnapshot.child(key.get(0)).child(keyNoodle.get(j)).child("path").getValue().toString());
                    priceNoodle.add(dataSnapshot.child(key.get(0)).child(keyNoodle.get(j)).child("price").getValue().toString());
//                    Log.v("ppking", "noodlename : " + foodNoodle);
//                    Log.v("ppking", "noodlepath : " + pathNoodle);
//                    Log.v("ppking", "noodleprice : " + priceNoodle);
                }

                //飯類
                for (int j=0 ; j < keyRice.size() ; j++) {
                    foodRice.add(dataSnapshot.child(key.get(1)).child(keyRice.get(j)).child("name").getValue().toString());
                    pathRice.add(dataSnapshot.child(key.get(1)).child(keyRice.get(j)).child("path").getValue().toString());
                    priceRice.add(dataSnapshot.child(key.get(1)).child(keyRice.get(j)).child("price").getValue().toString());
//                    Log.v("ppking", "ricename : " + foodRice);
//                    Log.v("ppking", "ricepath : " + pathRice);
//                    Log.v("ppking", "ricerice : " + priceRice);
                }

                //濃湯類
                for (int j=0 ; j < keySoup.size() ; j++) {
                    foodSoup.add(dataSnapshot.child(key.get(2)).child(keySoup.get(j)).child("name").getValue().toString());
                    pathSoup.add(dataSnapshot.child(key.get(2)).child(keySoup.get(j)).child("path").getValue().toString());
                    priceSoup.add(dataSnapshot.child(key.get(2)).child(keySoup.get(j)).child("price").getValue().toString());
//                    Log.v("ppking", "soupname : " + foodSoup);
//                    Log.v("ppking", "souppath : " + pathSoup);
//                    Log.v("ppking", "soupprice : " + priceSoup);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void WriteFoodBase(String name , String path , String price , final String selectItem) {
        this.DBName = name;
        this.DBPath = path;
        this.DBPrice = price;
        this.selectItem = selectItem;



        myRefWrite = databaseWrite.getReference("foodinfo");
        child01 = new HashMap<>();
        child02 = new HashMap<>();
        isupdate = false;




        myRefWrite.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(!isupdate) {
                        if (selectItem.equals("noodle")) {
                            intkey = Integer.parseInt(keyNoodle.get(keyNoodle.size() - 1));
                        }else if (selectItem.equals("rice")) {
                            intkey = Integer.parseInt(keyRice.get(keyRice.size() - 1));
                        }else if(selectItem.equals("soup")) {
                            intkey = Integer.parseInt(keySoup.get(keySoup.size() - 1));
                        }

                        //count = dataSnapshot.getChildrenCount();
                        child02.put("name", DBName);
                        child02.put("path", DBPath);
                        child02.put("price", DBPrice);
                        child01.put(""+(intkey+1) , child02);
//                        Log.v("ppking" ,"key.size"+key.get(key.size()-1 ));
                        //myRefWrite.updateChildren(child01);
                        isupdate = true;
                        myRefWrite.child(selectItem).updateChildren(child01);

                    }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void DeleteData(int itemPosition ,String type){
        myRefDelete = databaseDelete.getReference("foodinfo/"+type);
        if(type == "noodle") {
            myRefDelete.child(keyNoodle.get(itemPosition)).removeValue();
        }else if(type == "rice"){
            myRefDelete.child(keyRice.get(itemPosition)).removeValue();
        }else if(type == "soup"){
            myRefDelete.child(keySoup.get(itemPosition)).removeValue();
        }
        //myRefDelete.child(key.get(itemPosition)).removeValue();
    }
}
