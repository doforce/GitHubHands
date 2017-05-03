package com.edgarxie.githubhands.model;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.model.table.LanguageTable;
import com.edgarxie.githubhands.model.table.LanguageTableDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-5-3.
 */

public class LanguageModel {

    public static LanguageTableDao getLanguageDao(){
        return App.getInstances().getDaoSession().getLanguageTableDao();
    }

    public static boolean isTableEmpty(){
        QueryBuilder<LanguageTable> builder=getLanguageDao().queryBuilder();
        List<LanguageTable> languageTable=builder.where(LanguageTableDao.Properties.Position.eq(1)).list();
        if (languageTable.size()==0){
            return true;
        }
        return false;
    }

    public static ArrayList<String> getAll(){
        QueryBuilder<LanguageTable> builder=getLanguageDao().queryBuilder();
        List<LanguageTable> languageTables=builder
                .orderAsc(LanguageTableDao.Properties.Position).list();
        ArrayList<String> result=new ArrayList<>();
        languageTables.forEach(languageTable -> result.add(languageTable.getLanguage()));
        return result;
    }
}
