package com.edgarxie.githubhands.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.edgarxie.githubhands.databinding.ActivityMainBinding;
import com.edgarxie.githubhands.ui.activity.SearchAty;

/**
 * Created by edgar on 17-4-21.
 */

public class MainVM extends BaseViewModel<ActivityMainBinding> {
    private Context mContext;

    public MainVM(ActivityMainBinding binding, Context context) {
        super(binding);
        mContext=context;
    }

    public void openSearch(View view){
        Intent intent=new Intent(mContext, SearchAty.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
