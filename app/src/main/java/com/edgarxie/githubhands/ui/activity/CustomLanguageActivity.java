package com.edgarxie.githubhands.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.CustomLanguagePresenter;
import com.edgarxie.githubhands.ui.interf.ICustomLanguageView;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;

/**
 * Created by edgar on 17-5-3.
 */

public class CustomLanguageActivity extends BaseActivity<CustomLanguagePresenter>
        implements ICustomLanguageView{
    private Bundle mBundle;
    private RecyclerView mLanguageList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_language);
        mPresenter=new CustomLanguagePresenter();
        initViews();
        mBundle=getIntent().getExtras();
    }

    private void initViews(){
        mLanguageList= (RecyclerView) findViewById(R.id.all_language_list);
        mLanguageList.setLayoutManager(new LinearLayoutManager(this));
        mLanguageList.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadLanguages();
    }

    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }

    @Override
    public void setAdapter(BaseRVAdapter adapter) {
        mLanguageList.setAdapter(adapter);
    }
}
