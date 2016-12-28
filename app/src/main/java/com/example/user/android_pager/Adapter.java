package com.example.user.android_pager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private int pic [] ;
    private ArrayList<String> foodArray  ;

    public Adapter(Context context , int pic[] ,ArrayList<String> foodArray )  {
        this.context = context;
        this.pic = pic;
        this.foodArray=foodArray;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.img.setImageResource(pic[position]);
        holder.textView.setText(foodArray.get(position));
    }

    @Override
    public int getItemCount() {
        return pic.length;
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView textView;


        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.itemImg);
            img.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.itemText);
        }

        @Override
        public void onClick(View view) {
            //圖片對應位置
            int position = getAdapterPosition();
            Log.v("ppking" , "posion:" +position);
        }
    }
}
