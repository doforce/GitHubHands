package com.edgarxie.githubhands.model;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.model.table.TrendingLang;
import com.koma.greendao.gen.DaoSession;
import com.koma.greendao.gen.TrendingLangDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by edgar on 17-5-3.
 */

public class DbLangModel {

    public static boolean isEmpty(){
        List<TrendingLang> languages= App.mSession.getTrendingLangDao().loadAll();
        return languages.size() <= 0;
    }

    public static ArrayList<String> getAllSelectedLang(){
        List<TrendingLang> languages=App.mSession.getTrendingLangDao()
                .queryBuilder().where(TrendingLangDao.Properties.Selected.eq(1)).list();
        ArrayList<String> result=new ArrayList<>();
        if (languages.size()==0){
            result.add("All");
        }else {
            for (TrendingLang language : languages) {
                result.add(language.getLang());
            }
        }
        return result;
    }

    public static List<TrendingLang> getAllLang(){
        return App.mSession.getTrendingLangDao().loadAll();
    }

    public static void update(TrendingLang lang){
        TrendingLangDao dao=App.mSession.getTrendingLangDao();
        dao.update(lang);
    }

    public static void initTable(){
        final String[] all=App.ALL_LANG;
        DaoSession session=App.mSession;
        session.runInTx(() -> {
            final ArrayList<String> defa= new ArrayList<>(Arrays.asList(App.DEFAULT_LANG));
            for (int i = 0; i < all.length; i++) {
                TrendingLang trendingLang=new TrendingLang();
                trendingLang.setId(Long.valueOf(i));
                trendingLang.setLang(all[i]);
                if (defa.contains(all[i])){
                    trendingLang.setSelected(1);
                }else {
                    trendingLang.setSelected(0);
                }
                session.insert(trendingLang);
            }
        });

    }

}
