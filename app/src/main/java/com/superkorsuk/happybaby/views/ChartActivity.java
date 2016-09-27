package com.superkorsuk.happybaby.views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.superkorsuk.happybaby.R;
import com.superkorsuk.happybaby.db.BabyDoRepository;
import com.superkorsuk.happybaby.models.BabyDo;
import com.superkorsuk.happybaby.models.BabyDoType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        LineChart chart = (LineChart) findViewById(R.id.chart);
        Button buttonRandomData = (Button) findViewById(R.id.btn_random_data);

        final BabyDoRepository repository = new BabyDoRepository(ChartActivity.this);

        buttonRandomData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // baby_do random data
                for (int i = 0 ; i < 100 ; i++) {
                    BabyDo babyDoData = new BabyDo();
                    babyDoData.setBabyDoType(BabyDoType.FORMULA);
                    babyDoData.setAmount((int)(Math.random() * 1000));
                    babyDoData.setNote("우리애기 화이팅");

                    Calendar issueDate = Calendar.getInstance();
                    issueDate.add(Calendar.HOUR, i);
                    babyDoData.setIssueDate(issueDate.getTime());

                    repository.create(babyDoData);

                    if (i == 99) {
                        Toast.makeText(ChartActivity.this, "100개 랜덤데이터 생성 완료", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        List<BabyDo> rawData = repository.all();

        if (rawData.size() > 0) {
            List<Entry> entries = new ArrayList<Entry>();

            for (BabyDo data : rawData) {
                entries.add(new Entry(data.getId(),data.getAmount()));
            }

            LineDataSet dataSet = new LineDataSet(entries, "Milk trend");
            dataSet.setColor(Color.BLUE);
            dataSet.setDrawFilled(false);
            dataSet.setFillColor(Color.RED);
            dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

            // chart data set
            List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(dataSet);

            LineData data = new LineData(dataSets);

            chart.setData(data);
            chart.setBackgroundColor(Color.WHITE);
            chart.setDrawBorders(false);
            chart.setDescription("Test Milk Chart");
            chart.setDrawGridBackground(false);
            chart.setGridBackgroundColor(Color.WHITE);
            chart.setVisibleXRangeMaximum(10);

            chart.animateY(2000, Easing.EasingOption.EaseInBack);
            chart.invalidate();
        }



        // Random data 1
//        List<Entry> entries = new ArrayList<Entry>();
//        for (int i=0; i < 150; i++) {
//            entries.add(new Entry(i, new Random().nextInt(5000)));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries, "LineChart");
//        dataSet.setColor(Color.BLUE);
//        dataSet.setDrawFilled(true);
//        dataSet.setFillColor(Color.RED);
//        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        // Random data 2
//        List<Entry> entries2 = new ArrayList<Entry>();
//        for (int i=0; i < 150; i++) {
//            entries2.add(new Entry(i, new Random().nextInt(5000)));
//        }
//
//        LineDataSet dataSet1 = new LineDataSet(entries2, "LineChart2");
//        dataSet1.setColor(Color.RED);
//        dataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        dataSet1.setHighlightEnabled(true);
//        dataSet1.setLineWidth(2);



    }
}
