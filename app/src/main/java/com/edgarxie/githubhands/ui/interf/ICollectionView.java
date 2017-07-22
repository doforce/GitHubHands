package com.edgarxie.githubhands.ui.interf;

import com.edgarxie.githubhands.adapter.CollectionAdapter;

/**
 * Created by edgar on 17-7-22.
 */

public interface ICollectionView  extends ITopView{
    void setAdapter(CollectionAdapter adapter);
    void setRefresh(boolean refresh);
}
