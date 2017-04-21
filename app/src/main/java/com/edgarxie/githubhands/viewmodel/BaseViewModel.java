package com.edgarxie.githubhands.viewmodel;

import android.databinding.ViewDataBinding;

/**
 * Created by edgar on 17-4-21.
 */

public  abstract class BaseViewModel<T extends ViewDataBinding> {

    protected T mBinding;

    public BaseViewModel(T binding){
        mBinding=binding;
    }

}
