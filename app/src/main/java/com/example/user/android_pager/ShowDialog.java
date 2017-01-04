package com.example.user.android_pager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;
import static com.example.user.android_pager.Adapter.type;
import static com.example.user.android_pager.Adapter.typeDelPosition;


/**
 * Created by BiancaEN on 2016/12/30.
 */

public class ShowDialog {
    private Context context;
    private FireBase fireBase;
    Spinner spinner;
    public  void showAddDialog(final Context context ){
        fireBase = new FireBase();
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context,THEME_HOLO_LIGHT);
        builder.setTitle("請輸入要增加的名稱,價格及路徑");




        View view = LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        final AppCompatEditText inputName = (AppCompatEditText)view.findViewById(R.id.editName);
        final AppCompatEditText inputPrice = (AppCompatEditText)view.findViewById(R.id.editPrice);
        final AppCompatEditText inputPath = (AppCompatEditText)view.findViewById(R.id.editPath);


        //下拉式選項
        spinner = (Spinner)view.findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> lunchList = ArrayAdapter.createFromResource(context,R.array.planets_array,android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(lunchList);





        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String selectItemText = spinner.getSelectedItem().toString();

                if (selectItemText.equals("拉麵")){
                    selectItemText = "noodle";
                }else if (selectItemText.equals("定食")){
                    selectItemText = "rice";
                }else if (selectItemText.equals("濃湯")){
                    selectItemText = "soup";
                }



                fireBase.WriteFoodBase(inputName.getText().toString(),
                        inputPrice.getText().toString(),
                        inputPath.getText().toString(),
                        selectItemText);



                //Log.v("ppking" , "text" +selectItemText);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDelItem(final Context context , String name , String path ,String price){
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context , THEME_HOLO_LIGHT);
        builder.setTitle("選擇要刪除的項目為");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_del_show,null);
        final TextView textFood = (TextView)view.findViewById(R.id.del_Name);
        //final TextView textPath = (TextView)view.findViewById(R.id.del_Path);
        final TextView textPrice = (TextView)view.findViewById(R.id.del_Price);

        textFood.setText(name);
        //textPath.setText(path);
        textPrice.setText(price + "元");


        builder.setView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fireBase = new FireBase();
                fireBase.DeleteData(typeDelPosition ,type);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
