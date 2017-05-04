package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.LanguageModel;
import com.edgarxie.githubhands.ui.activity.CustomLanguageActivity;
import com.edgarxie.githubhands.ui.fragment.RepoFragment;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by edgar on 17-5-3.
 */

public class TrendingRepoPresenter extends BasePresenter<ITrendingRepoView> {
    private List<String> mLang=new ArrayList<>();
    private String[] mDefault_languages;

    public TrendingRepoPresenter(Context context){
        mContext=context;
        mDefault_languages=mContext.getResources()
                .getStringArray(R.array.default_languages);
    }
    public void goToCustomLanguage() {
        Intent intent=new Intent(mContext, CustomLanguageActivity.class);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("languages", (ArrayList<String>) mLang);
        mContext.startActivity(intent,bundle);
    }

    public void setLanguages() {
        if (LanguageModel.isTableEmpty()){
            mLang= Arrays.asList(mDefault_languages);
        }else {
            mLang=LanguageModel.getAll();
        }
        mView.addTabs(mLang);
    }

    public void onTabSelected(TabLayout.Tab tab) {
        FragmentTransaction transaction=mView.getChildFragmentMgr().beginTransaction();
        Fragment fragment=mView.getChildFragmentMgr().findFragmentByTag((String) tab.getText());
        if (fragment==null){
            fragment= RepoFragment.newInstance((String) tab.getText());
            transaction.add(R.id.repo_container,fragment, (String) tab.getText());
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
    }

    public void onTabUnSelected(TabLayout.Tab tab) {
        FragmentTransaction transaction=mView.getChildFragmentMgr().beginTransaction();
        Fragment fragment=mView.getChildFragmentMgr().findFragmentByTag((String) tab.getText());
        if (fragment!=null){
            transaction.hide(fragment);
            transaction.commit();
        }
    }
}
