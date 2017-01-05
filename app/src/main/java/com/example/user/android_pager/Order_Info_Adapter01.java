package com.example.user.android_pager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2017/1/4.
 */

public class Order_Info_Adapter01 extends RecyclerView.Adapter<Order_Info_Adapter01.MyViewHolder> {
    private Context context;
    private List<String> foodname;
    private List<String> number;



    public Order_Info_Adapter01(Context context , List<String> foodname , List<String> number) {
        this.context = context;
        this.foodname = foodname;
        this.number = number;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_info,parent,false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.foodText.setText(foodname.get(position));
        holder.numberText.setText(number.get(position));

    }

    @Override
    public int getItemCount() {
        return foodname.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodText;
        TextView numberText;

        public MyViewHolder(View itemView) {
            super(itemView);
            foodText = (TextView) itemView.findViewById(R.id.orderItem);
            numberText = (TextView) itemView.findViewById(R.id.orderPrice);
        }
    }
}
