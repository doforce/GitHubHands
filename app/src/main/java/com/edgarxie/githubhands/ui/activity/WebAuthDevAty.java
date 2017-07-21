package com.edgarxie.githubhands.ui.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.edgarxie.githubhands.R;

/**
 * Created by dofor on 2017/7/10.
 */

public class WebAuthDevAty extends BaseWebRepoDevAty {
    private ImageView mDelete;

    @Override
    protected void initSonViews() {
        mDelete= (ImageView) findViewById(R.id.delete);
        mDelete.setOnClickListener(v -> mPresenter.deleteToken());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_auth_developer;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkCollected(isRepo, repo, developer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
