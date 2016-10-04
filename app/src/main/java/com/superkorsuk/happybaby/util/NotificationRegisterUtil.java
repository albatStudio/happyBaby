package com.superkorsuk.happybaby.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.superkorsuk.happybaby.R;

/**
 * Created by 1001078 on 2016. 9. 19..
 */
public class NotificationRegisterUtil {
    Context context;
    NotificationManager notificationManager;

    public NotificationRegisterUtil(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Notification makeSimpleNotification(PendingIntent pendingIntent, String title, String text) {

        Notification notification = new Notification.Builder(this.context)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_menu_share)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setTicker("new Notification (Ticker)")
//                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .build();

        return  notification;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public int notify(Notification notification) {
        int id = (int)System.currentTimeMillis();
        notificationManager.notify(id, notification);
        Log.d("NOTIFICATION", "id : " + id);
        return id;
    }

    public void remove(int id) {
        notificationManager.cancel(id);
    }

}
