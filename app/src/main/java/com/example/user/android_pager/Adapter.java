package com.example.user.android_pager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private int pic [] ;
    private String foodName [] ;
    public Adapter(Context context , int pic[] , String foodName []) {
        this.context = context;
        this.pic = pic;
        this.foodName=foodName;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.img.setImageResource(pic[position]);
        holder.textView.setText(foodName[position]);
    }

    @Override
    public int getItemCount() {
        return pic.length;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.itemImg);
            textView = (TextView) itemView.findViewById(R.id.itemText);
        }
    }
}
