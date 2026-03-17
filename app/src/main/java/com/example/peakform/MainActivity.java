package com.example.peakform;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.peakform.data.entity.Entry;
import com.example.peakform.ui.entry.AddEntryActivity;
import com.example.peakform.viewmodel.EntryViewModel;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textScore;
    private HorizontalBarChart chart;
    private EntryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textScore = findViewById(R.id.text_performance_score);
        chart = findViewById(R.id.performance_chart);
        Button btnAddAnother = findViewById(R.id.button_add_another);
        Button btnClear = findViewById(R.id.button_clear);


        viewModel = new ViewModelProvider(this).get(EntryViewModel.class);


        viewModel.getAllEntries().observe(this, entries -> {
            if (entries != null && !entries.isEmpty()) {
                updateChart(entries);
            } else {
                textScore.setText("0");
                textScore.setTextColor(Color.parseColor("#6200EE"));
                chart.clear();
            }
        });


        btnAddAnother.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
            startActivity(intent);
        });

        btnClear.setOnClickListener(v -> viewModel.deleteAll());
    }

    private void updateChart(List<Entry> entries) {
        List<BarEntry> barEntries = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        float totalWeightedScore = 0;
        float totalWeight = 0;

        for (int i = 0; i < entries.size(); i++) {
            Entry e = entries.get(i);


            float completionRatio = (e.goalMinutes > 0) ? Math.min(e.actualMinutes / e.goalMinutes, 1.0f) : 0;
            float completionPercent = completionRatio * 100;


            totalWeightedScore += (completionRatio * (e.quality / 10f) * e.userWeight);
            totalWeight += e.userWeight;

            // Chart Data
            barEntries.add(new BarEntry(i, completionPercent));
            labels.add(e.activityName);


            if (completionPercent < 50) {
                colors.add(Color.parseColor("#EF5350")); // Red
            } else if (completionPercent >= 80) {
                colors.add(Color.parseColor("#66BB6A")); // Green
            } else {
                colors.add(Color.parseColor("#FFCA28")); // Yellow/Amber
            }
        }


        int score = (totalWeight > 0) ? (int)((totalWeightedScore / totalWeight) * 100) : 0;
        textScore.setText(String.valueOf(score));


        if (score < 50) textScore.setTextColor(Color.parseColor("#EF5350"));
        else if (score >= 80) textScore.setTextColor(Color.parseColor("#66BB6A"));
        else textScore.setTextColor(Color.parseColor("#6200EE"));


        BarDataSet set = new BarDataSet(barEntries, "Completion %");
        set.setColors(colors);
        set.setValueTextSize(11f);
        set.setDrawValues(true);


        set.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "%";
            }
        });

        BarData data = new BarData(set);
        data.setBarWidth(0.35f);

        chart.setData(data);


        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(11f);
        xAxis.setLabelCount(labels.size());


        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setAxisMaximum(100f);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawLabels(false);

        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);

        chart.setFitBars(true);
        chart.animateY(1000);
        chart.invalidate();
    }
}