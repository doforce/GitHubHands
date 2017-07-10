package com.edgarxie.githubhands.ui.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by dofor on 2017/7/10.
 */

public class WebRepoDevRepoDevAty extends BaseActivity<WebRepoDevP> implements IWebRepoDevView {
    private Toolbar mToolbar;
    private ImageView mBack;
    private TextView mTitle;
    private ProgressWheel mProgress;
    private WebView mWebView;
    private boolean isRepo;
    private String mUrl;
    private String mRepo;
    private String mDeveloper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_repo_developer);
        setBundleData();
        initViews();
        mPresenter=new WebRepoDevP(this);
    }
    
    private void setBundleData(){
        Bundle bundle=getIntent().getExtras();
        isRepo=bundle.getBoolean(Constant.BUNDLE_IS_REPO);
        mUrl =bundle.getString(Constant.BUNDLE_WEB_URL);
        mRepo=bundle.getString(Constant.BUNDLE_REPO);
        mDeveloper=bundle.getString(Constant.BUNDLE_DEVELOPER);
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBack= (ImageView) findViewById(R.id.bar_back);
        mTitle= (TextView) findViewById(R.id.bar_title);
        mProgress= (ProgressWheel) findViewById(R.id.progress_wheel);
        mWebView= (WebView) findViewById(R.id.web_show);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initWebView();
        initTitle();
        mBack.setOnClickListener(view -> finish());
    }

    private void initTitle(){
        if (isRepo){
            mTitle.setText(mRepo);
        }else {
            mTitle.setText(mDeveloper);
        }
    }

    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setDefaultTextEncodingName(Constant.DEFAULT_ENCODING);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(mUrl);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mProgress.setVisibility(View.GONE);
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
                break;
            case R.id.menu_share:
                break;
            case R.id.menu_follow:
                break;
            case R.id.menu_star:
                break;
            case R.id.menu_watch:
                break;
            case R.id.menu_fork:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //// TODO: 2017/7/10 从数据库或者网络中确定menu中的值
        if (isRepo){
            getMenuInflater().inflate(R.menu.web_repo, menu);
        }else {
            getMenuInflater().inflate(R.menu.web_developer,menu);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.clearCache(true);
        mWebView.clearFormData();
        mWebView.clearHistory();
    }

    @Override
    public void setVisibility(int visibility) {
        mProgress.setVisibility(visibility);
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
