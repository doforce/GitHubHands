package com.edgarxie.githubhands;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edgarxie.githubhands.model.bean.TrendingDevBean;
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
