package com.edgarxie.githubhands.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.presenter.MainP;
import com.edgarxie.githubhands.ui.fragment.CollectionFrag;
import com.edgarxie.githubhands.ui.fragment.TrendingDevFrag;
import com.edgarxie.githubhands.ui.fragment.TrendingRepoFrag;
import com.edgarxie.githubhands.ui.interf.IMainView;
import com.edgarxie.githubhands.util.Constant;

import java.util.List;

public class MainActivity extends BaseActivity<MainP>
        implements NavigationView.OnNavigationItemSelectedListener,IMainView {
    private FragmentManager mFm;
    private Fragment mRepoFrag;
    private Fragment mDeveloperFrag;
    private Fragment mCollectionsFrag;
    private static final String TAG = "MainActivity";
    private int startTitle=R.string.repository;
    private OnMenuClick onMenuClick;
    private OnTabSelectedListener onTabSelectedListener;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private TextView mToolbarTitle;
    private ImageView mToolbarSearch;
    private TabLayout mLanguageTab;
    private ImageView mSelect;
    private LinearLayout mTabLayout;

    private ImageView mUserAvatar;
    private TextView mUsername;
    private View mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mPresenter=new MainP(this);
        addDefaultFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (onMenuClick !=null){
            onMenuClick.onMenuClick(item);
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
        mRepoFrag = mFm.findFragmentByTag(Constant.TAG_TRENDING_REPO);
        mDeveloperFrag = mFm.findFragmentByTag(Constant.TAG_TRENDING_DEVELOPER);
        mCollectionsFrag = mFm.findFragmentByTag(Constant.TAG_COLLECTIONS);
        switch (id) {
            case R.id.nav_trending_repositories:
                mTabLayout.setVisibility(View.VISIBLE);
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.main_toolbar_frequency);
                startTitle=R.string.repository;
                setToolBarTitle(startTitle,R.string.daily);
                switchFragment(mDeveloperFrag,mCollectionsFrag,mRepoFrag,1
                        , Constant.TAG_TRENDING_REPO);
                break;
            case R.id.nav_trending_developers:
                mTabLayout.setVisibility(View.GONE);
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.main_toolbar_frequency);
                startTitle=R.string.developer;
                setToolBarTitle(startTitle,R.string.daily);
                switchFragment(mRepoFrag,mCollectionsFrag,mDeveloperFrag,2
                        , Constant.TAG_TRENDING_DEVELOPER);
                break;
            case R.id.nav_collections:
                mTabLayout.setVisibility(View.GONE);
                mToolbar.getMenu().clear();
                mToolbar.inflateMenu(R.menu.main_toolbar_type);
                setToolBarTitle(R.string.collection_repository);
                switchFragment(mRepoFrag,mDeveloperFrag,mCollectionsFrag,3
                        , Constant.TAG_COLLECTIONS);
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

        mRepoFrag= TrendingRepoFrag.newInstance(mPresenter
                .getDefaultTabText());
        transaction.add(R.id.fragment_container, mRepoFrag
                , Constant.TAG_TRENDING_REPO);
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


    public void initViews(){
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavView= (NavigationView) findViewById(R.id.nav_view);
        mToolbarTitle= (TextView) findViewById(R.id.toolbar_title);
        mToolbarSearch= (ImageView) findViewById(R.id.toolbar_search);
        mLanguageTab= (TabLayout) findViewById(R.id.tab_language);
        mSelect= (ImageView) findViewById(R.id.language_select);
        mTabLayout= (LinearLayout) findViewById(R.id.tab_layout);
        mHeaderView=mNavView.getHeaderView(0);
        mUserAvatar= (ImageView) mHeaderView.findViewById(R.id.user_avatar);
        mUsername= (TextView) mHeaderView.findViewById(R.id.username);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        setToolBarTitle(startTitle,R.string.daily);

        setListener();
    }


    private void setListener(){
        //// TODO: 17-7-16 获取token跳转
        mUserAvatar.setOnClickListener(view -> mPresenter.authEvent());
        mUsername.setOnClickListener(view -> mPresenter.authEvent());

        mToolbarSearch.setOnClickListener(v -> {
            Intent intent=new Intent(this, SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        mNavView.setNavigationItemSelectedListener(this);

        mSelect.setOnClickListener((v) -> mPresenter.goToCustomLanguage());
        mLanguageTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabSelectedListener.selected((String) tab.getText());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.verifyUser();
        mPresenter.setLanguages();
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

    public interface OnTabSelectedListener {
        void selected(String tab);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    //让fragment监听MainActivity中的MenuItem
    public interface OnMenuClick{
        void onMenuClick(MenuItem item);
    }

    public void setOnMenuClick(OnMenuClick mOnMenuClick) {
        this.onMenuClick = mOnMenuClick;
    }

    private void setToolBarTitle(int start, int end){
        mToolbarTitle.setText(getString(start)+" "+getString(end));
    }

    private void setToolBarTitle(int id){
        mToolbarTitle.setText(id);
    }

    @Override
    public void addTabs(List<String> tabs) {
        mLanguageTab.removeAllTabs();
        for (int i = 0; i < tabs.size(); i++) {
            mLanguageTab.addTab(mLanguageTab.newTab().setText(tabs.get(i)));
        }
    }


    @Override
    public void getTabAt(int index) {
        mLanguageTab.getTabAt(index);
    }

    @Override
    public void setUsernameText(String text) {
        mUsername.setText(text);
    }

    @Override
    public void setDefaultUsernameText() {
        mUsername.setText(R.string.login_with_github);
    }

    @Override
    public void setUserAvatar(String url) {
        Glide.with(this).load(url).into(mUserAvatar);
    }

    @Override
    public void setDefaultUserAvatar() {
        mUserAvatar.setImageDrawable(getResources()
                .getDrawable(R.drawable.avatar_default,null));
    }

    @Override
    protected void attachView() {
        mPresenter.attach(this);
    }
}
