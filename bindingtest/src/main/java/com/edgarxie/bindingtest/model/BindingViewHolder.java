package com.edgarxie.bindingtest.model;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by edgar on 17-4-6.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding=binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
