package com.superkorsuk.happybaby.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.adapters.ExpandableListViewAdapter;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;

import java.util.ArrayList;
import java.util.List;

import me.nlmartian.silkcal.DatePickerController;
import me.nlmartian.silkcal.DayPickerView;
import me.nlmartian.silkcal.SimpleMonthAdapter;

public class StatisticsCalendarActivity extends AppCompatActivity implements DatePickerController {

    private DayPickerView calendarView;
    private TextView selectedDayTextView;
    private String selectedDay;
    private RecyclerView recyclerview;

    BabyRepository babyRepo;
    BabyDoRepository babyDoRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_calendar);

        babyRepo = new BabyRepository(getApplicationContext());
        babyDoRepo = new BabyDoRepository(getApplicationContext());

        selectedDayTextView = (TextView) findViewById(R.id.tv_selected_day);
        selectedDay = new String();

        calendarView = (DayPickerView) findViewById(R.id.calendar_view);
        calendarView.setController(this);
    }

    @Override
    public int getMaxYear() {
        return 0;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        System.out.println(" year=" + year + " month=" + month + " day=" + day);
        selectedDay = year + "/" + month + "/" + day;
        selectedDayTextView.setText(selectedDay);

/*        babyDoFormulaShow(year, month, day);
        babyDoMilkShow(year, month, day);
        babyDoPoopShow(year, month, day);
        babyDoSellpaShow(year, month, day);*/

        recyclerview = (RecyclerView) findViewById(R.id.LV_statistics_selected_day);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<ExpandableListViewAdapter.Item> selectedDayData = new ArrayList<>();

        // for breastfeeding (수유)
        ExpandableListViewAdapter.Item breastfeeding = new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "수유");
        breastfeeding.invisibleChildren = new ArrayList<>();
        breastfeeding.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "10시 05분: 10ml"));
        breastfeeding.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "16시 30분: 10ml"));

        selectedDayData.add(breastfeeding);

        // for poop (대변)
        ExpandableListViewAdapter.Item poop = new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "응가");
        poop.invisibleChildren = new ArrayList<>();
        poop.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "07시 13분: 일반똥"));
        poop.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "13시 45분: 설사"));

        selectedDayData.add(poop);

        // for duration (수면)
        ExpandableListViewAdapter.Item duration = new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "수면");
        duration.invisibleChildren = new ArrayList<>();
        duration.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "22:10 ~ 07:43"));
        duration.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "09:32 ~ 11:12"));

        selectedDayData.add(duration);

        recyclerview.setAdapter(new ExpandableListViewAdapter(selectedDayData));
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

    }

    public void babyDoFormulaShow(int year, int month, int day) {
    }

    public void babyDoMilkShow(int year, int month, int day) {
    }

    public void babyDoPoopShow(int year, int month, int day) {
    }

    public void babyDoSellpaShow(int year, int month, int day) {
    }
}
