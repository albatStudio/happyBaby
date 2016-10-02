package com.superkorsuk.happybaby.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.adapters.ExpandableListViewAdapter;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.db.BabyRepository;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;
import com.superkorsuk.happybaby.models.PoopAmount;
import com.superkorsuk.happybaby.models.PoopColor;
import com.superkorsuk.happybaby.util.DateAndTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.nlmartian.silkcal.DatePickerController;
import me.nlmartian.silkcal.DayPickerView;
import me.nlmartian.silkcal.SimpleMonthAdapter;

import static junit.framework.Assert.assertTrue;

public class StatisticsCalendarActivity extends AppCompatActivity implements DatePickerController {

    private static final int BABY_ID = 1;

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
        month = month + 1;

        System.out.println("Fjdkfjdkfj");
        Log.d("onDayOfMonthSelected", "확인용1 : " );

        System.out.println(" year=" + year + " month=" + month + " day=" + day);
        selectedDay = year + "/" + month + "/" + day;
        selectedDayTextView.setText(selectedDay);


/*        babyDoFormulaShow(year, month, day);
        babyDoMilkShow(year, month, day);
        babyDoPoopShow(year, month, day);
        babyDoSellpaShow(year, month, day);*/




/*        // 날짜 선택시 list view에 데이타를 넣어주는 example
        List<ExpandableListViewAdapter.Item> selectedDayData = new ArrayList<>();

        // for breastfeeding (수유)
        selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "수유"));
        selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "10시 05분: 10ml"));
        selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "16시 30분: 10ml"));

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

        recyclerview.setAdapter(new ExpandableListViewAdapter(selectedDayData));*/

        babyDoShow(year, month, day);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

    }

    public void babyDoShow(int year, int month, int day) {
        List<ExpandableListViewAdapter.Item> selectedDayData = new ArrayList<>();
        BabyDoRepository repo = new BabyDoRepository(getApplicationContext());

        //TODO :: 실 배포시 아래 날짜 절대값을 변수로 변경 필요
        List<BabyDo> babyDoList = repo.getBabyDoListAt(BABY_ID, 2016, 6, 19, BabyDoType.BREAST_MILK);

        selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.HEADER, "응가"));

        if(babyDoList.size() < 1) {
            selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, "기록 없음"));
        }

        for(BabyDo milk : babyDoList) {
            Date issueDate = milk.getIssueDate();
            int amount = milk.getAmount();
            String note = milk.getNote();

            String time = "" + issueDate;
            String poopData = "" + time.substring(12,19) + "  양:" + amount + "  기록:" + note;

            selectedDayData.add(new ExpandableListViewAdapter.Item(ExpandableListViewAdapter.CHILD, poopData));
        }
        recyclerview = (RecyclerView) findViewById(R.id.LV_statistics_selected_day);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(new ExpandableListViewAdapter(selectedDayData));
    }

}
