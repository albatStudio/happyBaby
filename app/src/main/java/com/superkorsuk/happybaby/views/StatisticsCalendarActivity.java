package com.superkorsuk.happybaby.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoFactory;

import org.w3c.dom.Text;

import me.nlmartian.silkcal.DatePickerController;
import me.nlmartian.silkcal.DayPickerView;
import me.nlmartian.silkcal.SimpleMonthAdapter;

public class StatisticsCalendarActivity extends AppCompatActivity implements DatePickerController {

    private DayPickerView calendarView;
    private TextView selectedDayTextView;
    private String selectedDay;

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

        babyDoFormulaShow(year, month, day);
        babyDoMilkShow(year, month, day);
        babyDoPoopShow(year, month, day);
        babyDoSellpaShow(year, month, day);
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
