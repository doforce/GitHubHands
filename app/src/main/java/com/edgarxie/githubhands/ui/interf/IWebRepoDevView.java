package com.edgarxie.githubhands.ui.interf;

import android.view.MenuItem;

/**
 * Created by dofor on 2017/7/10.
 */

public interface IWebRepoDevView extends ITopView {
    void setVisibility(int visibility);
    void finishAty();
    void setMenuItemTitle(MenuItem item,int id);
}
