package com.superkorsuk.happybaby.util;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.Baby;

import java.util.List;

public class BabyBasicInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_basic_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        System.out.println("아기 시작");
        displayBabyBasicInfo();
    }

    protected void onStart(Bundle savedInstanceState) {
        super.onStart();
        setContentView(R.layout.activity_baby_basic_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayBabyBasicInfo();

    }

    public void displayBabyBasicInfo() {
        BabyRepository babyRepo = new BabyRepository(getApplicationContext());

        EditText editText = (EditText) findViewById(R.id.etBabyBasicInfo);

        List<Baby> babyList = babyRepo.all();

        System.out.println("아기 컴컴");
        for(Baby baby : babyList) {
            editText.setText(baby.toString());
            Log.d("Baby_Basic_Info", baby.toString());
        }

    }

}
