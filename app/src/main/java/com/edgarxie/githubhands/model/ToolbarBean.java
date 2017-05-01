package com.edgarxie.githubhands.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.edgarxie.githubhands.BR;

/**
 * Created by edgar on 17-4-21.
 */

public class ToolbarBean extends BaseObservable {

    private String toolBarTitle;
    private int menuId;

    @Bindable
    public int getMenuId(){
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Bindable
    public String getToolBarTitle() {
        return toolBarTitle;
    }

    public void setToolBarTitle(String toolBarTitle) {
        this.toolBarTitle = toolBarTitle;
        notifyPropertyChanged(BR.toolBarTitle);
    }
}
