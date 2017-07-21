package com.edgarxie.githubhands.ui.activity;

import com.edgarxie.githubhands.R;

/**
 * Created by dofor on 2017/7/10.
 */

public class WebRepoDevAty extends BaseWebRepoDevAty {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_repo_developer;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkStarredWatchedFollowing(isRepo);
        mPresenter.checkCollected(isRepo, repo, developer);
    }
}
