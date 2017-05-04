package com.edgarxie.githubhands.ui.interf;

import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by edgar on 17-5-3.
 */

public interface ITrendingRepoView extends ITopView {
    FragmentManager getChildFragmentMgr();
    void addTabs(List<String> tabs);
}
