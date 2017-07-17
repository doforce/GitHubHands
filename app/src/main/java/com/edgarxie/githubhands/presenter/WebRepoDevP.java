package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.edgarxie.githubhands.ui.interf.IWebRepoDevView;
import com.edgarxie.utils.android.SharePreUtil;

/**
 * Created by dofor on 2017/7/10.
 */

public class WebRepoDevP extends BasePresenter<IWebRepoDevView> {

    public WebRepoDevP(Context context){
        mContext=context;
    }

    public void deleteToken() {
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setMessage("Delete your personal access token?");
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Sure", (dialog, which) -> {
            SharePreUtil.clear(mContext);
            mView.finishAty();
        });
        builder.show();

    }
}
