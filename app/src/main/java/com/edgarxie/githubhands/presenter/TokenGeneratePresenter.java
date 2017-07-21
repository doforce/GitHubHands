package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edgarxie.githubhands.model.bean.AuthUserBean;
import com.edgarxie.githubhands.ui.activity.WebAuthDevAty;
import com.edgarxie.githubhands.ui.interf.ITokenGenerateView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.githubhands.util.NetConstant;
import com.edgarxie.utils.android.SharePreUtil;
import com.edgarxie.utils.android.ToastUtil;
import com.xxdong.ok.OkManagerBean;

import java.io.IOException;

/**
 * Created by edgar on 17-7-16.
 */

public class TokenGeneratePresenter extends BasePresenter<ITokenGenerateView> {

    public TokenGeneratePresenter(Context context){
        mContext=context;
    }

    public void submit() {
        String token=mView.getTokenText();
        if ("".equals(token)){
            ToastUtil.show(mContext,"Please input your token.");
            return;
        }
        mView.setProgressVisibility(View.VISIBLE);
        OkManagerBean<AuthUserBean> ok=new OkManagerBean<>();
        try {
            ok.getAsync(NetConstant.GITHUB_BASE_API + "/user"
                    , NetConstant.ACCESS_TOKEN, token, AuthUserBean.class, data -> {
                       saveUserData(token,data);
                        mView.runUi(() -> verifyTokenDone(data));
            }, msg ->
                mView.runUi(() -> {
                    mView.setProgressVisibility(View.GONE);
                    ToastUtil.show(mContext,msg);
                }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserData(String token,AuthUserBean user){
        SharePreUtil.putString(mContext,Constant.SHARE_PRE_TOKEN,token);
        SharePreUtil.putString(mContext,Constant.SHARE_PRE_AVATAR,user.getAvatarUrl());
        SharePreUtil.putString(mContext,Constant.SHARE_PRE_EMAIL,user.getEmail());
        SharePreUtil.putString(mContext,Constant.SHARE_PRE_URL,user.getUrl());
        SharePreUtil.putString(mContext,Constant.SHARE_PRE_USERNAME,user.getUsername());
    }

    private void verifyTokenDone(AuthUserBean data){
        mView.setProgressVisibility(View.GONE);
        Intent intent=new Intent(mContext, WebAuthDevAty.class);
        Bundle bundle=new Bundle();
        bundle.putBoolean(Constant.BUNDLE_IS_REPO,false);
        bundle.putString(Constant.BUNDLE_DEVELOPER,data.getUsername());
        bundle.putString(Constant.BUNDLE_WEB_URL,data.getUrl());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
        mView.finishAty();
    }
}
