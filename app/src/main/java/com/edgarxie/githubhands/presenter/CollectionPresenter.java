package com.edgarxie.githubhands.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edgarxie.githubhands.adapter.CollectionAdapter;
import com.edgarxie.githubhands.model.DbCollectionMode;
import com.edgarxie.githubhands.model.bean.CollectionBean;
import com.edgarxie.githubhands.model.table.DevCollection;
import com.edgarxie.githubhands.model.table.RepoCollection;
import com.edgarxie.githubhands.ui.activity.WebRepoDevAty;
import com.edgarxie.githubhands.ui.interf.ICollectionView;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.utils.functional.NoneConsumers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-7-22.
 */

public class CollectionPresenter extends BasePresenter<ICollectionView> {
    private CollectionAdapter adapter;
    private List<CollectionBean> data = new ArrayList<>();

    public CollectionPresenter(Context context) {
        mContext = context;
        adapter = new CollectionAdapter(mContext);
    }

    public void loadCollection(int whatCollection) {
        if (whatCollection == Constant.Collection.REPO) {
            loadRepoCollection(() -> {});
        } else {
            loadDevCollection(() -> {});
        }
        setAdapterListener();
    }

    private void setAdapterListener(){
        adapter.setOnItemClickedListener((view, item) -> {
            CollectionBean bean= (CollectionBean) item;
            if (bean.getType()==CollectionBean.TYPE_REPO){
                Intent intent=new Intent(mContext, WebRepoDevAty.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean(Constant.BUNDLE_IS_REPO,true);
                bundle.putString(Constant.BUNDLE_WEB_URL,bean.getRepoLink());
                bundle.putString(Constant.BUNDLE_REPO,bean.getRepo());
                bundle.putString(Constant.BUNDLE_REPO_DESC,bean.getDesc());
                bundle.putString(Constant.BUNDLE_REPO_LANG,bean.getLang());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }else {
                Intent intent=new Intent(mContext, WebRepoDevAty.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean(Constant.BUNDLE_IS_REPO,false);
                bundle.putString(Constant.BUNDLE_WEB_URL,bean.getUserLink());
                bundle.putString(Constant.BUNDLE_DEVELOPER,bean.getUser());
                bundle.putString(Constant.BUNDLE_DEV_AVATAR,bean.getDeveloperAvatar());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    public void loadCollectionOnRefresh(int whatCollection){
        if (whatCollection == Constant.Collection.REPO) {
            loadRepoCollection(() -> mView.setRefresh(false));
        } else {
            loadDevCollection(() -> mView.setRefresh(false));
        }
    }

    public void loadRepoCollection(NoneConsumers consumers){
        List<CollectionBean> temp = new ArrayList<>();
        List<RepoCollection> repos = DbCollectionMode.getAllRepoCollections();
        if (repos.size() != 0) {
            for (RepoCollection repo : repos) {
                CollectionBean bean = new CollectionBean();
                bean.setType(CollectionBean.TYPE_REPO);
                bean.setRepo(repo.getRepo());
                bean.setDesc(repo.getDesc());
                bean.setLang(repo.getLang());
                bean.setRepoLink(repo.getRepoLink());
                temp.add(bean);
            }
            data = temp;
            adapter.setList(data);
            mView.setAdapter(adapter);
            consumers.accept();
            temp = null;
        }
    }

    public void loadDevCollection(NoneConsumers consumers){
        List<CollectionBean> temp = new ArrayList<>();
        List<DevCollection> deve = DbCollectionMode.getAllDevCollections();
        if (deve.size() != 0) {
            for (DevCollection d : deve) {
                CollectionBean bean = new CollectionBean();
                bean.setType(CollectionBean.TYPE_DEV);
                bean.setDeveloperAvatar(d.getDeveloperAvatar());
                bean.setUser(d.getUser());
                bean.setUserLink(d.getUserLink());
                temp.add(bean);
            }
            data = temp;
            adapter.setList(data);
            mView.setAdapter(adapter);
            consumers.accept();
            temp = null;
        }
    }
}
