package com.edgarxie.githubhands.ui.interf;

import java.util.List;

/**
 * Created by edgar on 17-5-3.
 */

public interface IMainView extends ITopView {
    void addTabs(List<String> tabs);
    void getTabAt(int index);
}
