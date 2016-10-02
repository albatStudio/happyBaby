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
        List<ExpandableListViewAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "Fruits"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Apple"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Orange"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Banana"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "Cars"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Audi"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Aston Martin"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "BMW"));
        data.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Cadillac"));

        ExpandableListViewAdapter.Item places = new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "Places");
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Kerala"));
        places.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Tamil Nadu"));
        places.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Karnataka"));
        places.invisibleChildren.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "Maharashtra"));

        data.add(places);

        recyclerview.setAdapter(new ExpandableListViewAdapter(data));
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
