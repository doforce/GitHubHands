package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.presenter.TrendingRepoPresenter;
import com.edgarxie.githubhands.ui.activity.MainActivity;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;
import com.edgarxie.githubhands.util.NetConstant;

public class TrendingRepoFrag extends BaseFragment<TrendingRepoPresenter> implements ITrendingRepoView
        , MainActivity.OnTabSelectedListener {
    public static final String ARGS ="args";
    private String mLanguage="";
    private String mFrequency=NetConstant.DAILY;
    private RecyclerView mRvRepo;
    private SwipeRefreshLayout mRefresh;
    protected MainActivity mActivity;
    private boolean isFirstLoad;

    public TrendingRepoFrag(){}

    public static TrendingRepoFrag newInstance(String lang) {
        TrendingRepoFrag fragment = new TrendingRepoFrag();
        Bundle args = new Bundle();
        args.putString(ARGS,lang);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLanguage=getArguments().getString(ARGS);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isFirstLoad=true;

        mRvRepo= (RecyclerView) mView.findViewById(R.id.rv_list_repo_dev);
        mRefresh= (SwipeRefreshLayout) mView.findViewById(R.id.layout_swipe_refresh);
        mRvRepo.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvRepo.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        mPresenter=new TrendingRepoPresenter(getContext());
        mActivity= (MainActivity) getActivity();
        mRefresh.setOnRefreshListener(() -> mPresenter.refreshRepo(mLanguage,mFrequency));
        mActivity.setRepoMenuClick(id -> {
            switch (id) {
                case R.id.action_trending_daily:
                    mActivity.setRepoTitle(getString(R.string.repository_daily));
                    applyFrequencyChange(NetConstant.DAILY);
                    break;
                case R.id.action_trending_weekly:
                    mActivity.setRepoTitle(getString(R.string.repository_weekly));
                    applyFrequencyChange(NetConstant.WEEKLY);
                    break;
                case R.id.action_trending_monthly:
                    mActivity.setRepoTitle(getString(R.string.repository_monthly));
                    applyFrequencyChange(NetConstant.MONTHLY);
                    break;
            }
        });
        mActivity.setOnTabSelectedListener(this);
    }

    private void applyFrequencyChange(String frequency){
        if (!mFrequency.equals(frequency)){
            mFrequency=frequency;
            mPresenter.refreshRepo(mLanguage,mFrequency);
        }
    }

    @Override
    public void selected(String tab) {
        if (!mLanguage.equals(tab)){
            mLanguage= tab;
            mPresenter.refreshRepo(mLanguage,mFrequency);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            mPresenter.resumeRequest(mLanguage, mFrequency);
        }
    }

    @Override
    public void setFirstLoad(boolean firstLoad) {
        isFirstLoad = firstLoad;
    }

    @Override
    public void runOnUIThread(Runnable action) {
        getActivity().runOnUiThread(action);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        mRefresh.setRefreshing(refreshing);
    }

    @Override
    public void refreshingPost(Runnable runnable) {
        mRefresh.post(runnable);
    }

    @Override
    public void setRecyclerAdapter(TrendingRepoAdapter adapter) {
        mRvRepo.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trending_list;
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
