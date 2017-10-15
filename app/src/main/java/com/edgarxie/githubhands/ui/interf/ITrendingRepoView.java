package com.edgarxie.githubhands.ui.interf;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;

/**
 * Created by edgar on 17-6-16.
 */

public interface ITrendingRepoView extends ITopTrendingView {
    void setRecyclerAdapter(TrendingRepoAdapter adapter);
    void setFirstLoad(boolean isFirst);
}
