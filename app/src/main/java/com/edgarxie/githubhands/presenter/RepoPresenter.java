package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.model.bean.BaseTrendingRepoBean;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.githubhands.ui.interf.IRepoView;
import com.edgarxie.githubhands.util.NetConstants;
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

public class RepoPresenter extends BasePresenter<IRepoView> {
    private TrendingRepoAdapter mAdapter;
    private Context mContext;
    private List<TrendingRepoBean> mRepos = new ArrayList<>();
    private static final String TAG = "RepoPresenter";
    private static final String TIME_OUT="timeout";

    public RepoPresenter(Context context) {
        mContext = context;
        mAdapter = new TrendingRepoAdapter(mContext);
    }

    public void resumeRequest(String language,String frequency){
        requestRepo(language,frequency);
        setAdapterListener();
    }

    public void requestRepo(String language, String frequency) {
        mView.refreshingPost(() -> mView.setRefreshing(true));
        OkManagerBean<BaseTrendingRepoBean> beanBuilder = new OkManagerBean<>();
        Map<String, String> par = new HashMap<>();
        par.put("since", frequency);
        try {
            beanBuilder.getAsync(NetConstants.BASE_TRENDING_URL + NetConstants.REPO + formatLanguage(language)
                    , par, BaseTrendingRepoBean.class, data -> {
                        int count = data.getCount();
                        if (count != 0) {
                            mRepos = data.getItems();
                            mAdapter.setList(mRepos);
                            mView.runOnUIThread(() -> mView.setRecyclerAdapter(mAdapter));
                        } else {
                            ToastUtil.show(mContext, data.getMsg());
                        }
                        mView.refreshingPost(() -> mView.setRefreshing(false));
                    }, err -> {
                        mView.refreshingPost(() -> mView.setRefreshing(false));
                        ToastUtil.show(mContext, err);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAdapterListener(){
        mAdapter.setOnItemClickedListener((view, item) -> {
            ToastUtil.show(mContext,"Adapter");
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
