package com.superkorsuk.happybaby.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.Gender;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class BabyAddActivity extends AppCompatActivity {
    private int year, month, day;
    private Calendar birthday;
    private String name;
    private Gender gender;

    private EditText editTextName;
    private TextView textViewBirthday;
    private EditText editTextGestation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_add);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextName = (EditText) findViewById(R.id.et_baby_name);
        textViewBirthday = (TextView) findViewById(R.id.tv_baby_birthday);
        editTextGestation = (EditText) findViewById(R.id.et_gestation);

        textViewBirthday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        new DatePickerDialog(BabyAddActivity.this, dateSetListener, year, month, day).show();
                        break;

//                    case MotionEvent.ACTION_UP:
//                        textViewBirthday.setText(birthday);
//                        break;

                    default:
                        break;
                }
                return false;
            }
        });

        Button saveBaby = (Button) findViewById(R.id.btnSaveBabyInfo);

        saveBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO :: 각 항목 validation check 필요

                // 객체 생성 및 repository에 저장
                name = editTextName.getText().toString();

                RadioGroup genderGroup = (RadioGroup) findViewById(R.id.radio_group_gender);
                switch (genderGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_female:
                        gender = Gender.FEMALE;
                        break;
                    case R.id.radio_male:
                        gender = Gender.MALE;
                        break;
                }

                BabyRepository babyRepo = new BabyRepository(getApplicationContext());

                // add
                Baby baby = new Baby();
                baby.setName(name);
                baby.setGender(gender);
                baby.setBirthday(birthday);
                baby.setGestationPeriod(Integer.parseInt(editTextGestation.getText().toString()), 0);

                int result = babyRepo.create(baby);

                if (result > 0) {
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                    Log.d("DB", "baby added");
                } else {
                    Log.d("DB", "baby addition was failed");
                }
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            birthday = Calendar.getInstance();
            birthday.set(year, monthOfYear, dayOfMonth);

            String birthdayString = birthday.get(Calendar.YEAR) + "/" + (birthday.get(Calendar.MONTH)+1) + "/" + birthday.get(Calendar.DAY_OF_MONTH);
            textViewBirthday.setText(birthdayString);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
