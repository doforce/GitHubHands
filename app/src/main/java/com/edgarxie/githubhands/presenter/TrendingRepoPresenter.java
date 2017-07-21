package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.model.bean.JsonTrendingRepoBean;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.githubhands.ui.activity.WebRepoDevAty;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;
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

public class TrendingRepoPresenter extends BasePresenter<ITrendingRepoView> {
    private TrendingRepoAdapter mAdapter;
    private Context mContext;
    private List<TrendingRepoBean> mRepos = new ArrayList<>();

    public TrendingRepoPresenter(Context context) {
        mContext = context;
        mAdapter = new TrendingRepoAdapter(mContext);
    }

    public void resumeRequest(String language,String frequency){
        requestRepo(language,frequency);
        setAdapterListener();
    }

    public void requestRepo(String language, String frequency) {
        mView.refreshingPost(() -> mView.setRefreshing(true));
        OkManagerBean<JsonTrendingRepoBean> beanBuilder = new OkManagerBean<>();
        Map<String, String> par = new HashMap<>();
        par.put(NetConstant.SINCE, frequency);
        try {
            beanBuilder.getAsync(NetConstant.BASE_TRENDING_URL + NetConstant.REPO + formatLanguage(language)
                    , par, JsonTrendingRepoBean.class, data -> {
                        int count = data.getCount();
                        if (count != 0) {
                            mRepos = setCollection(data.getItems());
                            mAdapter.setList(mRepos);
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

    private List<TrendingRepoBean> setCollection(List<TrendingRepoBean> data){
        //// TODO: 2017/6/29 从收藏的数据表里读取数据
//        List<String> collectedRepos= DbCollectionMode.getAllSelectRepo();
//        List<TrendingRepoBean> result=data;
//        for (TrendingRepoBean bean : data) {
//            if (collectedRepos.contains(bean.getRepo())){
//                bean.setCollected(true);
//            }else {
//                bean.setCollected(false);
//            }
//        }
        return data;
    }

    private void setAdapterListener(){
        mAdapter.setOnItemClickedListener((view, item) -> {
            TrendingRepoBean bean= (TrendingRepoBean) item;
            Intent intent=new Intent(mContext, WebRepoDevAty.class);
            Bundle bundle=new Bundle();
            bundle.putBoolean(Constant.BUNDLE_IS_REPO,true);
            bundle.putString(Constant.BUNDLE_WEB_URL,bean.getRepoLink());
            bundle.putString(Constant.BUNDLE_REPO,bean.getRepo());
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });
    }

    private String formatLanguage(String language) {
//        if (language.equals("C#")) {
//            language = "c%23";
//        } else if (language.equals("F#")) {
//            language = "f%23";
//        }
        return language;
    }
}
