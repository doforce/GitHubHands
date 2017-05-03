package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.TrendingRepoPresenter;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;

/**
 * Created by edgar on 17-4-18.
 */

public class TrendingRepoFrag extends BaseMainFragment<TrendingRepoPresenter> implements ITrendingRepoView{
    private ViewPager mViewPager;
    private TabLayout mLanguageTab;
    private ImageView mSelect;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter=new TrendingRepoPresenter(getContext());
        initViews();
    }

    private void initViews(){
        mViewPager= (ViewPager) mView.findViewById(R.id.view_pager);
        mLanguageTab= (TabLayout) mView.findViewById(R.id.tab_language);
        mSelect= (ImageView) mView.findViewById(R.id.language_select);
        mSelect.setOnClickListener((v) -> mPresenter.goToCustomLanguage());
    }

    @Override
    public void setPagerAdapter(FragmentPagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mLanguageTab.setupWithViewPager(mViewPager);
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
