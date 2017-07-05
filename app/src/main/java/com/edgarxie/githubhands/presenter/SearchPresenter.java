package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.adapter.SearchRepoAdapter;
import com.edgarxie.githubhands.model.bean.SearchRepoBean;
import com.edgarxie.githubhands.ui.interf.ISearchView;
import com.edgarxie.githubhands.util.NetConstants;
import com.edgarxie.utils.android.ToastUtil;
import com.xxdong.ok.OkManagerBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dofor on 2017/7/5.
 */

public class SearchPresenter extends BasePresenter<ISearchView> {
    private List<SearchRepoBean.Items> repos=new ArrayList<>();
    private SearchRepoAdapter mAdapter;

    public SearchPresenter(Context context){
        mContext=context;
        mAdapter=new SearchRepoAdapter(mContext);
    }

    public void textSubmit(String query) {
        OkManagerBean<SearchRepoBean> okManagerBean=new OkManagerBean<>();
        Map<String,String> p=new HashMap<>();
        p.put("q",query);
        mView.setRefresh(true);
        try {
            okManagerBean.getAsync(NetConstants.BASE_GITHUB_URL
                    + NetConstants.GITHUB_SEARCH_REPO, p, SearchRepoBean.class, data -> {
                    int count=data.getTotalCount();
                if (count!=0){
                    repos.clear();
                    List<SearchRepoBean.Items> items=data.getItems();
                    for (int i = 0; i < items.size(); i++) {
                        repos.add(items.get(i));
                        if (i==29)
                            break;
                    }
                    mAdapter.setList(repos);
                    mView.runOnUi(() -> mView.setAdapter(mAdapter));
                }else {
                    mView.runOnUi(() -> ToastUtil.show(mContext,"No result for the search"));
                }
                mView.setRefresh(false);
            }, msg -> {
                mView.runOnUi(() -> ToastUtil.show(mContext,"Connection error"));
                mView.setRefresh(false);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void textChange(String newText) {

    }

    public void setAdapterListener(){
        mAdapter.setOnItemClickedListener((view, item) -> {
            SearchRepoBean.Items data= (SearchRepoBean.Items) item;
            ToastUtil.show(mContext,data.getHtmlUrl());
        });
    }
}
