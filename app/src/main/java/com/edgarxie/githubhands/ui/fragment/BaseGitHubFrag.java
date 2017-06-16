package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.BasePresenter;
import com.edgarxie.githubhands.ui.activity.MainActivity;
import com.edgarxie.githubhands.util.MainConstants;
import com.edgarxie.githubhands.util.NetConstants;

/**
 * Created by edgar on 17-4-24.
 */

public abstract class BaseGitHubFrag<T extends BasePresenter>
        extends BaseFragment<T> implements MainActivity.OnMenuClick{
    protected T mPresenter;
    protected MainActivity mActivity;
    protected String mFrequency= NetConstants.DAILY;
    protected int mWhatCollection= MainConstants.Collection.REPO;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity= (MainActivity) getActivity();
        mActivity.setOnMenuClick(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity=null;
    }

    @Override
    public void onMenuClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_trending_daily:
                mFrequency=NetConstants.DAILY;
                break;
            case R.id.action_trending_weekly:
                mFrequency=NetConstants.WEEKLY;
                break;
            case R.id.action_trending_monthly:
                mFrequency=NetConstants.MONTHLY;
                break;
            case R.id.action_collections_repo:
                mWhatCollection=MainConstants.Collection.REPO;
                break;
            case R.id.action_collections_developer:
                mWhatCollection=MainConstants.Collection.DEVE;
                break;
        }
    }
}
