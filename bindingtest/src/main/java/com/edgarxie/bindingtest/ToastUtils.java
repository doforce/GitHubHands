package com.edgarxie.bindingtest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by edgar on 17-4-10.
 */

public class ToastUtils {

    public static void show(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
