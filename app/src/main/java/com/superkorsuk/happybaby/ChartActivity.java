package com.superkorsuk.happybaby;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        // Random data 1
        List<Entry> entries = new ArrayList<Entry>();
        for (int i=0; i < 150; i++) {
            entries.add(new Entry(i, new Random().nextInt(5000)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "LineChart");
        dataSet.setColor(Color.BLUE);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.RED);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        // Random data 2
        List<Entry> entries2 = new ArrayList<Entry>();
        for (int i=0; i < 150; i++) {
            entries2.add(new Entry(i, new Random().nextInt(5000)));
        }

        LineDataSet dataSet1 = new LineDataSet(entries2, "LineChart2");
        dataSet1.setColor(Color.RED);
        dataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet1.setHighlightEnabled(true);
        dataSet1.setLineWidth(2);

        // chart data set
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet);
        dataSets.add(dataSet1);

        LineData data = new LineData(dataSets);

        chart.setData(data);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawBorders(false);
        chart.setDescription("Test Line Chart");
        chart.setDrawGridBackground(false);
        chart.setGridBackgroundColor(Color.WHITE);
        chart.setVisibleXRangeMaximum(10);

        chart.animateXY(2000,2000, Easing.EasingOption.EaseInExpo, Easing.EasingOption.EaseInExpo);
        chart.invalidate();

    }
}
