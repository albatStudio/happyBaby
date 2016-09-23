package com.superkorsuk.happybaby.controllers;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import com.superkorsuk.happybaby.R;

/**
 * Created by 1001078 on 2016. 9. 19..
 */
public class NotificationController {
    Context context;
    NotificationManager notificationManager;

    public NotificationController(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Notification makeSimpleNotification(PendingIntent pendingIntent, String title, String text) {
        Notification notification = new Notification.Builder(this.context)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_menu_share)
                .setAutoCancel(true)
                .build();

        return  notification;
    }

    public int notify(Notification notification) {
        int id = (int)System.currentTimeMillis();
        notificationManager.notify(id, notification);

        return id;
    }


}
