package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.TrendingDevAdapter;
import com.edgarxie.githubhands.model.bean.JsonTrendingDevBean;
import com.edgarxie.githubhands.model.bean.TrendingDevBean;
import com.edgarxie.githubhands.ui.interf.ITrendingDeveloperView;
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
        par.put("since", frequency);
        try {
            beanBuilder.getAsync(NetConstants.BASE_TRENDING_URL + NetConstants.DEVELOPER,
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
            ToastUtil.show(mContext,"Adapter");
        });
    }
}
