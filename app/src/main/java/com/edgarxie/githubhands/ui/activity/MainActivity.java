package com.edgarxie.githubhands.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.ActivityMainBinding;
import com.edgarxie.githubhands.model.MenuModel;
import com.edgarxie.githubhands.ui.fragment.CollectionsFragment;
import com.edgarxie.githubhands.ui.fragment.TrendingDeveloperFragment;
import com.edgarxie.githubhands.ui.fragment.TrendingRepoFragment;
import com.edgarxie.githubhands.util.MainConstants;
import com.edgarxie.githubhands.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<MainViewModel,ActivityMainBinding>
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager mFm;
    private Fragment mRepoFrag;
    private Fragment mDeveloperFrag;
    private Fragment mCollectionsFrag;
    private static final String TAG = "MainActivity";
    private MenuModel mMenu=new MenuModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        mBinding.setModel(getModel());
        mMenu.setToolBarTitle(getString(R.string.repository_daily));
        mBinding.setMenu(mMenu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getModel() {
        mModel=new MainViewModel(mBinding,getApplicationContext());
        return mModel;
    }

    private void initViews(){
        setSupportActionBar(mBinding.appBarMainId.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.appBarMainId.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
        addDefaultFragment();
//        mBinding.appBarMainId.toolbarTitle.setText(R.string.repository_daily);
    }


    /**
     * 添加默认要显示的fragment
     */
    private void addDefaultFragment(){
        mFm =getSupportFragmentManager();
        FragmentTransaction transaction= mFm.beginTransaction();
        mRepoFrag =new TrendingRepoFragment();
        transaction.add(R.id.fragment_container, mRepoFrag
                , MainConstants.TAG_TRENDING_REPO);
        transaction.commit();
    }



    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_frequency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_trending_daily:
                setToolBarTitle(R.string.trending_daily);
                break;
            case R.id.action_trending_weekly:
                setToolBarTitle(R.string.trending_weekly);
                break;
            case R.id.action_trending_monthly:
                setToolBarTitle(R.string.trending_monthly);
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
                mBinding.appBarMainId.toolbar.getMenu().clear();
                mBinding.appBarMainId.toolbar
                        .inflateMenu(R.menu.main_toolbar_frequency);
                setToolBarTitle(R.string.trending_daily);
                switchFragment(mDeveloperFrag,mCollectionsFrag,mRepoFrag,1
                        ,MainConstants.TAG_TRENDING_REPO);
                break;
            case R.id.nav_trending_developers:
                mBinding.appBarMainId.toolbar.getMenu().clear();
                mBinding.appBarMainId.toolbar
                        .inflateMenu(R.menu.main_toolbar_frequency);
                setToolBarTitle(R.string.trending_daily);
                switchFragment(mRepoFrag,mCollectionsFrag,mDeveloperFrag,2
                        ,MainConstants.TAG_TRENDING_DEVELOPER);
                break;
            case R.id.nav_collections:
                mBinding.appBarMainId.toolbar.getMenu().clear();
                mBinding.appBarMainId.toolbar.inflateMenu(R.menu.main_toolbar_type);
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
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
                show = new TrendingRepoFragment();
            }else if (showId==2){
                show=new TrendingDeveloperFragment();
            }else if (showId==3){
                show=new CollectionsFragment();
            }
            ft.add(R.id.fragment_container, show, tag);
        } else {
            ft.show(show);
        }
        ft.commit();
    }

    private void setToolBarTitle(int id){
        mMenu.setToolBarTitle(getString(id));
    }

}
