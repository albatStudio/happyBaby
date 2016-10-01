package com.superkorsuk.happybaby.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.superkorsuk.happybaby.MainActivity;
import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.fragments.BabyDoFragment;
import com.superkorsuk.happybaby.fragments.BaseFragment;
import com.superkorsuk.happybaby.fragments.GrowthFragment;
import com.superkorsuk.happybaby.fragments.StatsFragment;
import com.superkorsuk.happybaby.fragments.TodayFragment;


public class TodayActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {

    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

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
                    loadFragment(new TodayFragment());
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
}
