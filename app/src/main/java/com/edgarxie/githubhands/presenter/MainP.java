package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.model.DbLangModel;
import com.edgarxie.githubhands.ui.activity.CustomLangAty;
import com.edgarxie.githubhands.ui.interf.IMainView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.edgarxie.githubhands.model.DbLangModel.getAllSelectedLang;

/**
 * Created by edgar on 17-5-3.
 */

public class MainP extends BasePresenter<IMainView> {
    private ArrayList<String> mLang=new ArrayList<>();

    public MainP(Context context){
        mContext=context;
    }

    public void goToCustomLanguage() {
        Intent intent=new Intent(mContext, CustomLangAty.class);
        mContext.startActivity(intent);
    }

    public void setLanguages() {
        if (DbLangModel.isEmpty()){
            DbLangModel.initTable();
            mLang.clear();
            mLang=new ArrayList<>(Arrays.asList(App.DEFAULT_LANG));
        }else {
            mLang= getAllSelectedLang();
        }
        mView.addTabs(mLang);
    }

    public String getDefaultTabText() {
        String result="All";
        if (!DbLangModel.isEmpty()){
            result=getAllSelectedLang().get(0);
        }
        return result;
    }
}
