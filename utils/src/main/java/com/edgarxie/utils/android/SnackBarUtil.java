package com.edgarxie.utils.android;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by edgar on 17-4-12.
 */

public class SnackBarUtil {

    public static void show(View view,CharSequence text){
        show(view,text,Snackbar.LENGTH_SHORT);
    }

    public static void show(View view,CharSequence text,int duration){
        Snackbar.make(view,text,duration).show();
    }
}
