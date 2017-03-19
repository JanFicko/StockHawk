package com.udacity.stockhawk.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.sync.QuoteSyncJob;
import com.udacity.stockhawk.ui.MainActivity;

import timber.log.Timber;

public class StockHawkWidgetIntentService extends IntentService {

    private static final String[] STOCKHAWK_COLUMNS = {
            Contract.Quote.COLUMN_SYMBOL,
            Contract.Quote.COLUMN_PRICE,
            Contract.Quote.COLUMN_PERCENTAGE_CHANGE
    };

    private static final int INDEX_SYMBOL = 0;
    private static final int INDEX_PRICE = 1;
    private static final int INDEX_CHANGE = 2;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public StockHawkWidgetIntentService() {
        super(StockHawkWidgetIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Timber.d("TUKAJ---");
        /*AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                StockHawkWidget.class));

        //String queryUri = Contract.Quote.getStockFromUri(null);


        for (int appWidgetId : appWidgetIds) {
            int layoutId = R.layout.stock_hawk_widget;
            RemoteViews views = new RemoteViews(getPackageName(), layoutId);


            // Create an Intent to launch MainActivity
            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }*/

    }
}
