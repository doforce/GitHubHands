package com.edgarxie.githubhands.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.CustomLanguagePresenter;

/**
 * Created by edgar on 17-5-3.
 */

public class CustomLanguageActivity extends BaseActivity<CustomLanguagePresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_language);
    }

    private void initViews(){
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width=metrics.widthPixels;
    }
}
