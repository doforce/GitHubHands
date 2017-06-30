package com.edgarxie.githubhands.model;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.model.table.Collection;
import com.koma.greendao.gen.CollectionDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dofor on 2017/6/29.
 */

public class DbCollectionMode {

    public static List<String> getAllSelectRepo(){
        List<Collection> collections= App.mSession.getCollectionDao().queryBuilder()
                .where(CollectionDao.Properties.IsRepo.eq(true)).list();
        List<String> repos=new ArrayList<>();
        for (Collection collection1 : collections) {
            repos.add(collection1.getRepo());
        }
        return repos;
    }
}
