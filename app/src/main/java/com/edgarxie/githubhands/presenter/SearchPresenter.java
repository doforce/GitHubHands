package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.view.View;

import com.edgarxie.githubhands.adapter.SearchRepoAdapter;
import com.edgarxie.githubhands.model.bean.SearchRepoBean;
import com.edgarxie.githubhands.model.bean.JsonSearchRepoBean;
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
    private List<SearchRepoBean> repos=new ArrayList<>();
    private SearchRepoAdapter mAdapter;

    public SearchPresenter(Context context){
        mContext=context;
        mAdapter=new SearchRepoAdapter(mContext);
    }

    public void textSubmit(String query) {
        OkManagerBean<JsonSearchRepoBean> okManagerBean=new OkManagerBean<>();
        Map<String,String> p=new HashMap<>();
        p.put("q",query);
        mView.setVisibility(View.VISIBLE);
        try {
            okManagerBean.getAsync(NetConstants.BASE_GITHUB_URL
                    + NetConstants.GITHUB_SEARCH_REPO, p, JsonSearchRepoBean.class, data -> {
                    int count=data.getTotalCount();
                if (count!=0){
//                    repos.clear();
//                    List<JsonSearchRepoBean.SearchRepoBean> items=data.getItems();
//                    for (int i = 0; i < items.size(); i++) {
//                        repos.add(items.get(i));
//                        if (i==29)
//                            break;
//                    }
                    repos=data.getItems();
                    mAdapter.setList(repos);
                    mView.setAdapter(mAdapter);
                }else {
                    mView.showToast("No result for the search");
                }
                mView.setVisibility(View.GONE);
            }, msg -> {
                mView.showToast("Connection error");
                mView.setVisibility(View.GONE);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void textChange(String newText) {

    }

    public void setAdapterListener(){
        mAdapter.setOnItemClickedListener((view, item) -> {
            SearchRepoBean data= (SearchRepoBean) item;
            ToastUtil.show(mContext,data.getHtmlUrl());
        });
    }
}
