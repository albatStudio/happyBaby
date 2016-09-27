package com.superkorsuk.happybaby.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.superkorsuk.happybaby.MainActivity;
import com.superkorsuk.happybaby.R;


public class ProfileSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_select);

        initEventHandlers();
    }

    private void initEventHandlers() {

        setSelectProfileEventHandler();
        setNewProfileEventHandler();
        setSignInEventHandler();
    }

    private void setSignInEventHandler() {
        Button btnSignIn = (Button) findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileSelectActivity.this, FirebaseActivity.class));
            }
        });
    }

    private void setNewProfileEventHandler() {
        Button btnNewProfile = (Button)findViewById(R.id.btn_new_profile);
        btnNewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Profile 생성 Activity로 이동
            }
        });

    }

    private void setSelectProfileEventHandler() {
        Button btnSelectProfile = (Button)findViewById(R.id.btn_select_profile);
        btnSelectProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedBabyId = 1;
                setSelectedBaby(selectedBabyId);

                startActivity(new Intent(ProfileSelectActivity.this, MainActivity.class));
            }
        });
    }

    private void setSelectedBaby(int babyId) {
        SharedPreferences pref = getSharedPreferences("status", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("selected_baby_id", babyId);
        editor.commit();
    }

}
