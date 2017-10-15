package com.edgarxie.githubhands;

import android.app.Application;

import com.edgarxie.githubhands.util.GreenDaoManager;
import com.koma.greendao.gen.DaoSession;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by edgar on 17-4-12.
 */

public class App extends Application {


    public static DaoSession mSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mSession=GreenDaoManager.getInstance(this).getNewSession();
        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
    }
}
