package com.edgarxie.githubhands.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.adapter.SearchRepoAdapter;
import com.edgarxie.githubhands.presenter.SearchPresenter;
import com.edgarxie.githubhands.ui.interf.ISearchView;

/**
 * Created by edgar on 17-4-21.
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements ISearchView {
    private ImageView mBack;
    private SearchView mSearch;
    private RecyclerView mResult;
    private SwipeRefreshLayout mRefresh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        mPresenter=new SearchPresenter(this);
    }

    private void initViews(){
        mBack= (ImageView) findViewById(R.id.back);
        mSearch= (SearchView) findViewById(R.id.search);
        mResult= (RecyclerView) findViewById(R.id.rv_search_result);
        mRefresh= (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refresh);

        mSearch.setIconified(false);

        mBack.setOnClickListener(v -> finish());
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.textSubmit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.textChange(newText);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setAdapterListener();
    }

    @Override
    public void setAdapter(SearchRepoAdapter adapter) {
        mResult.setAdapter(adapter);
    }

    @Override
    public void runOnUi(Runnable action) {
        runOnUiThread(action);
    }

    @Override
    public void setRefresh(boolean refresh) {
        mRefresh.post(() -> mRefresh.setRefreshing(refresh));
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
