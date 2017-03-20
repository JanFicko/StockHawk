package com.udacity.stockhawk.widget;

import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.data.Contract;

import timber.log.Timber;

public class StockHawkWidgetService extends RemoteViewsService{
    private static final String[] STOCKHAWK_COLUMNS = {
            Contract.Quote.COLUMN_SYMBOL,
            Contract.Quote.COLUMN_PRICE,
            Contract.Quote.COLUMN_PERCENTAGE_CHANGE
    };

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Cursor data = getContentResolver().query(
                Contract.Quote.URI,
                STOCKHAWK_COLUMNS,
                null,
                null,
                Contract.Quote.COLUMN_PRICE + " DESC");

        return new StockHawkWidgetDataProvider(this.getApplicationContext(), intent, data);
    }
}
