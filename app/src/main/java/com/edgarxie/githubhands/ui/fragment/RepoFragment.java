package com.edgarxie.githubhands.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.edgarxie.githubhands.R;

public class RepoFragment extends BaseFragment {
    public static final String ARG_LANGUAGE="arg_language";
    private String mLanguage="";
    private RecyclerView mRvRepo;
    private SwipeRefreshLayout mRefresh;

    public RepoFragment() {

    }

    public static RepoFragment newInstance(String lang) {
        RepoFragment fragment = new RepoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LANGUAGE,lang);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLanguage = getArguments().getString(ARG_LANGUAGE);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRvRepo= (RecyclerView) mView.findViewById(R.id.rv_trending_repo);
        mRefresh= (SwipeRefreshLayout) mView.findViewById(R.id.layout_swipe_refresh);
        mRvRepo.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvRepo.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_repo;
    }

    @Override
    protected void attachView() {

    }
}
