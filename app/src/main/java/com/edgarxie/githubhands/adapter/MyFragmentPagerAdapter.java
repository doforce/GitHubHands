package com.edgarxie.githubhands.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edgarxie.githubhands.ui.fragment.RepoFragment;

import java.util.ArrayList;

/**
 * Created by edgar on 17-4-30.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mLang=new ArrayList<>();

    public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<String> list) {
        super(fm);
        mLang=list;
    }

    @Override
    public Fragment getItem(int position) {
        return RepoFragment.newInstance(mLang.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return mLang.indexOf(object);
    }

    @Override
    public int getCount() {
        return mLang.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLang.get(position);
    }
}
