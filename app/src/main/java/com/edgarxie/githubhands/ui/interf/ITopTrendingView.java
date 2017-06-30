package com.edgarxie.githubhands.ui.interf;


/**
 * Created by dofor on 2017/6/30.
 */

public interface ITopTrendingView extends ITopView {
    void setRefreshing(boolean refreshing);
    void refreshingPost(Runnable runnable);
    void runOnUIThread(Runnable action);
}
