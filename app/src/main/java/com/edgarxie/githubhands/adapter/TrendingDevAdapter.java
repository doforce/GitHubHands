package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.DbCollectionMode;
import com.edgarxie.githubhands.model.bean.TrendingDevBean;
import com.edgarxie.githubhands.util.Constant;
import com.edgarxie.utils.android.ToastUtil;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;

/**
 * Created by edgar on 17-5-3.
 */

public class TrendingDevAdapter extends BaseRVAdapter<TrendingDevBean,BaseRVAdapter.SparseArrayViewHolder> {
    private Context mContext;

    public TrendingDevAdapter(Context context){
        mContext=context;
    }
    @Override
    protected void bindDataToItemView(SparseArrayViewHolder holder, TrendingDevBean item) {

        holder.setText(R.id.item_user,item.getUser());
        holder.setText(R.id.item_full_name,item.getFullName());
        ImageView view=holder.getView(R.id.item_avatar);
        Glide.with(mContext).load(item.getDeveloperAvatar()).into(view);
        boolean selected= DbCollectionMode.isDevCollected(item.getUser());
        if (!selected){
            holder.setImageBackground(R.id.item_collect
                    ,mContext.getResources().getDrawable(R.drawable.collection_heart_unselected));
        }else {
            holder.setImageBackground(R.id.item_collect
                    ,mContext.getResources().getDrawable(R.drawable.collection_heart_selected));
        }
        holder.setOnClickListener(R.id.item_collect, v -> {
            boolean select=DbCollectionMode.isDevCollected(item.getUser());
            if (!select){
                holder.setImageBackground(R.id.item_collect
                        ,mContext.getResources().getDrawable(R.drawable.collection_heart_selected));
                DbCollectionMode.devCollected(item);
                ToastUtil.show(mContext, Constant.COLLECTED);
            }else {
                holder.setImageBackground(R.id.item_collect
                        ,mContext.getResources().getDrawable(R.drawable.collection_heart_unselected));
                DbCollectionMode.devUncollected(item);
                ToastUtil.show(mContext,Constant.UNCOLLECTED);
            }
            TrendingDevBean bean=item;
            bean.setCollected(!selected);
            itemChange(bean,getPosition(item));
        });
    }



    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SparseArrayViewHolder(inflateItemView(parent, R.layout.item_trending_developer));
    }

}
