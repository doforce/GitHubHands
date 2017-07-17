package com.edgarxie.githubhands.ui.interf;

/**
 * Created by edgar on 17-7-16.
 */

public interface ITokenGenerateView extends ITopView {

    void finishAty();

    String getTokenText();

    void setProgressVisibility(int vi);

    void runUi(Runnable action);

}
