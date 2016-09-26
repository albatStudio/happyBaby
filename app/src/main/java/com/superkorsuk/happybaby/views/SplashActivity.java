package com.superkorsuk.happybaby.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.superkorsuk.happybaby.MainActivity;
import com.superkorsuk.happybaby.R;

public class SplashActivity extends Activity {
    int SPLASH_TIME = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int babyId = getSelectedBaby();
        if (babyId > 0) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    overridePendingTransition(0, android.R.anim.fade_in);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, SPLASH_TIME);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    overridePendingTransition(0, android.R.anim.fade_in);
                    startActivity(new Intent(SplashActivity.this, ProfileSelectActivity.class));
                    finish();
                }
            }, SPLASH_TIME);
        }
    }

    private int getSelectedBaby() {
        SharedPreferences pref = getSharedPreferences("status", 0);
        return pref.getInt("selected_baby_id", 0);
    }
}
