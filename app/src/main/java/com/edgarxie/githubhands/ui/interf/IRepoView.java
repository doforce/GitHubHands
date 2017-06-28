package com.edgarxie.githubhands.ui.interf;

import com.edgarxie.githubhands.adapter.TrendingRepoAdapter;

/**
 * Created by edgar on 17-6-16.
 */

public interface IRepoView extends ITopView {
    void setRefreshing(boolean refreshing);
    void refreshingPost(Runnable runnable);
    void setRecyclerAdapter(TrendingRepoAdapter adapter);
    void runOnUIThread(Runnable action);
}
