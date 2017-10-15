package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.adapter.CollectionAdapter;
import com.edgarxie.githubhands.presenter.CollectionPresenter;
import com.edgarxie.githubhands.ui.activity.MainActivity;
import com.edgarxie.githubhands.ui.interf.ICollectionView;
import com.edgarxie.githubhands.util.Constant;

/**
 * Created by edgar on 17-4-18.
 */

public class CollectionFrag extends BaseFragment<CollectionPresenter> implements MainActivity.OnCollectionMenuClick,ICollectionView {

    private MainActivity mActivity;
    private int whatCollection = Constant.Collection.REPO;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mPresenter=new CollectionPresenter(getContext());
        mActivity.setCollectionMenuClick(this);
        initViews();
    }

    private void initViews(){
        recyclerView= (RecyclerView) mView.findViewById(R.id.rv_list_repo_dev);
        refreshLayout= (SwipeRefreshLayout) mView.findViewById(R.id.layout_swipe_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        refreshLayout.setOnRefreshListener(() -> mPresenter.loadCollectionOnRefresh(whatCollection));
    }

    @Override
    public void onMenuClick(int id) {
        switch (id) {
            case R.id.action_collections_repo:
                mActivity.setCollTitle(getString(R.string.collection_repository));
                if (whatCollection!=Constant.Collection.REPO) {
                    whatCollection = Constant.Collection.REPO;
                    mPresenter.loadRepoCollection(() -> {
                    });
                }
                break;
            case R.id.action_collections_developer:
                mActivity.setCollTitle(getString(R.string.collection_developer));
                if (whatCollection!=Constant.Collection.DEVELOPER) {
                    whatCollection = Constant.Collection.DEVELOPER;
                    mPresenter.loadDevCollection(() -> {
                    });
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadCollection(whatCollection);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trending_list;
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }

    @Override
    public void setAdapter(CollectionAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRefresh(boolean refresh) {
        refreshLayout.post(() -> refreshLayout.setRefreshing(refresh));
    }
}
