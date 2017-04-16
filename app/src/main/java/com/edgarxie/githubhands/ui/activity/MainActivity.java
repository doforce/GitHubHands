package com.edgarxie.githubhands.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(mBinding.appBarMainId.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.appBarMainId.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_trending_repositories:
                mBinding.appBarMainId.toolbar.getMenu().clear();
                mBinding.appBarMainId.toolbar.inflateMenu(R.menu.main_toolbar);
                break;
            case R.id.nav_trending_developers:
                mBinding.appBarMainId.toolbar.getMenu().clear();
                mBinding.appBarMainId.toolbar.inflateMenu(R.menu.main_toolbar);
            case R.id.nav_collections:
                mBinding.appBarMainId.toolbar.getMenu().clear();
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
}
