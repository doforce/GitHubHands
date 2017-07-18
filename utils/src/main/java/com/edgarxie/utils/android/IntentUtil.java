package com.edgarxie.utils.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by edgar on 17-7-18.
 */

public class IntentUtil {

    public static void openInBrowser(Context context,String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
