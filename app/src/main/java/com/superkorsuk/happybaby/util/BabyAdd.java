package com.superkorsuk.happybaby.util;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.models.Baby;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class BabyAdd extends AppCompatActivity {
    private int year, month, day;
    static String birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_add);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        final TextView setBabyBirthday = (TextView) findViewById(R.id.tvBabyBirthday);

        setBabyBirthday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        new DatePickerDialog(BabyAdd.this, dateSetListener, year, month, day).show();
                        break;

                    case MotionEvent.ACTION_UP:
                        setBabyBirthday.setText(birthday);
                        break;

                    default:
                        break;
                }

                //TODO :: 날짜를 선택하면 textview에 해당 날짜가 기록되게 해야함
                return false;
            }
        });

        Button saveBaby = (Button) findViewById(R.id.btnSaveBabyInfo);

        saveBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO :: DB로 저장하는 내용 작성
                Toast.makeText(getApplicationContext(), "저장하기", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            birthday = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
            System.out.println("birthday = " + birthday);
            Toast.makeText(getApplicationContext(), birthday, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
