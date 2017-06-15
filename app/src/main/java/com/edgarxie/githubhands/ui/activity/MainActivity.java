package com.edgarxie.githubhands.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.MainP;
import com.edgarxie.githubhands.ui.fragment.CollectionFrag;
import com.edgarxie.githubhands.ui.fragment.TrendingDevFrag;
import com.edgarxie.githubhands.ui.fragment.TrendingRepoFrag;
import com.edgarxie.githubhands.util.MainConstants;
import com.edgarxie.githubhands.ui.interf.IMainView;

public class MainActivity extends BaseActivity<MainP>
        implements NavigationView.OnNavigationItemSelectedListener,IMainView {
    private FragmentManager mFm;
    private Fragment mRepoFrag;
    private Fragment mDeveloperFrag;
    private Fragment mCollectionsFrag;
    private static final String TAG = "MainActivity";
    private int startTitle=R.string.repository;
    private OnMenuClick mOnMenuClick;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private TextView mToolbarTitle;
    private ImageView mToolbarSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter=new MainP();
        initViews();
        addDefaultFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (mOnMenuClick!=null){
            mOnMenuClick.onMenuClick(item);
        }
        switch (id){
            case R.id.action_trending_daily:
                setToolBarTitle(startTitle,R.string.daily);
                break;
            case R.id.action_trending_weekly:
                setToolBarTitle(startTitle,R.string.weekly);
                break;
            case R.id.action_trending_monthly:
                setToolBarTitle(startTitle,R.string.monthly);
                break;
            case R.id.action_collections_repo:
                setToolBarTitle(R.string.collection_repository);
                break;
            case R.id.action_collections_developer:
                setToolBarTitle(R.string.collection_developer);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mRepoFrag = mFm.findFragmentByTag(MainConstants.TAG_TRENDING_REPO);
        mDeveloperFrag = mFm.findFragmentByTag(MainConstants.TAG_TRENDING_DEVELOPER);
        mCollectionsFrag = mFm.findFragmentByTag(MainConstants.TAG_COLLECTIONS);
        switch (id) {
            case R.id.nav_trending_repositories:
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.main_toolbar_frequency);
                startTitle=R.string.repository;
                setToolBarTitle(startTitle,R.string.daily);
                switchFragment(mDeveloperFrag,mCollectionsFrag,mRepoFrag,1
                        ,MainConstants.TAG_TRENDING_REPO);
                break;
            case R.id.nav_trending_developers:
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.main_toolbar_frequency);
                startTitle=R.string.developer;
                setToolBarTitle(startTitle,R.string.daily);
                switchFragment(mRepoFrag,mCollectionsFrag,mDeveloperFrag,2
                        ,MainConstants.TAG_TRENDING_DEVELOPER);
                break;
            case R.id.nav_collections:
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.main_toolbar_type);
                setToolBarTitle(R.string.collection_repository);
                switchFragment(mRepoFrag,mDeveloperFrag,mCollectionsFrag,3
                        ,MainConstants.TAG_COLLECTIONS);
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_feedback:
                break;
            case R.id.nav_share:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 添加默认要显示的fragment
     */
    private void addDefaultFragment(){
        mFm =getSupportFragmentManager();
        FragmentTransaction transaction= mFm.beginTransaction();
        mRepoFrag =new TrendingRepoFrag();
        transaction.add(R.id.fragment_container, mRepoFrag
                , MainConstants.TAG_TRENDING_REPO);
        transaction.commit();
    }

    private void switchFragment(Fragment hide1, Fragment hide2, Fragment show
            , int showId, String tag){
        FragmentTransaction ft=mFm.beginTransaction();
        if (hide1 != null) {
            ft.hide(hide1);
        }
        if (hide2 != null) {
            ft.hide(hide2);
        }
        if (show == null) {
            if (showId==1){
                show = new TrendingRepoFrag();
            }else if (showId==2){
                show=new TrendingDevFrag();
            }else if (showId==3){
                show=new CollectionFrag();
            }
            ft.add(R.id.fragment_container, show, tag);
        } else {
            ft.show(show);
        }
        ft.commit();
    }


    private void initViews(){
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavView= (NavigationView) findViewById(R.id.nav_view);
        mToolbarTitle= (TextView) findViewById(R.id.toolbar_title);
        mToolbarSearch= (ImageView) findViewById(R.id.toolbar_search);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        setToolBarTitle(startTitle,R.string.daily);

        mToolbarSearch.setOnClickListener(v -> {
            Intent intent=new Intent(this, SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_frequency, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //让fragment监听MainActivity中的MenuItem
    public interface OnMenuClick{
        void onMenuClick(MenuItem item);
    }

    public void setOnMenuClick(OnMenuClick mOnMenuClick) {
        this.mOnMenuClick = mOnMenuClick;
    }

    private void setToolBarTitle(int start, int end){
        mToolbarTitle.setText(getString(start)+" "+getString(end));
    }

    private void setToolBarTitle(int id){
        mToolbarTitle.setText(id);
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
