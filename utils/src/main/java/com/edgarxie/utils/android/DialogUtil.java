package com.edgarxie.utils.android;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by dofor on 2017/9/8.
 */

public class DialogUtil {

    public static void textDialog(Context context,String title,String content){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("Sure", (dialog, which) -> {})
                .setNegativeButton("Cancel", (dialog, which) -> {})
                .show();
    }
}
