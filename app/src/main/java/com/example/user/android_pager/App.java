package com.example.user.android_pager;


import java.util.List;

public class App {

    private int mDrawable;
    private String mName;
    private String mUrl;
    private List<String> mPath;

    public App(String name, int drawable) {
        mName = name;
        mDrawable = drawable;

    }
    public App(String name , String url){
        mName = name;
        mUrl = url;
    }
    public App(String name, List<String> path){
        mName = name;
        mPath = path;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }

    public String getPicture(){
        return mUrl;
    }

}