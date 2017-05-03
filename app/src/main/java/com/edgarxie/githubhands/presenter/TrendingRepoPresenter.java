package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.adapter.MyFragmentPagerAdapter;
import com.edgarxie.githubhands.model.LanguageModel;
import com.edgarxie.githubhands.ui.activity.CustomLanguageActivity;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by edgar on 17-5-3.
 */

public class TrendingRepoPresenter extends BasePresenter<ITrendingRepoView> {
    private MyFragmentPagerAdapter mPagerAdapter;
    private ArrayList<String> mLang=new ArrayList<>();
    private String[] mDefault_languages;

    public TrendingRepoPresenter(Context context){
        mContext=context;
        mDefault_languages=mContext.getResources()
                .getStringArray(R.array.default_languages);
    }
    public void goToCustomLanguage() {
        Intent intent=new Intent(mContext, CustomLanguageActivity.class);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("languages",mLang);
        mContext.startActivity(intent,bundle);
    }

    public void setLanguages() {
        if (LanguageModel.isTableEmpty()){
            mLang.clear();
            Collections.addAll(mLang, mDefault_languages);
        }else {
            mLang=LanguageModel.getAll();
        }
        mPagerAdapter=new MyFragmentPagerAdapter(mView.getChildFragmentMgr(),mLang);
        mView.setPagerAdapter(mPagerAdapter);
    }
}
