package com.edgarxie.githubhands.model;

import android.util.Log;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.utils.java.ListUtil;
import com.koma.greendao.gen.TrendingDevBeanDao;
import com.koma.greendao.gen.TrendingRepoBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by edgar on 17-7-22.
 */

public class DbTrendingCacheModel {

    public static TrendingRepoBeanDao repoDao(){
        return App.mSession.getTrendingRepoBeanDao();
    }

    public static TrendingDevBeanDao devDao(){
        return App.mSession.getTrendingDevBeanDao();
    }

    /**
     *
     * @param language
     * @param frequency
     * @return nullable
     */
    public static List<TrendingRepoBean> getRepoCache(String language, String frequency){
        QueryBuilder<TrendingRepoBean> qb=repoDao().queryBuilder();
        qb.where(qb.and(TrendingRepoBeanDao.Properties.Lang.eq(language)
                ,TrendingRepoBeanDao.Properties.Frequency.eq(frequency)));
        List<TrendingRepoBean> been=qb.list();
        if (been.size()!=0){
            for (int i = 0; i < been.size(); i++) {
                been.get(i).setAvatars(ListUtil.stringToList(been.get(i).getAvatar()));
            }
        }
        return been;
    }

    public static void deleteRepoCache(List<TrendingRepoBean> repoBeen){
        if (repoBeen.size()!=0) {
            repoDao().deleteInTx(repoBeen);
            Log.e("delete repo","ok");
        }
    }

    public static void addRepoCache(List<TrendingRepoBean> repoBeen){
        for (int i = 0; i < repoBeen.size(); i++) {
            repoBeen.get(i).setAvatar(ListUtil.listToString(repoBeen.get(i).getAvatars()));
        }
        repoDao().insertInTx(repoBeen);
        Log.e("add repo","ok");
    }

    public static List<TrendingRepoBean> allRepoCache(){
        return repoDao().loadAll();
    }
}
