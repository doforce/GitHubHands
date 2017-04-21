package com.edgarxie.githubhands.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.edgarxie.githubhands.databinding.ActivityMainBinding;
import com.edgarxie.githubhands.ui.activity.SearchActivity;

/**
 * Created by edgar on 17-4-21.
 */

public class MainViewModel extends BaseViewModel<ActivityMainBinding> {
    private Context mContext;

    public MainViewModel(ActivityMainBinding binding, Context context) {
        super(binding);
        mContext=context;
    }

    public void openSearch(View view){
        Intent intent=new Intent(mContext, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
