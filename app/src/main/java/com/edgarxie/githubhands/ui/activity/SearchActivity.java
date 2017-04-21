package com.edgarxie.githubhands.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.ActivitySearchBinding;
import com.edgarxie.githubhands.viewmodel.SearchViewModel;

/**
 * Created by edgar on 17-4-21.
 */

public class SearchActivity extends BaseActivity<SearchViewModel,ActivitySearchBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchViewModel getModel() {
        mModel=new SearchViewModel(mBinding);
        return mModel;
    }
}
