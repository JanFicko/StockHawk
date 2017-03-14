package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
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

        if(intent.hasExtra(getResources().getString(R.string.intent_symbol)) && intent.hasExtra(getResources().getString(R.string.intent_history))){
            mTextViewStockName.setText(intent.getStringExtra(getResources().getString(R.string.intent_symbol)));
            mColumnHistory = intent.getStringExtra(getResources().getString(R.string.intent_history));
            Timber.d("COLUMN_HISTORY: %s", mColumnHistory);
            splitMillisecondsAndPrice(mColumnHistory);
        }

        List<Entry> entries = new ArrayList<Entry>();

        for (int i=0; i<10; i++) {

            // turn your data into Entry objects
            entries.add(new Entry(i, i));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Day");
        dataSet.setColor(ContextCompat.getColor(this, R.color.colorAccent));
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.white));


        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.invalidate();


    }

    private int millisecondsToDay(long milliseconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    private void splitMillisecondsAndPrice(String input){
        /*String temp = "input";
        String parts[] = temp.split("\n");
        ArrayList<String> listItems = new ArrayList<String>();

        for (int i = 0; i < parts.length; i =i+2) {
            listItems.add(parts[i]+", "+parts[i+1]);

        }
             *//*Below loop is just to verify if your list contains correct items, Printing logs*//*
        for (int i = 0; i < listItems.size(); i++) {

            Timber.d("item = %s", listItems.get(i));
        }*/



        List<String[]> output = new ArrayList<>();
        List<String> tempSplit = new ArrayList<>();
        if(input.contains(",")){
            tempSplit = Arrays.asList(input.split("\n"));
        }

        for(int i=0; i<tempSplit.size(); i++){
            output.add(tempSplit.get(i).split(", "));
        }

        for(int j=0; j<output.size(); j++){
            double price = Math.round(Double.valueOf(output.get(j)[1]) * 100.0) / 100.0;
            Timber.d("Milliseconds: " + String.valueOf(Long.valueOf(output.get(j)[0])) + " Price: " + price);
        }


    }


}
