package com.edgarxie.githubhands;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.edgarxie.githubhands.model.table.DaoMaster;
import com.edgarxie.githubhands.model.table.DaoSession;


/**
 * Created by edgar on 17-4-12.
 */

public class App extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static App instances;
    @Override    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }
    public static App getInstances(){
        return instances;
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "github", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
