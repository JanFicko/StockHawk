package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.udacity.stockhawk.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.udacity.stockhawk.R.id.chart;

public class DetailActivity extends AppCompatActivity {

    private String mColumnHistory;

    @BindView(R.id.tv_stock_name)
    TextView mTextViewStockName;
    @BindView(chart)
    LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        List<String[]> stockData = new ArrayList<>();
        List<Entry> entries = new ArrayList<Entry>();

        if(intent.hasExtra(getResources().getString(R.string.intent_symbol)) && intent.hasExtra(getResources().getString(R.string.intent_history))){
            mTextViewStockName.setText(intent.getStringExtra(getResources().getString(R.string.intent_symbol)));
            mColumnHistory = intent.getStringExtra(getResources().getString(R.string.intent_history));
            stockData = splitMillisecondsAndPrice(mColumnHistory);
        }


        if(stockData != null){
            int counter = (stockData.size() - 1);
            for(int i=0; i < stockData.size(); i++){
                float price = Float.valueOf(String.valueOf(Math.round(Float.valueOf(stockData.get(counter)[1]) * 100.0) / 100.0));
                long milliseconds = Long.valueOf(stockData.get(counter)[0]);

                entries.add(new Entry(i, price));
                counter--;
            }
        }

        LineDataSet dataSet = new LineDataSet(entries, getResources().getString(R.string.label_price_history));
        dataSet.setColors(ContextCompat.getColor(this, R.color.colorAccent));
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.white));
        dataSet.setValueTextSize(getResources().getDimension(R.dimen.text_size_extra_small));

        Legend legend = mChart.getLegend();
        legend.setTextColor(ContextCompat.getColor(this, R.color.white));
        legend.setTextSize(getResources().getDimension(R.dimen.text_size_tiny));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ContextCompat.getColor(this, R.color.white));
        leftAxis.setTextSize(getResources().getDimension(R.dimen.text_size_tiny));

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(ContextCompat.getColor(this, R.color.white));
        rightAxis.setTextSize(getResources().getDimension(R.dimen.text_size_tiny));

        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);

        Description description = mChart.getDescription();
        description.setText("");


        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.invalidate();
    }

    private List<String[]> splitMillisecondsAndPrice(String input){
        List<String[]> output = new ArrayList<>();
        List<String> tempSplit = new ArrayList<>();
        if(input.contains(",")){
            tempSplit = Arrays.asList(input.split("\n"));
        }

        for(int i=0; i<tempSplit.size(); i++){
            output.add(tempSplit.get(i).split(", "));
        }

        return output;
    }


}
