package com.edgarxie.githubhands;

import android.app.Application;

import com.edgarxie.githubhands.util.GreenDaoManager;
import com.koma.greendao.gen.DaoSession;


/**
 * Created by edgar on 17-4-12.
 */

public class App extends Application {


    public static DaoSession mSession;
    public static String[] DEFAULT_LANG;
    public static String[] ALL_LANG;

    @Override
    public void onCreate() {
        super.onCreate();
        mSession=GreenDaoManager.getInstance(this).getNewSession();
        DEFAULT_LANG=getResources().getStringArray(R.array.default_languages);
        ALL_LANG=getResources().getStringArray(R.array.all_language);
    }
}
