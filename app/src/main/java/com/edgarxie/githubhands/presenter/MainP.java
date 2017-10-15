package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.DbLangModel;
import com.edgarxie.githubhands.ui.activity.CustomLangAty;
import com.edgarxie.githubhands.ui.activity.TokenGenerateActivity;
import com.edgarxie.githubhands.ui.activity.WebAuthDevAty;
import com.edgarxie.githubhands.ui.interf.IMainView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.utils.android.SharePreUtil;

import java.util.ArrayList;
import java.util.Arrays;

import static com.edgarxie.githubhands.model.DbLangModel.getAllSelectedLang;

/**
 * Created by edgar on 17-5-3.
 */

public class MainP extends BasePresenter<IMainView> {
    private ArrayList<String> mLang=new ArrayList<>();
    public  String[] DEFAULT_LANG;
    public  String[] ALL_LANG;

    public MainP(Context context){
        mContext=context;
        DEFAULT_LANG=context.getResources().getStringArray(R.array.default_languages);
        ALL_LANG=context.getResources().getStringArray(R.array.all_language);
    }

    public void goToCustomLanguage() {
        Intent intent=new Intent(mContext, CustomLangAty.class);
        mContext.startActivity(intent);
    }

    private void goToTokenGenerate(){
        Intent intent=new Intent(mContext, TokenGenerateActivity.class);
        mContext.startActivity(intent);
    }

    private void goToUserWebUrl(String username){
        Intent intent=new Intent(mContext, WebAuthDevAty.class);
        Bundle bundle=new Bundle();
        bundle.putBoolean(Constant.BUNDLE_IS_REPO,false);
        bundle.putString(Constant.BUNDLE_DEVELOPER,username);
        bundle.putString(Constant.BUNDLE_WEB_URL,SharePreUtil.getString(mContext,Constant.SHARE_PRE_URL));
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }


    public void authEvent(){
        String username=SharePreUtil.getString(mContext,Constant.SHARE_PRE_USERNAME);
        if (username!=null){
            goToUserWebUrl(username);
        }else {
            goToTokenGenerate();
        }
    }

    public void setLanguages() {
        if (DbLangModel.isEmpty()){
            DbLangModel.initTable(ALL_LANG,DEFAULT_LANG);
            mLang.clear();
            mLang=new ArrayList<>(Arrays.asList(DEFAULT_LANG));
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

    public void verifyUser() {
        String username= SharePreUtil.getString(mContext, Constant.SHARE_PRE_USERNAME);
        String avatar= SharePreUtil.getString(mContext, Constant.SHARE_PRE_AVATAR);
        if (username!=null && avatar!=null){
            mView.setUsernameText(username);
            mView.setUserAvatar(avatar);
        }else {
            mView.setDefaultUserAvatar();
            mView.setDefaultUsernameText();
        }
    }
}
