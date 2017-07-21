package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edgarxie.githubhands.adapter.TrendingDevAdapter;
import com.edgarxie.githubhands.model.bean.JsonTrendingDevBean;
import com.edgarxie.githubhands.model.bean.TrendingDevBean;
import com.edgarxie.githubhands.ui.activity.WebRepoDevAty;
import com.edgarxie.githubhands.ui.interf.ITrendingDeveloperView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.githubhands.util.NetConstant;
import com.edgarxie.utils.android.ToastUtil;
import com.xxdong.ok.OkManagerBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edgar on 17-6-16.
 */

public class TrendingDevPresenter extends BasePresenter<ITrendingDeveloperView> {
    private Context mContext;
    private TrendingDevAdapter mAdapter;
    private List<TrendingDevBean> mData=new ArrayList<>();

    public TrendingDevPresenter(Context context) {
        mContext = context;
        mAdapter=new TrendingDevAdapter(mContext);
    }

    public void resumeRequest(String frequency){
        requestDeveloper(frequency);
        setAdapterListener();
    }

    public void requestDeveloper(String frequency) {
        mView.refreshingPost(() -> mView.setRefreshing(true));
        OkManagerBean<JsonTrendingDevBean> beanBuilder = new OkManagerBean<>();
        Map<String, String> par = new HashMap<>();
        par.put(NetConstant.SINCE, frequency);
        try {
            beanBuilder.getAsync(NetConstant.BASE_TRENDING_URL + NetConstant.DEVELOPER,
                    par, JsonTrendingDevBean.class, data -> {
                        int count = data.getCount();
                        if (count != 0) {
                            mData=data.getItems();
                            mAdapter.setList(mData);
                            mView.runOnUIThread(() -> mView.setRecyclerAdapter(mAdapter));
                        } else {
                            mView.runOnUIThread(() -> ToastUtil.show(mContext,data.getMsg()));
                        }
                        mView.refreshingPost(() -> mView.setRefreshing(false));
                    }, err -> {
                        mView.refreshingPost(() -> mView.setRefreshing(false));
                        mView.runOnUIThread(() -> ToastUtil.show(mContext,err));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAdapterListener(){
        mAdapter.setOnItemClickedListener((view, item) -> {
            TrendingDevBean bean= (TrendingDevBean) item;
            Intent intent=new Intent(mContext, WebRepoDevAty.class);
            Bundle bundle=new Bundle();
            bundle.putBoolean(Constant.BUNDLE_IS_REPO,false);
            bundle.putString(Constant.BUNDLE_WEB_URL,bean.getUserLink());
            bundle.putString(Constant.BUNDLE_DEVELOPER,bean.getUser());
            bundle.putString(Constant.BUNDLE_DEV_AVATAR,bean.getDeveloperAvatar());
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });
    }
}
