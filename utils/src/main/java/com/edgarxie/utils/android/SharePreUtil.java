package com.edgarxie.utils.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by edgarx on 16-9-6.
 */
public class SharePreUtil {

    public static void putString(Context context, String filename,String key, String val){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key,val);
        editor.apply();
    }

    public static void putBoolean(Context context,String filename, String key, boolean val){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(key,val);
        editor.apply();
    }

    public static boolean getBoolean(Context context,String filename,  String key){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }


    public static void putStringsSet(Context context, String filename, String key, Set<String> val){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putStringSet(key,val);
        editor.apply();
    }

    public static Set<String> getStringsSet(Context context, String filename, String key){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getStringSet(key,new HashSet<>());
    }

    public static void putInt(Context context, String filename, String key, int val){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt(key,val);
        editor.apply();
    }

    public static String getString(Context context,String filename,  String key){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getString(key,"");
    }

    public static int getInt(Context context,String filename, String key){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getInt(key,0);
    }

    public static void remove(Context context, String filename, String key){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context,String filename ){
        SharedPreferences preferences=context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();
    }
}
