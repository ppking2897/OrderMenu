package com.example.user.android_pager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.user.android_pager.Adapter.type;
import static com.example.user.android_pager.Adapter.typeDelPosition;


public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder>{
    private Context context;
    private List<App> apps  ;
    private View view, view2;
    private Myhandler myhandler;
    private List<String> foodName;
    private List<String> price;
    private List<String> path;
    private final View.OnClickListener onClickListener2  = new MyOnClickListener2();
    private RecyclerView recyclerView;
    private int itemPosition;

    private FireBase fireBase;



    public Adapter2(Context context , List<String> path , List<String> foodName , List<String> price )  {
        this.context = context;
        this.foodName=foodName;
        this.price = price;
        this.path = path;
        myhandler = new Myhandler();
        fireBase = new FireBase();
        startUpdateTimer();


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false));

        //recyclerview內的layout指定
        view = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
        view.setOnClickListener(onClickListener2);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //resize 重新改變圖片大小
        if(!foodName.isEmpty()&&!price.isEmpty()&&!path.isEmpty()) {
            Picasso.with(context).load(path.get(position)).resize(300, 300).into(holder.img);
            holder.textView.setText("名稱 : " + foodName.get(position));
            holder.textPrice.setText("價格 : " + price.get(position) + "元");

        }

    }


    @Override
    public int getItemCount() {
        return foodName.size();

    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView textView , textPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.itemImg);
            textView = (TextView) itemView.findViewById(R.id.itemText);
            textPrice = (TextView) itemView.findViewById(R.id.itemPrice);
        }
    }
    public void add(String text, int position) {
        //apps.add(new App(text,R.drawable.b0));
        foodName.add(position,text);
        notifyItemInserted(position);
    }

    //---------------自動notifydatachange 更新------------------
    private void startUpdateTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                myhandler.sendEmptyMessage(0);
            }
        }, 0, 3000);
    }
    public class Myhandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            notifyDataSetChanged();
//            notifyItemRangeChanged(0 , foodName.size());
//            notifyItemChanged(0);
//            if(fireBase.isChange) {
//                notifyItemRangeChanged(0, path.size());
//                notifyItemRangeChanged(0, price.size());
//                notifyItemRangeChanged(0, foodName.size());
//            }
//            Log.v("ppking", "change");
        }
    }

    //--------------------------------------------------------

    public class MyOnClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (F2.isDelete) {
                recyclerView = new RecyclerView(context);
                itemPosition = recyclerView.getChildAdapterPosition(view);
                String foodItem = foodName.get(itemPosition);
                String pathItem = path.get(itemPosition);
                String priceItem = price.get(itemPosition);
                ShowDialog showDialog = new ShowDialog();
                showDialog.showDelItem(context, foodItem, pathItem, priceItem);
                F2.isDelete = false;
                type = "soup";
                typeDelPosition = itemPosition;
                Toast.makeText(context,foodItem,Toast.LENGTH_LONG).show();
            }

        }

    }
}

