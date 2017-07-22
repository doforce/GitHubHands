package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.DbCollectionMode;
import com.edgarxie.githubhands.model.bean.SearchRepoBean;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.utils.android.ToastUtil;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;

/**
 * Created by edgar on 17-5-3.
 */

public class SearchRepoAdapter extends BaseRVAdapter<SearchRepoBean,BaseRVAdapter.SparseArrayViewHolder> {
    private Context mContext;

    public SearchRepoAdapter(Context context){
        mContext=context;
    }
    @Override
    protected void bindDataToItemView(SparseArrayViewHolder holder, SearchRepoBean item) {
        holder.setText(R.id.item_repo,item.getFullName());
        holder.setText(R.id.item_desc,item.getDescription());
        holder.setText(R.id.item_lang,item.getLanguage());
        boolean selected= DbCollectionMode.isRepoCollected(item.getFullName());
        if (!selected){
            holder.setImageBackground(R.id.item_collect
                    ,mContext.getDrawable(R.drawable.collection_heart_unselected));
        }else {
            holder.setImageBackground(R.id.item_collect
                    ,mContext.getDrawable(R.drawable.collection_heart_selected));
        }
        holder.setOnClickListener(R.id.item_collect, v -> {
            boolean select=DbCollectionMode.isRepoCollected(item.getFullName());
            if (!select){
                DbCollectionMode.repoCollected(item.getDescription()
                        ,item.getLanguage(),item.getFullName(),item.getHtmlUrl());
                holder.setImageBackground(R.id.item_collect
                        ,mContext.getDrawable(R.drawable.collection_heart_selected));
                ToastUtil.show(mContext, Constant.COLLECTED);
            }else {
                DbCollectionMode.repoUncollected(item.getFullName());
                holder.setImageBackground(R.id.item_collect
                        ,mContext.getDrawable(R.drawable.collection_heart_unselected));
                ToastUtil.show(mContext,Constant.UNCOLLECTED);
            }
        });
    }

    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SparseArrayViewHolder holder=new SparseArrayViewHolder(inflateItemView(parent, R.layout.item_search_repo));
        return holder;
    }

}
