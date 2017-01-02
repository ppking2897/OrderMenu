package com.example.user.android_pager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

/**
 * Created by BiancaEN on 2016/12/30.
 */

public class ShowDialog {
    private Context context;
    private FireBase fireBase;
    public  void showDialog(final Context context){
        fireBase = new FireBase();
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context,THEME_HOLO_LIGHT);
        builder.setTitle("請輸入要增加的名稱,價格及路徑");


        View view = LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        final AppCompatEditText inputName = (AppCompatEditText)view.findViewById(R.id.editName);
        final AppCompatEditText inputPrice = (AppCompatEditText)view.findViewById(R.id.editPrice);
        final AppCompatEditText inputPath = (AppCompatEditText)view.findViewById(R.id.editPath);
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

                fireBase.WriteFoodBase(inputName.getText().toString(),
                        inputPrice.getText().toString(),
                        inputPath.getText().toString());
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
