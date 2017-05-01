package com.edgarxie.githubhands.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.ActivitySearchBinding;

/**
 * Created by edgar on 17-4-21.
 */

public class SearchAty extends AppCompatActivity {
    private ActivitySearchBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_search);
    }
}
