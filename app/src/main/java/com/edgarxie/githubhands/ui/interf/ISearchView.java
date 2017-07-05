package com.edgarxie.githubhands.ui.interf;

import com.edgarxie.githubhands.adapter.SearchRepoAdapter;

/**
 * Created by dofor on 2017/7/5.
 */

public interface ISearchView extends ITopView {

    void setAdapter(SearchRepoAdapter adapter);

    void runOnUi(Runnable action);

    void setRefresh(boolean refresh);

}
