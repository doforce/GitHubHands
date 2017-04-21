package com.edgarxie.githubhands.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.FragmentCollectionsBinding;

/**
 * Created by edgar on 17-4-18.
 */

public class CollectionsFragment extends BaseFragment {
    FragmentCollectionsBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_collections,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
