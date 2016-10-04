package com.superkorsuk.happybaby.util;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.superkorsuk.happybaby.views.GetPhotoActivity;

/**
 * Created by 1001078 on 2016. 9. 19..
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.d("ALARM", "Alarm Received");
//        Log.d("ALARM", intent.getStringExtra("Extra"));
        Toast.makeText(context, "Alarm Received!", Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent(context, GetPhotoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        NotificationRegisterUtil notificationRegister = new NotificationRegisterUtil(context);
        notificationRegister.notify(notificationRegister.makeSimpleNotification(pendingIntent, "NOTI Title", "Subject message"));
    }
}
