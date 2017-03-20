package com.udacity.stockhawk.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Stock;

import java.util.ArrayList;
import java.util.List;


public class StockHawkWidgetDataProvider implements RemoteViewsService.RemoteViewsFactory{

    private List<Stock> collection = new ArrayList<>();
    private Context context;
    private Intent intent;
    private Cursor cursor;

    public StockHawkWidgetDataProvider(Context context, Intent intent, Cursor cursor) {
        this.context = context;
        this.intent = intent;
        this.cursor = cursor;
    }

    private void initializeData(){
        while(cursor.moveToNext()){
            Stock stock = new Stock(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));

            collection.add(stock);
        }
    }

    @Override
    public void onCreate() {
        initializeData();
    }

    @Override
    public void onDataSetChanged() {
        initializeData();
    }

    @Override
    public void onDestroy() {
        collection.clear();
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(),
                R.layout.widget_list_item);

        Stock stock = collection.get(position);

        remoteView.setTextViewText(R.id.widget_tv_name, stock.getSymbolName());
        remoteView.setTextViewText(R.id.widget_tv_price, "$" + stock.getStockPrice());

        float stockChange = Float.valueOf(stock.getStockChange());

        if (stockChange > 0) {
            remoteView.setTextColor(R.id.widget_tv_price_change, ContextCompat.getColor(context, R.color.material_green_700));
        } else {
            remoteView.setTextColor(R.id.widget_tv_price_change, ContextCompat.getColor(context, R.color.material_red_700));
        }

        remoteView.setTextViewText(R.id.widget_tv_price_change, "(" + stock.getStockChange() + ")");


        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
