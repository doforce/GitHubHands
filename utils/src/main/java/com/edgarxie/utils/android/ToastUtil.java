package com.edgarxie.utils.android;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by edgarx on 16-10-13.
 */

public class ToastUtil {
    public static void show(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context,String text,int time){
        Toast.makeText(context,text,time).show();
    }
}
