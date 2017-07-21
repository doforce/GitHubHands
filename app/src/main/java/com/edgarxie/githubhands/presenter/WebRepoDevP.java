package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.ui.interf.IWebRepoDevView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.githubhands.util.NetConstant;
import com.edgarxie.utils.android.SharePreUtil;
import com.edgarxie.utils.android.ToastUtil;
import com.xxdong.ok.OkManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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

    public void authOperation(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu_star:
                auth(NetConstant.GITHUB_STARRED);
                break;
            case R.id.menu_watch:
                auth(NetConstant.GITHUB_WATCHED);
                break;
            case R.id.menu_follow:
                auth(NetConstant.GITHUB_FOLLOWING);
                break;
        }
    }

    private void auth(String path) {
        switch (path){
            case NetConstant.GITHUB_STARRED:
                if (mView.getMenuItemTitle(R.id.menu_star).equals(mView.getStr(R.string.star))){
                        putAuth(path,R.id.menu_star,R.string.un_star);
                }else {
                    deleteAuth(path,R.id.menu_star,R.string.star);
                }
                break;
            case NetConstant.GITHUB_WATCHED:
                if (mView.getMenuItemTitle(R.id.menu_watch).equals(mView.getStr(R.string.watch))){
                    putAuth(path,R.id.menu_watch,R.string.un_watch);
                }else {
                    deleteAuth(path,R.id.menu_watch,R.string.watch);
                }
                break;
            case NetConstant.GITHUB_FOLLOWING:
                if (mView.getMenuItemTitle(R.id.menu_follow).equals(mView.getStr(R.string.follow))){
                    putAuth(path,R.id.menu_follow,R.string.un_follow);
                }else {
                    deleteAuth(path,R.id.menu_follow,R.string.follow);
                }
                break;
        }
    }

    private void putAuth(String path, int menuId, int successTitleId){
        OkManager.getInstance()
                .putAsync(NetConstant.GITHUB_BASE_API + path + mView.getTitleText()
                        , NetConstant.ACCESS_TOKEN,SharePreUtil.getString(mContext
                                ,Constant.SHARE_PRE_TOKEN),authCallback(menuId,successTitleId));
    }

    private void deleteAuth(String path, int menuId, int successTitleId){
        try {
            OkManager.getInstance()
                    .deleteAsync(NetConstant.GITHUB_BASE_API + path + mView.getTitleText()
                            , NetConstant.ACCESS_TOKEN,SharePreUtil.getString(mContext
                                    ,Constant.SHARE_PRE_TOKEN),authCallback(menuId,successTitleId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Callback authCallback(int menuId, int successTitleId){
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.runUi(() -> ToastUtil.show(mContext,NetConstant.CONNECT_ERROR));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    mView.runUi(() -> ToastUtil.show(mContext,"Done"));
                    mView.runUi(() -> mView.setMenuItemTitle(menuId,successTitleId));
                }else {
                    mView.runUi(() -> {
                        try {
                            Log.e("401",response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        };
    }

    private void isStarredWatchedFollowing(String path){
        OkManager okManager=OkManager.getInstance();
        try {
            okManager.getAsync(NetConstant.GITHUB_BASE_API + path+mView.getTitleText(), NetConstant.ACCESS_TOKEN
                    , SharePreUtil.getString(mContext,Constant.SHARE_PRE_TOKEN), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mView.runUi(() -> ToastUtil.show(mContext,NetConstant.CONNECT_ERROR));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    boolean isSuccess=response.isSuccessful();
                    mView.runUi(() -> initItemTitle(path,isSuccess));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkStarredWatchedFollowing(boolean isRepo) {
        if (SharePreUtil.getString(mContext,Constant.SHARE_PRE_TOKEN)!=null){
            if (isRepo) {
                isStarredWatchedFollowing(NetConstant.GITHUB_STARRED);
                isStarredWatchedFollowing(NetConstant.GITHUB_WATCHED);
            }else {
                isStarredWatchedFollowing(NetConstant.GITHUB_FOLLOWING);
            }
        }
    }

    private void initItemTitle(String path, boolean isSuccess){
        if (isSuccess){
            switch (path){
                case NetConstant.GITHUB_STARRED:
                    mView.setMenuItemTitle(R.id.menu_star,R.string.un_star);
                    break;
                case NetConstant.GITHUB_WATCHED:
                    mView.setMenuItemTitle(R.id.menu_watch,R.string.un_watch);
                    break;
                case NetConstant.GITHUB_FOLLOWING:
                    mView.setMenuItemTitle(R.id.menu_follow,R.string.un_follow);
                    break;
            }
        }else {
            switch (path){
                case NetConstant.GITHUB_STARRED:
                    mView.setMenuItemTitle(R.id.menu_star,R.string.star);
                    break;
                case NetConstant.GITHUB_WATCHED:
                    mView.setMenuItemTitle(R.id.menu_watch,R.string.watch);
                    break;
                case NetConstant.GITHUB_FOLLOWING:
                    mView.setMenuItemTitle(R.id.menu_follow,R.string.follow);
                    break;
            }
        }
    }
}
