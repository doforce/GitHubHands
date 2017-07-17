package com.edgarxie.githubhands.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.TokenGeneratePresenter;
import com.edgarxie.githubhands.ui.interf.ITokenGenerateView;
import com.edgarxie.githubhands.util.NetConstant;
import com.edgarxie.utils.android.ToastUtil;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by edgar on 17-7-16.
 */

public class TokenGenerateActivity extends BaseActivity<TokenGeneratePresenter> implements ITokenGenerateView {
    private ImageView mBack;
    private ImageView mQuestion;
    private EditText mTokenBox;
    private Button mTokenSubmit;
    private ProgressWheel mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_token);
        initViews();
        mPresenter=new TokenGeneratePresenter(this);
    }

    private void initViews() {
        mBack= (ImageView) findViewById(R.id.back);
        mQuestion= (ImageView) findViewById(R.id.question);
        mTokenBox= (EditText) findViewById(R.id.token_box);
        mTokenSubmit= (Button) findViewById(R.id.token_submit);
        mProgress= (ProgressWheel) findViewById(R.id.progress_wheel);

        mBack.setOnClickListener(view -> finish());
        mQuestion.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(NetConstant.GITHUB_TOKEN_MANUAL_URL);
            intent.setData(content_url);
            startActivity(intent);
        });
        mTokenSubmit.setOnClickListener(v -> mPresenter.submit());
    }

    @Override
    public String getTokenText() {
        return mTokenBox.getText().toString();
    }

    @Override
    public void setProgressVisibility(int vi) {
        mProgress.setVisibility(vi);
    }

    @Override
    public void finishAty() {
        finish();
    }

    @Override
    public void runUi(Runnable action) {
        runOnUiThread(action);
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
