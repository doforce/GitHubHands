package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.DbCollectionMode;
import com.edgarxie.githubhands.model.bean.CollectionBean;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;

/**
 * Created by edgar on 17-5-3.
 */

public class CollectionAdapter extends BaseRVAdapter<CollectionBean,BaseRVAdapter.SparseArrayViewHolder> {
    private Context mContext;

    public CollectionAdapter(Context context){
        mContext=context;
    }
    @Override
    protected void bindDataToItemView(SparseArrayViewHolder holder, CollectionBean item) {
        if (item.getType()==CollectionBean.TYPE_REPO){
            repoHolder(holder,item);
        }else {
            devHolder(holder,item);
        }
    }

    private void repoHolder(SparseArrayViewHolder holder,CollectionBean item){
        holder.setText(R.id.item_repo,item.getRepo());
        holder.setText(R.id.item_desc,item.getDesc());
        holder.setText(R.id.item_lang,item.getLang());
        holder.setOnClickListener(R.id.item_collect, v -> {
            DbCollectionMode.repoUncollected(item.getRepo());
            itemRemove(getPosition(item));
        });
    }

    private void devHolder(SparseArrayViewHolder holder,CollectionBean item){
        holder.setText(R.id.item_user,item.getUser());
        ImageView view=holder.getView(R.id.item_avatar);
        Glide.with(mContext).load(item.getDeveloperAvatar()).into(view);
        holder.setOnClickListener(R.id.item_collect, v -> {
            DbCollectionMode.devUncollected(item.getUser());
            itemRemove(getPosition(item));
        });
    }


    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SparseArrayViewHolder holder=null;
        switch (viewType){
            case CollectionBean.TYPE_REPO:
                holder=new SparseArrayViewHolder(inflateItemView(parent,R.layout.item_search_repo));
                break;
            case CollectionBean.TYPE_DEV:
                holder=new SparseArrayViewHolder(inflateItemView(parent,R.layout.item_collection_developer));
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }
}
