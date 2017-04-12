package com.edgarxie.githubhands.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by edgar on 17-4-12.
 */

public class SnackBarUtil {

    public static void show(View view,CharSequence text){
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();
    }

    public static void show(View view,CharSequence text,int duration){
        Snackbar.make(view,text,duration).show();
    }
}
