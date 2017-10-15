package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.model.DbTrendingCacheModel;
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
        List<TrendingRepoBean> repoBeen=userCache(language,frequency);
        requestRepo(language, frequency,repoBeen);
        mView.setFirstLoad(false);
        setAdapterListener();
    }

    public void requestRepo(String language, String frequency,List<TrendingRepoBean> cache) {
        mView.refreshingPost(() -> mView.setRefreshing(true));
        OkManagerBean<JsonTrendingRepoBean> beanBuilder = new OkManagerBean<>();
        Map<String, String> par = new HashMap<>();
        par.put(NetConstant.SINCE, frequency);
        try {
            beanBuilder.getAsync(NetConstant.BASE_TRENDING_URL + NetConstant.REPO + formatLanguage(language)
                    , par, JsonTrendingRepoBean.class, data -> {
                        int count = data.getCount();
                        if (count != 0) {
                            mRepos = data.getItems();
                            mAdapter.setList(mRepos);
                            mView.runOnUIThread(() -> mView.setRecyclerAdapter(mAdapter));
                            DbTrendingCacheModel.addRepoCache(mRepos);
                            DbTrendingCacheModel.deleteRepoCache(cache);
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

    public void refreshRepo(String language,String frequency){
        List<TrendingRepoBean> repoBeen=userCache(language, frequency);
        requestRepo(language,frequency,repoBeen);
    }

    private List<TrendingRepoBean> userCache(String language, String frequency){
//        mRepos= DbTrendingCacheModel.getRepoCache(language,frequency);
//        if (mRepos.size()!=0){
//            mAdapter.setList(mRepos);
//            mView.setRecyclerAdapter(mAdapter);
//            ToastUtil.show(mContext,"not null");
//        }else {
//            ToastUtil.show(mContext,"null");
//        }
        return mRepos;
    }

    private void setAdapterListener(){
        mAdapter.setOnItemClickedListener((view, item) -> {
            TrendingRepoBean bean= (TrendingRepoBean) item;
            Intent intent=new Intent(mContext, WebRepoDevAty.class);
            Bundle bundle=new Bundle();
            bundle.putBoolean(Constant.BUNDLE_IS_REPO,true);
            bundle.putString(Constant.BUNDLE_WEB_URL,bean.getRepoLink());
            bundle.putString(Constant.BUNDLE_REPO,bean.getRepo());
            bundle.putString(Constant.BUNDLE_REPO_DESC,bean.getDesc());
            bundle.putString(Constant.BUNDLE_REPO_LANG,bean.getLang());
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });
    }

    private String formatLanguage(String language) {
        return language.replace("#","-shuo");
    }
}
