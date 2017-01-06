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
    private FirebaseDatabase databaseOrder = FirebaseDatabase.getInstance();

//    DatabaseReference myRefFood;
//    DatabaseReference myRefPrice;
//    DatabaseReference myRefPath;
    private DatabaseReference myRefTest;
    private DatabaseReference myRefWrite;
    private DatabaseReference myRefDelete;
    private DatabaseReference myRefOrder;

    List<String> foodNoodle =new ArrayList<>();
    List<String> foodRice =new ArrayList<>();
    List<String> foodSoup =new ArrayList<>();



    List<String> priceNoodle = new ArrayList<>();
    List<String> priceRice = new ArrayList<>();
    List<String> priceSoup = new ArrayList<>();

    List<String> pathNoodle = new ArrayList<>();
    List<String> pathRice = new ArrayList<>();
    List<String> pathSoup = new ArrayList<>();

    List<String> orderName01 = new ArrayList<>();
    List<String> orderNumber01 = new ArrayList<>();
    String orderPrice01;

    List<String> orderName02 = new ArrayList<>();
    List<String> orderNumber02 = new ArrayList<>();
    String orderPrice02;

    List<String> orderName03 = new ArrayList<>();
    List<String> orderNumber03 = new ArrayList<>();
    String orderPrice03;

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

    public void ReadOrderInfo01(final String name , final String seat) {
        myRefOrder = databaseOrder.getReference(name);
        myRefOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderName01.clear();
                orderNumber01.clear();
                orderPrice01 = "" ;
                for (int i = 0 ; i<dataSnapshot.child(seat).child("name").getChildrenCount() ; i++){
                    orderName01.add(dataSnapshot.child(seat).child("name").child("name"+i).getValue().toString());
                }

                for (int i = 0 ; i<dataSnapshot.child(seat).child("number").getChildrenCount() ; i++){
                    orderNumber01.add(dataSnapshot.child(seat).child("number").child("number"+i).getValue().toString());
                }

                for (int i = 0 ; i<dataSnapshot.child(seat).child("price").getChildrenCount() ; i++){
                    orderPrice01=dataSnapshot.child(seat).child("price").child("price"+i).getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void ReadOrderInfo02(final String name , final String seat) {
        myRefOrder = databaseOrder.getReference(name);
        myRefOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderName02.clear();
                orderNumber02.clear();
                orderPrice02 = "" ;
                for (int i = 0 ; i<dataSnapshot.child(seat).child("name").getChildrenCount() ; i++){
                    orderName02.add(dataSnapshot.child(seat).child("name").child("name"+i).getValue().toString());
                }

                for (int i = 0 ; i<dataSnapshot.child(seat).child("number").getChildrenCount() ; i++){
                    orderNumber02.add(dataSnapshot.child(seat).child("number").child("number"+i).getValue().toString());
                }

                for (int i = 0 ; i<dataSnapshot.child(seat).child("price").getChildrenCount() ; i++){
                    orderPrice02=dataSnapshot.child(seat).child("price").child("price"+i).getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void ReadOrderInfo03(final String name , final String seat) {
        myRefOrder = databaseOrder.getReference(name);
        myRefOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderName03.clear();
                orderNumber03.clear();
                orderPrice03 = "" ;
                for (int i = 0 ; i<dataSnapshot.child(seat).child("name").getChildrenCount() ; i++){
                    orderName03.add(dataSnapshot.child(seat).child("name").child("name"+i).getValue().toString());
                }

                for (int i = 0 ; i<dataSnapshot.child(seat).child("number").getChildrenCount() ; i++){
                    orderNumber03.add(dataSnapshot.child(seat).child("number").child("number"+i).getValue().toString());
                }

                for (int i = 0 ; i<dataSnapshot.child(seat).child("price").getChildrenCount() ; i++){
                    orderPrice03=dataSnapshot.child(seat).child("price").child("price"+i).getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void DeleteOrderInfo(String seatNumber){
        myRefDelete = databaseDelete.getReference("numberseat");
        myRefDelete.child(seatNumber).removeValue();
    }

}
