package com.edgarxie.githubhands.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.CustomLangP;
import com.edgarxie.githubhands.ui.interf.ICustomLanguageView;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;
import com.edgarxie.utils.android.recyclerview.RVDecoration;

/**
 * Created by edgar on 17-5-3.
 */

public class CustomLangAty extends BaseActivity<CustomLangP>
        implements ICustomLanguageView{
    private RecyclerView mLanguageList;
    private ImageView mBack;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_language);
        mPresenter=new CustomLangP(this);
        initViews();
    }

    private void initViews(){
        mLanguageList= (RecyclerView) findViewById(R.id.all_language_list);
        mBack= (ImageView) findViewById(R.id.toolbar_back);
        mTitle= (TextView) findViewById(R.id.toolbar_title);

        mLanguageList.setLayoutManager(new LinearLayoutManager(this));
        mLanguageList.addItemDecoration(new RVDecoration(this,RVDecoration.VERTICAL_LIST));
        mTitle.setText(getString(R.string.title_custom_language));
        mBack.setOnClickListener((view) -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadLanguages();
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
