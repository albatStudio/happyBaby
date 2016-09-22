package com.superkorsuk.happybaby.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                startActivity(new Intent(ProfileSelectActivity.this, MainActivity.class));
            }
        });
    }

}
