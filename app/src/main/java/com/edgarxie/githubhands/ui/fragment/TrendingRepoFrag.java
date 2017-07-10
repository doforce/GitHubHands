package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;
import com.edgarxie.githubhands.presenter.TrendingRepoPresenter;
import com.edgarxie.githubhands.ui.activity.MainActivity;
import com.edgarxie.githubhands.ui.interf.ITrendingRepoView;
import com.edgarxie.githubhands.util.NetConstant;

public class TrendingRepoFrag extends BaseFragment<TrendingRepoPresenter> implements ITrendingRepoView
        ,MainActivity.OnMenuClick, MainActivity.OnTabSelectedListener {
    public static final String ARGS ="args";
    private String mLanguage="";
    private String mFrequency;
    private RecyclerView mRvRepo;
    private SwipeRefreshLayout mRefresh;
    protected MainActivity mActivity;
    private boolean isFirstLoad=true;

    public TrendingRepoFrag() {

    }

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

        mRvRepo= (RecyclerView) mView.findViewById(R.id.rv_trending_repo);
        mRefresh= (SwipeRefreshLayout) mView.findViewById(R.id.layout_swipe_refresh);
        mRvRepo.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvRepo.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        mPresenter=new TrendingRepoPresenter(getContext());
        mActivity= (MainActivity) getActivity();

        mRefresh.setOnRefreshListener(() -> mPresenter.requestRepo(mLanguage,mFrequency));
        mActivity.setOnTabSelectedListener(this);
        mActivity.setOnMenuClick(this);
    }

    @Override
    public void onMenuClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_trending_daily:
                applyFrequencyChange(NetConstant.DAILY);
                break;
            case R.id.action_trending_weekly:
                applyFrequencyChange(NetConstant.WEEKLY);
                break;
            case R.id.action_trending_monthly:
                applyFrequencyChange(NetConstant.MONTHLY);
                break;
            case R.id.action_collections_repo:
                break;
            case R.id.action_collections_developer:
                break;
        }
    }

    private void applyFrequencyChange(String frequency){
        if (mFrequency!=frequency){
            mFrequency=frequency;
            mPresenter.requestRepo(mLanguage,mFrequency);
        }
    }

    @Override
    public void selected(String tab) {
        if (mLanguage!=tab){
            mLanguage= tab;
            mPresenter.requestRepo(mLanguage,mFrequency);
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
    public void onStop() {
        super.onStop();
        isFirstLoad=false;
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
