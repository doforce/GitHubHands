package com.edgarxie.githubhands.presenter;

import android.content.Context;

import com.edgarxie.githubhands.adapter.CustomLanguageAdapter;
import com.edgarxie.githubhands.model.DbLangModel;
import com.edgarxie.githubhands.model.table.TrendingLang;
import com.edgarxie.githubhands.ui.interf.ICustomLanguageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-5-3.
 */

public class CustomLangP extends BasePresenter<ICustomLanguageView> {
    private CustomLanguageAdapter mAdapter;
    private List<TrendingLang> mData =new ArrayList<>();

    public CustomLangP(Context context){
        this.mContext=context;
        mAdapter=new CustomLanguageAdapter();
    }

    public void loadLanguages() {
        mData=DbLangModel.getAllLang();
        mAdapter.setList(mData);
        mView.setAdapter(mAdapter);
    }

}
