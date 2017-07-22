package com.edgarxie.githubhands.model;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.model.bean.TrendingDevBean;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.githubhands.model.table.DevCollection;
import com.edgarxie.githubhands.model.table.RepoCollection;
import com.koma.greendao.gen.DevCollectionDao;
import com.koma.greendao.gen.RepoCollectionDao;

import java.util.List;

/**
 * Created by dofor on 2017/6/29.
 */

public class DbCollectionMode {

    private static RepoCollectionDao repoDao(){
        return App.mSession.getRepoCollectionDao();
    }

    private static DevCollectionDao devDao(){
        return App.mSession.getDevCollectionDao();
    }

    public static boolean isRepoCollected(String repo){
        List<RepoCollection> repos=repoDao().queryBuilder()
                .where(RepoCollectionDao.Properties.Repo.eq(repo)).list();
        return repos.size()!=0;
    }

    public static boolean isDevCollected(String user){
        List<DevCollection> repos=devDao().queryBuilder()
                .where(DevCollectionDao.Properties.User.eq(user)).list();
        return repos.size()!=0;
    }

    public static void repoCollected(TrendingRepoBean item){
        repoCollected(item.getDesc(),item.getLang(),item.getRepo(),item.getRepoLink());
    }

    public static void repoCollected(String... item){
        RepoCollection repoCollection=new RepoCollection();
        repoCollection.setDesc(item[0]);
        repoCollection.setLang(item[1]);
        repoCollection.setRepo(item[2]);
        repoCollection.setRepoLink(item[3]);
        App.mSession.runInTx(() -> App.mSession.insert(repoCollection));
    }

    public static void devCollected(TrendingDevBean item){
        devCollected(item.getDeveloperAvatar(),item.getUser(),item.getUserLink());
    }

    public static void devCollected(String... item){
        DevCollection collection=new DevCollection();
        collection.setDeveloperAvatar(item[0]);
        collection.setUser(item[1]);
        collection.setUserLink(item[2]);
        App.mSession.runInTx(() -> App.mSession.insert(collection));
    }

    public static void repoUncollected(TrendingRepoBean item){
       repoUncollected(item.getRepo());
    }

    public static void repoUncollected(String repo){
        List<RepoCollection> repoCollections=repoDao().queryBuilder()
                .where(RepoCollectionDao.Properties.Repo.eq(repo)).list();
        if (repoCollections.size()!=0) {
            App.mSession.runInTx(() -> App.mSession.delete(repoCollections.get(0)));
        }
    }

    public static void devUncollected(TrendingDevBean item){
       devUncollected(item.getUser());
    }

    public static void devUncollected(String user){
        List<DevCollection> collections=devDao().queryBuilder()
                .where(DevCollectionDao.Properties.User.eq(user)).list();
        if (collections.size()!=0) {
            App.mSession.runInTx(() -> App.mSession.delete(collections.get(0)));
        }
    }

    public static List<RepoCollection> getAllRepoCollections(){
        return repoDao().loadAll();
    }

    public static List<DevCollection> getAllDevCollections(){
        return devDao().loadAll();
    }
}
