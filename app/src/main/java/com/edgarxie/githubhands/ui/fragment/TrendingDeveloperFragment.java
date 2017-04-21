package com.edgarxie.githubhands.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.FragmentTrendingDeveloperBinding;

/**
 * Created by edgar on 17-4-18.
 */

public class TrendingDeveloperFragment extends BaseFragment {
    FragmentTrendingDeveloperBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_trending_developer,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
