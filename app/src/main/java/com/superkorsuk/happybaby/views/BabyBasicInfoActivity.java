package com.superkorsuk.happybaby.views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.Gender;
import com.superkorsuk.happybaby.adapters.BabyBasicInfoListViewAdapter;

import java.util.List;

public class BabyBasicInfoActivity extends AppCompatActivity {

    private Drawable iconDrawable;
    private String babyName;
    private String babyDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_basic_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
/*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), BabyAddActivity.class));
            }
        });

        displayBabyBasicInfo();
    }

    protected void onStart(Bundle savedInstanceState) {
        super.onStart();
        setContentView(R.layout.activity_baby_basic_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayBabyBasicInfo();

    }

    @Override
    protected void onResume() {
        super.onResume();

        displayBabyBasicInfo();
    }

    public void displayBabyBasicInfo() {

        // Baby info get
        BabyRepository babyRepo = new BabyRepository(getApplicationContext());
        List<Baby> babyList = babyRepo.all();
        String[] babyInfo = new String[babyList.size()];

        // ListView Set
        ListView listView = (ListView) findViewById(R.id.lvBabyBasicInfo);
        BabyBasicInfoListViewAdapter adapter = new BabyBasicInfoListViewAdapter();
        listView.setAdapter(adapter);

        for(Baby baby : babyList) {
            Gender gen = baby.getGender();
            String gender;

            if(gen.equals("MALE")) gender = "아들";
            else gender = "딸";

            // TODO : 정보 보여주는 것을 방법, 내용 등 고민 필요
            adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_info_black_24dp), baby.getName().toString(), baby.getBirthDayYear().toString() + "/" + baby.getBirthDayMonth().toString() + "/" + baby.getBirthDayDay().toString());
        }
    }

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBabyDay() {
        return babyDay;
    }

    public void setBabyDay(String babyDay) {
        this.babyDay = babyDay;
    }


}
