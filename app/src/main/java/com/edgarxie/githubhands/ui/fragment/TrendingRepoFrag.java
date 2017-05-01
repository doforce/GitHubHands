package com.edgarxie.githubhands.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.adapter.MyFragmentPagerAdapter;
import com.edgarxie.githubhands.databinding.FragmentTrendingRepoBinding;

/**
 * Created by edgar on 17-4-18.
 */

public class TrendingRepoFrag extends BaseMainFragment {
    private FragmentTrendingRepoBinding mBinding;
    private SparseArray<String> mLang=new SparseArray<>();
    private MyFragmentPagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_trending_repo,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setListener();
    }

    private void initViews(){
        mLang.put(0,"Java");
        mLang.put(1,"Python");
        mLang.put(2,"JavaScript");
        mLang.put(3,"Php");
        mLang.put(4,"c");
        mLang.put(5,"html");
        mLang.put(6,"css");
        mPagerAdapter=new MyFragmentPagerAdapter(getChildFragmentManager(),mLang);
        mBinding.viewPager.setAdapter(mPagerAdapter);
        mBinding.tabLanguage.setupWithViewPager(mBinding.viewPager);
    }

    private void setListener(){
//        mBinding.tabLanguage.getTabCount()
    }
}
