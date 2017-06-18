package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.model.bean.TrendingRepoBean;
import com.edgarxie.githubhands.ui.interf.IRepoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-6-16.
 */

public class RepoPresenter extends BasePresenter<IRepoView> {
    private TrendingRepoAdapter mAdapter;
    private Context mContext;
    private List<TrendingRepoBean> mRepos = new ArrayList<>();
    private static final String TAG = "RepoPresenter";

    public RepoPresenter(Context context) {
        mContext = context;
        mAdapter = new TrendingRepoAdapter(mContext);
    }

    public void requestRepo(String language, String frequency) {
//        mView.refreshingPost(() -> mView.setRefreshing(true));
//        OkManagerBean<BaseTrendingRepoBean> beanBuilder = new OkManagerBean<>();
//        Map<String, String> par = new HashMap<>();
//        par.put("since", frequency);
//        try {
//            beanBuilder.getAsync(NetConstants.BASE_TRENDING_URL + NetConstants.REPO + formatLanguage(language)
//                    , par, BaseTrendingRepoBean.class, data -> {
//                        mRepos = data.getItems();
//                        mAdapter.setList(mRepos);
//                        mView.setRecyclerAdapter(mAdapter);
//                        mView.refreshingPost(() -> mView.setRefreshing(false));
//                    }, System.err::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void onRefresh() {

    }

    //当frequency或者tab中的language改变时，重新价值trending项目
    public void reloadRepo(String language, String frequency) {
        requestRepo(language, frequency);
    }

    private String formatLanguage(String language) {
        if (language.equals("C#")) {
            language = "c%23";
        } else if (language.equals("F#")) {
            language = "f%23";
        }
        return language;
    }
}
