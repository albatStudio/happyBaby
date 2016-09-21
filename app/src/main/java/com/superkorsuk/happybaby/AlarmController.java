package com.superkorsuk.happybaby;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by 1001078 on 2016. 9. 19..
 */
public class AlarmController {
    AlarmManager alarmManager;
    Context context;
    Intent intent;
    PendingIntent pendingIntent;

    AlarmController(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.intent = new Intent(this.context, AlarmReceiver.class);
        this.pendingIntent = PendingIntent.getBroadcast(this.context, 1234567, intent, 0);
            // timer 여러개일때를 대비하여 id를 hashCode로
    }

    AlarmController(Context context, PendingIntent pendingIntent) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.pendingIntent = pendingIntent;
    }

    public void setTimerForOnetime(int timerSeconds) {
        Log.d("ALARM", "One time AlarmController is set.");

        // alarm fire 됐을때 할일. completion handler 처리 필요
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (timerSeconds * 1000), pendingIntent);
    }

    public void setTimerForRepeat(Calendar firstTime, int intervalSeconds) {
        Log.d("ALARM", "Repeat AlarmController is set.");

        // android 5.1부터 최소 interval 이 60초 이상이어야 함.
        // alarm fire 됐을때 할일. completion handler 처리 필요
        this.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  firstTime.getTimeInMillis(), intervalSeconds * 1000, pendingIntent);
        Log.d("ALARM", pendingIntent.toString());
    }

    public void removeAlarm() {
        // cancel 불안정.
        Log.d("ALARM", pendingIntent.toString());
        this.alarmManager.cancel(pendingIntent);


    }

}
