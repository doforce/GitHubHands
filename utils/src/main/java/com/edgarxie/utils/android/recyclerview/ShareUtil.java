package com.edgarxie.utils.android.recyclerview;

import android.content.Context;
import android.content.Intent;

/**
 * Created by dofor on 2017/10/12.
 */

public class ShareUtil {

    public static void shareText(Context context,String content){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "Share to..."));
    }
}
