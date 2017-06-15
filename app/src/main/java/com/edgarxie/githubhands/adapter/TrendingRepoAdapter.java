package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.bean.RepoBean;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;

/**
 * Created by edgar on 17-5-3.
 */

public class TrendingRepoAdapter extends BaseRVAdapter<RepoBean,BaseRVAdapter.SparseArrayViewHolder> {
    private Context mContext;

    public TrendingRepoAdapter(Context context){
        mContext=context;
    }
    @Override
    protected void bindDataToItemView(SparseArrayViewHolder holder, RepoBean item) {
        holder.setText(R.id.item_repo,item.getRepo());
        holder.setText(R.id.item_desc,item.getDesc());
        holder.setText(R.id.item_lang,item.getLang());
        holder.setText(R.id.item_stars,item.getStars());
        holder.setText(R.id.item_forks,item.getForks());
        holder.setText(R.id.item_added_stars,item.getAdded_stars());
        setAvatarsUrl(holder,item.getAvatars());
    }

    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SparseArrayViewHolder holder=new SparseArrayViewHolder(inflateItemView(parent, R.layout.item_trending_repo));
        return holder;
    }

    //set the avatars url
    public void setAvatarsUrl(SparseArrayViewHolder holder,String ... avatars) {
        LinearLayout linearLayout=holder.getView(R.id.item_avatars);
        int avatarSize= (int) mContext.getResources().getDimension(R.dimen.avatar_small_size);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(avatarSize,avatarSize);
        for (int i = 0; i < avatars.length; i++) {
            ImageView image=new ImageView(mContext);
            linearLayout.addView(image,params);
        }
        for (int i = 0; i < avatars.length; i++) {
            Glide.with(mContext).load(avatars[i])
                    .into((ImageView) linearLayout.getChildAt(i));
        }
    }

}
