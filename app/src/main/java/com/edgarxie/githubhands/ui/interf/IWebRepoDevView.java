package com.edgarxie.githubhands.ui.interf;

/**
 * Created by dofor on 2017/7/10.
 */

public interface IWebRepoDevView extends ITopView {
    void finishAty();
    void setMenuItemTitle(int menuId,int textId);
    String getMenuItemTitle(int menuId);
    void runUi(Runnable action);
    String getTitleText();
    String getStr(int id);
    boolean isCollected();
    void setCollected(boolean collected);
}
