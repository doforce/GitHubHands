package com.edgarxie.githubhands.ui.interf;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by edgar on 17-5-3.
 */

public interface ITrendingRepoView extends ITopView {
    void setPagerAdapter(FragmentPagerAdapter adapter);
    FragmentManager getChildFragmentMgr();
}
