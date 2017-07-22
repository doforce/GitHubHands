package com.edgarxie.githubhands.ui.activity;

import android.widget.ImageView;

import com.edgarxie.githubhands.R;

/**
 * Created by dofor on 2017/7/10.
 */

public class WebRepoDevAty extends BaseWebRepoDevAty {
    private ImageView collectView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_repo_developer;
    }

    @Override
    protected void initSonViews() {
        collectView= (ImageView) findViewById(R.id.bar_collect);
        collectView.setOnClickListener(v -> mPresenter.collectOperation(isRepo
                ,repoDesc,repoLang,repo,developer,avatar,url));
    }

    @Override
    public void setCollectImageBG(int id) {
        collectView.setBackground(getDrawable(id));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkStarredWatchedFollowing(isRepo);
        mPresenter.checkCollected(isRepo, repo, developer);
    }
}
