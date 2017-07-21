package com.edgarxie.githubhands.ui.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.WebRepoDevP;
import com.edgarxie.githubhands.ui.interf.IWebRepoDevView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.utils.android.IntentUtil;
import com.edgarxie.utils.android.SharePreUtil;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by dofor on 2017/7/10.
 */

public abstract class BaseWebRepoDevAty extends BaseActivity<WebRepoDevP> implements IWebRepoDevView {
    protected Toolbar toolbar;
    protected ImageView back;
    protected TextView title;
    protected ProgressWheel progress;
    protected WebView webView;
    protected boolean isRepo;
    protected String url;
    protected String repo;
    protected String developer;
    protected String avatar;
    protected String repoDesc;
    protected String repoLang;
    protected boolean collected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setBundleData();
        initViews();
        mPresenter=new WebRepoDevP(this);
    }
    
    private void setBundleData(){
        Bundle bundle=getIntent().getExtras();
        isRepo=bundle.getBoolean(Constant.BUNDLE_IS_REPO);
        url =bundle.getString(Constant.BUNDLE_WEB_URL);
        if (isRepo) {
            repo = bundle.getString(Constant.BUNDLE_REPO);
            repoDesc=bundle.getString(Constant.BUNDLE_REPO_DESC);
            repoLang=bundle.getString(Constant.BUNDLE_REPO_LANG);
        }else {
            developer = bundle.getString(Constant.BUNDLE_DEVELOPER);
            avatar =bundle.getString(Constant.BUNDLE_DEV_AVATAR);
        }
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        back = (ImageView) findViewById(R.id.bar_back);
        title = (TextView) findViewById(R.id.bar_title);
        progress = (ProgressWheel) findViewById(R.id.progress_wheel);
        webView = (WebView) findViewById(R.id.web_show);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initWebView();
        initTitle();
        back.setOnClickListener(view -> finish());
        initSonViews();
    }

    protected void initSonViews(){}

    private void initTitle(){
        if (isRepo){
            title.setText(repo);
        }else {
            title.setText(developer);
        }
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setDefaultTextEncodingName(Constant.DEFAULT_ENCODING);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu_collect:
                mPresenter.collectOperation(isRepo,repoDesc,repoLang,repo,developer, avatar, url);
                break;
            case R.id.menu_open_in_browser:
                IntentUtil.openInBrowser(this, webView.getUrl());
                break;
            case R.id.menu_follow:
                mPresenter.authOperation(item);
                break;
            case R.id.menu_star:
                mPresenter.authOperation(item);
                break;
            case R.id.menu_watch:
                mPresenter.authOperation(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (SharePreUtil.getString(this,Constant.SHARE_PRE_TOKEN)!=null) {
            if (isRepo) {
                getMenuInflater().inflate(R.menu.web_repo, menu);
            } else {
                getMenuInflater().inflate(R.menu.web_developer, menu);
            }
        }else {
            getMenuInflater().inflate(R.menu.web_no_token,menu);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);
        webView.clearFormData();
        webView.clearHistory();
    }

    @Override
    public void setMenuItemTitle(int menuId, int textId) {
        toolbar.getMenu().findItem(menuId).setTitle(textId);
    }

    @Override
    public String getMenuItemTitle(int menuId) {
       return toolbar.getMenu().findItem(menuId).getTitle().toString();
    }

    @Override
    public void runUi(Runnable action) {
        runOnUiThread(action);
    }

    @Override
    public String getStr(int id) {
        return getString(id);
    }

    @Override
    public void finishAty() {
        finish();
    }

    @Override
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    @Override
    public String getTitleText() {
        return title.getText().toString();
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }

    protected abstract int getLayoutId();
}
