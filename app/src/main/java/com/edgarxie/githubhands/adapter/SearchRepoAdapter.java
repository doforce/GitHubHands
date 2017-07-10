package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.bean.SearchRepoBean;
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
        boolean selected=item.isCollected();
        if (!selected){
            holder.setImageBackground(R.id.item_collect
                    ,mContext.getResources().getDrawable(R.drawable.collection_heart_unselected));
        }else {
            holder.setImageBackground(R.id.item_collect
                    ,mContext.getResources().getDrawable(R.drawable.collection_heart_selected));
        }
        holder.setOnClickListener(R.id.item_collect, v -> {
            ToastUtil.show(mContext,"Collect");
            if (!selected){
                holder.setImageBackground(R.id.item_collect
                        ,mContext.getResources().getDrawable(R.drawable.collection_heart_selected));
                //// TODO: 2017/6/29 添加收藏
            }else {
                holder.setImageBackground(R.id.item_collect
                        ,mContext.getResources().getDrawable(R.drawable.collection_heart_unselected));
                //// TODO: 2017/6/29 删除收藏
            }
            SearchRepoBean bean=item;
            bean.setCollected(!selected);
            itemChange(bean,getPosition(item));
        });
    }

    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SparseArrayViewHolder holder=new SparseArrayViewHolder(inflateItemView(parent, R.layout.item_search_repo));
        return holder;
    }

}
