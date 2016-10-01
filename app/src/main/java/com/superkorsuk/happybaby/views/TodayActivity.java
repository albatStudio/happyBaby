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
import com.superkorsuk.happybaby.fragments.TodayFragment;


public class TodayActivity extends AppCompatActivity implements TodayFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        createBottomBarListeners(savedInstanceState);
    }

    private void createBottomBarListeners(Bundle savedInstanceState) {

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setDefaultTab(R.id.tab_today);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_today) {
                    loadTodayFragment();
                } else if (tabId == R.id.tab_baby_do) {

                } else if (tabId == R.id.tab_statistics) {

                } else if (tabId == R.id.tab_dev) {
                    startActivity(new Intent(TodayActivity.this, MainActivity.class));
                }
            }
        });
    }

    private void loadTodayFragment() {
        Fragment fragment = new TodayFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
