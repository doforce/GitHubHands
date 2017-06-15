package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.TrendingRepoP;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;

import java.util.List;

/**
 * Created by edgar on 17-4-18.
 */

public class TrendingRepoFrag extends BaseMainFrag<TrendingRepoP> implements ITrendingRepoView{
    private TabLayout mLanguageTab;
    private ImageView mSelect;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter=new TrendingRepoP(getContext());
        initViews();
    }

    private void initViews(){
        mLanguageTab= (TabLayout) mView.findViewById(R.id.tab_language);
        mSelect= (ImageView) mView.findViewById(R.id.language_select);
        mSelect.setOnClickListener((v) -> mPresenter.goToCustomLanguage());
        mLanguageTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPresenter.onTabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mPresenter.onTabUnSelected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public void addTabs(List<String> tabs) {
        mLanguageTab.removeAllTabs();
        for (int i = 0; i < tabs.size(); i++) {
            mLanguageTab.addTab(mLanguageTab.newTab().setText(tabs.get(i)));
        }
    }

    @Override
    public FragmentManager getChildFragmentMgr() {
        return getChildFragmentManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setLanguages();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trending_repo;
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
