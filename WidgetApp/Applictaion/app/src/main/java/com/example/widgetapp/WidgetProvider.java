package com.example.widgetapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetID : appWidgetIds){
            Intent i = new Intent(context, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context,0,i,0);
            RemoteViews remoteviews = new RemoteViews(context.getPackageName(),R.layout.widget);
            remoteviews.setOnClickPendingIntent(R.id.widget_view,pi);
            appWidgetManager.updateAppWidget(appWidgetID,remoteviews);
        }
    }
}
