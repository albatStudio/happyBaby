package com.superkorsuk.happybaby.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.superkorsuk.happybaby.MainActivity;
import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.fragments.BabyDoFragment;
import com.superkorsuk.happybaby.fragments.BaseFragment;
import com.superkorsuk.happybaby.fragments.GrowthFragment;
import com.superkorsuk.happybaby.fragments.StatsFragment;
import com.superkorsuk.happybaby.fragments.TodayFragment;
import com.superkorsuk.happybaby.util.BabyUtil;


public class TodayActivity extends AppCompatActivity
        implements BaseFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_today);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createBottomBarListeners(savedInstanceState);
    }

    private void createBottomBarListeners(Bundle savedInstanceState) {

        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setDefaultTab(R.id.tab_today);


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                // TODO Fragment를 매번 생성하지 않도록 manager 생성 필요

                if (tabId == R.id.tab_today) {
                    switchTab(tabId);
                } else if (tabId == R.id.tab_baby_do) {
                    switchTab(tabId);

                } else if (tabId == R.id.tab_growth) {
                    switchTab(tabId);

                } else if (tabId == R.id.tab_statistics) {
                    switchTab(tabId);

                } else if (tabId == R.id.tab_dev) {
                    startActivity(new Intent(TodayActivity.this, MainActivity.class));
                }
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void switchTab(int tabId) {
        if (tabId == R.id.tab_today) {
            TodayFragment todayFrag = new TodayFragment();
            todayFrag.setBabyId(BabyUtil.getCurrentBabyId(getSharedPreferences("status", MODE_PRIVATE)));
            loadFragment(todayFrag);
            setTitle("Today");
        } else if (tabId == R.id.tab_baby_do) {
            loadFragment(new BabyDoFragment());
            setTitle("한일 입력");
        } else if (tabId == R.id.tab_growth) {
            loadFragment(new GrowthFragment());
            setTitle("아기 성장");
        } else if (tabId == R.id.tab_statistics) {
            loadFragment(new StatsFragment());
            setTitle("통계");
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_dev_add_a_baby_do) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
