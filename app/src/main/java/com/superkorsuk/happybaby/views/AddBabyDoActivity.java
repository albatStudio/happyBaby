package com.superkorsuk.happybaby.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.Baby;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddBabyDoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private PlaceholderFragment fragment0;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby_do);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_baby_do);
        toolbar.setTitle("아기 기록");
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_baby_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {

            // TODO 데이터 생성
            fragment0.saveBabyDo();


            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private int year, month, day;
        private int hour, minute;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        private DatePickerDialog.OnDateSetListener dateSetListener;

        private TimePickerDialog.OnTimeSetListener timeSetListener;


        @Override
        public void onStart() {
            super.onStart();

            GregorianCalendar calendar = new GregorianCalendar();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar issueDate = Calendar.getInstance();
                    issueDate.set(year, monthOfYear, dayOfMonth);

                    String birthdayString = issueDate.get(Calendar.YEAR) + "/" + (issueDate.get(Calendar.MONTH)+1) + "/" + issueDate.get(Calendar.DAY_OF_MONTH);
                    EditText etDate = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_date);


                    etDate.setText(birthdayString);
                }
            };

            timeSetListener = new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String time = hourOfDay + ":" + minute;
                    EditText etTime = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_time);

                    etTime.setText(time);

                }
            };



            EditText etDate = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_date);
            etDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        new DatePickerDialog(getActivity(), dateSetListener, year, month, day).show();
                    }


                    return false;
                }
            });

            EditText etTime = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_time);
            etTime.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        new TimePickerDialog(getActivity(), timeSetListener, hour, minute, true).show();
                    }

                    return false;
                }
            });
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_add_baby_do_0, container, false);
        }

        public void saveBabyDo() {
            int babyId = 1;
            EditText etNote = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_note);
            EditText etDate = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_date);
            EditText etAmount = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_amount);
            EditText etTime = (EditText) getView().findViewById(R.id.fragment_add_baby_0_et_time);

            String note = etNote.getText().toString();
            String issueDateText = etDate + " " + etTime;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date issueDate = new Date();
            int amount = Integer.parseInt(etAmount.getText().toString());
            try {
                issueDate = sdf.parse(issueDateText);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            BabyDo babyDo = BabyDoFactory.getFormula(babyId, issueDate, amount, note);

            BabyDoRepository babyDoRepo = new BabyDoRepository(getView().getContext());

            int result = babyDoRepo.create(babyDo);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    fragment0 = PlaceholderFragment.newInstance(position + 1);
                    return fragment0;
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                case 2:
                    return PlaceholderFragment.newInstance(position + 1);
                case 3:
                    return PlaceholderFragment.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "분유";
                case 1:
                    return "모유";
                case 2:
                    return "대변";
                case 3:
                    return "수면";
            }
            return null;
        }
    }
}
