package com.superkorsuk.happybaby.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.superkorsuk.happybaby.MainActivity;
import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.adapters.TodayDoListViewAdapter;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.fragments.BabyDoFragment;
import com.superkorsuk.happybaby.fragments.BaseFragment;
import com.superkorsuk.happybaby.fragments.GrowthFragment;
import com.superkorsuk.happybaby.fragments.StatsFragment;
import com.superkorsuk.happybaby.fragments.TodayFragment;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;
import com.superkorsuk.happybaby.models.Gender;

import java.util.List;


public class TodayActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {

    BottomBar bottomBar;

    private static final int BABY_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        createBottomBarListeners(savedInstanceState);

        displayTodayDoList();
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

    public void displayTodayDoList() {

        BabyDoRepository repo = new BabyDoRepository(getApplicationContext());
        List<BabyDo> babyDoList = repo.getBabyDoListAt(BABY_ID, 2016, 6, 19);

        // ListView Set
        ListView listView = (ListView) findViewById(R.id.lvTodayDo);
        TodayDoListViewAdapter adapter = new TodayDoListViewAdapter();
        listView.setAdapter(adapter);


        for(BabyDo doList : babyDoList) {
            Log.d("doList : " , doList.getBabyDoType() + " , " + doList.getIssueDate());

            Drawable icon = ContextCompat.getDrawable(this, R.drawable.ic_info_black_24dp);

            switch (doList.getBabyDoType()) {
                case FORMULA:
                    icon = ContextCompat.getDrawable(this, R.drawable.ic_info_black_24dp);
                    break;
                case BREAST_MILK:
                    icon = ContextCompat.getDrawable(this, R.drawable.today_bottle);
                    break;
                case POOP:
                    icon = ContextCompat.getDrawable(this, R.drawable.today_dong);
                    break;
                case SLEEP:
                    icon = ContextCompat.getDrawable(this, R.drawable.today_sleep);
                    break;
                default:
                    break;

            }

            adapter.addItem(icon, doList.getBabyDoType().toString(), doList.getIssueDate().toString().substring(11, 18));
        }
    }

    public void displayTodayDoList1() {

        // Baby info get
        BabyRepository babyRepo = new BabyRepository(getApplicationContext());
        List<Baby> babyList = babyRepo.all();
        String[] babyInfo = new String[babyList.size()];

        // ListView Set
        ListView listView = (ListView) findViewById(R.id.lvTodayDo);
        TodayDoListViewAdapter adapter = new TodayDoListViewAdapter();
        listView.setAdapter(adapter);

        for(Baby baby : babyList) {
            Gender gen = baby.getGender();
            String gender;

            if(gen.equals("MALE")) gender = "아들";
            else gender = "딸";

            // TODO : 정보 보여주는 것을 방법, 내용 등 고민 필요
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_info_black_24dp), baby.getName().toString() + "   " + gender, baby.getBirthDayYear().toString() + "/" + baby.getBirthDayMonth().toString() + "/" + baby.getBirthDayDay().toString());
        }
    }
}
