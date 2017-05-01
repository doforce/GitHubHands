package com.edgarxie.githubhands.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.edgarxie.githubhands.ui.fragment.RepoFragment;

/**
 * Created by edgar on 17-4-30.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private SparseArray<String> mLang=new SparseArray<>();

    public MyFragmentPagerAdapter(FragmentManager fm,SparseArray<String> list) {
        super(fm);
        mLang=list;
    }

    @Override
    public Fragment getItem(int position) {
        return RepoFragment.newInstance(mLang.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return mLang.indexOfValue((String) object);
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
