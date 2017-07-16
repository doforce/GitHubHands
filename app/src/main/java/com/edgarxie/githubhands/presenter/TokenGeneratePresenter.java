package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.ui.interf.ITokenGenerateView;

/**
 * Created by edgar on 17-7-16.
 */

public class TokenGeneratePresenter extends BasePresenter<ITokenGenerateView> {

    public TokenGeneratePresenter(Context context){
        mContext=context;
    }
}
