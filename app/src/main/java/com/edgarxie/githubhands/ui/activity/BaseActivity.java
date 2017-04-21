package com.edgarxie.githubhands.ui.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.viewmodel.BaseViewModel;

/**
 * Created by edgar on 17-4-16.
 */

public abstract class BaseActivity<T extends BaseViewModel,V extends ViewDataBinding> extends AppCompatActivity {
    protected T mModel;
    protected V mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        mBinding= DataBindingUtil.setContentView(this,getLayoutId());
    }

    protected abstract int getLayoutId();
    protected abstract T getModel();
}
