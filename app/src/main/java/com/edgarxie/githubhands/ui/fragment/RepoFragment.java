package com.edgarxie.githubhands.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.FragmentRepoBinding;

public class RepoFragment extends BaseFragment {
    private FragmentRepoBinding mBinding;
    public static final String ARG_LANGUAGE="arg_language";
    private String mLanguage="";

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_repo, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.rvTrendingRepo.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvTrendingRepo.addItemDecoration(new DividerItemDecoration(getContext()
                , LinearLayout.VERTICAL));

    }
}
