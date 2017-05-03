package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.ui.interf.ITopView;

/**
 * Created by edgar on 17-5-3.
 */

public abstract class BasePresenter<T extends ITopView> {
    protected Context mContext;
    protected T mView;


    public void attach(T view){
        mView=view;
    }

    public void detach(){
        mView=null;
    }
}
