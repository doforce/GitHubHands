package com.edgarxie.utils.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by edgarx on 16-9-6.
 */
public class SharePreUtil {
    public static final String ASSN_NAME="assn_name";
    public static final String SELECTED_POS="selected_pos";
    public static final String FILE_NAME="file_name";

    public static void putString(Context context, String key, String val){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,val);
        editor.commit();
    }

    public static void putStringsSet(Context context, String key, Set<String> val){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putStringSet(key,val);
        editor.commit();
    }

    public static void putInt(Context context, String key, int val){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt(key,val);
        editor.commit();
    }

    public static String getString(Context context, String key){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    public static int getInt(Context context,String key){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return preferences.getInt(key,0);
    }

    public static void remove(Context context, String key){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context){
        SharedPreferences preferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();
    }
}
