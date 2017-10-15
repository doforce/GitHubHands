package com.edgarxie.githubhands.ui.interf;

import com.edgarxie.githubhands.adapter.TrendingDevAdapter;

/**
 * Created by edgar on 17-6-16.
 */

public interface ITrendingDeveloperView extends ITopTrendingView {

    void setRecyclerAdapter(TrendingDevAdapter adapter);
    void setFirstLoad(boolean isFirst);
}
