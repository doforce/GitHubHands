package com.edgarxie.githubhands.viewmodel;

import android.databinding.ViewDataBinding;

/**
 * Created by edgar on 17-4-21.
 */

public  abstract class BaseViewModel<V extends ViewDataBinding> {

    protected V mBinding;

    public BaseViewModel(V binding){
        mBinding=binding;
    }

}
