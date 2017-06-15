package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.edgarxie.githubhands.App;
import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.DbLangModel;
import com.edgarxie.githubhands.ui.activity.CustomLangAty;
import com.edgarxie.githubhands.ui.fragment.RepoFragment;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by edgar on 17-5-3.
 */

public class TrendingRepoP extends BasePresenter<ITrendingRepoView> {
    private ArrayList<String> mLang=new ArrayList<>();

    public TrendingRepoP(Context context){
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
            mLang= DbLangModel.getAllSelectedLang();
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
