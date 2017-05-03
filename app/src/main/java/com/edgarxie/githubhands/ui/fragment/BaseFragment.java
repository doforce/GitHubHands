package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgarxie.githubhands.presenter.BasePresenter;

/**
 * Created by edgar on 17-4-16.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment{
    protected T mPresenter;
    protected View mView;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutId(),container,false);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        attachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        mView=null;
    }

    protected abstract int getLayoutId();
    protected abstract void attachView();
}
