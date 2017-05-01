package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.edgarxie.githubhands.BR;
import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.ItemTrendingRepoBinding;
import com.edgarxie.githubhands.model.RepoBean;

/**
 * Created by edgar on 17-5-1.
 */

public class TrendingRepoAdapter
        extends BindingRecyclerAdapter<RepoBean,ItemTrendingRepoBinding> {

    public TrendingRepoAdapter(Context context) {
        super(context);
    }

    //set the avatars url
    @Override
    public void setOthers(int position,RepoBean currentData) {
        mBinding.itemAvatars.removeAllViews();
        String[] avatars=currentData.getAvatars();
        int avatarSize= (int) mContext.getResources().getDimension(R.dimen.avatar_small_size);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(avatarSize,avatarSize);
        for (int i = 0; i < avatars.length; i++) {
            ImageView image=new ImageView(mContext);
            mBinding.itemAvatars.addView(image,params);
        }
        for (int i = 0; i < avatars.length; i++) {
            Glide.with(mContext).load(avatars[i])
                    .into((ImageView) mBinding.itemAvatars.getChildAt(i));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_trending_repo;
    }

    @Override
    protected int getVariableId() {
        return BR.repos;
    }
}
