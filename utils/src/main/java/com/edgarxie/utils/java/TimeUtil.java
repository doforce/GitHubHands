package com.edgarxie.utils.java;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edgarx on 16-10-15.
 */

public class TimeUtil {

    public static String namePictureWithTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    public static String getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(date);
    }

    public static String formatDate(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
