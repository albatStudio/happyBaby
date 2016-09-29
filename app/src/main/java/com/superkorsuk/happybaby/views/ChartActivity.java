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
import com.superkorsuk.happybaby.models.BabyDoFactory;
import com.superkorsuk.happybaby.models.BabyDoType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ChartActivity extends AppCompatActivity {

    BabyDoRepository repository;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart = (LineChart) findViewById(R.id.chart);
        Button buttonRandomData = (Button) findViewById(R.id.btn_random_data);

        repository = new BabyDoRepository(ChartActivity.this);

        buttonRandomData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // baby_do random data
                for (int i = 0 ; i < 100 ; i++) {

                    BabyDo babyDoData = new BabyDo();

                    Calendar issueDate = Calendar.getInstance();
                    issueDate.add(Calendar.HOUR, i);

                    babyDoData = BabyDoFactory.getFormula(0, issueDate.getTime(), (int)(Math.random() * 1000), "화이팅");

                    repository.create(babyDoData);

                    if (i == 99) {
                        Toast.makeText(ChartActivity.this, "100개 랜덤데이터 생성 완료", Toast.LENGTH_SHORT).show();
                        if (chart != null) {
                            chart.animateY(1000, Easing.EasingOption.EaseInBack);
                            chart.notifyDataSetChanged();
                            chart.invalidate();
                        }
                    }
                }
            }
        });

        drawChart();
    }

    private void drawChart() {
        // get Data
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

            chart.animateY(1000, Easing.EasingOption.EaseInBack);
            chart.invalidate();
        }
    }
}
