package com.superkorsuk.happybaby;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by 1001078 on 2016. 9. 19..
 */
public class AlarmController {
    AlarmManager alarmManager;
    Context context;

    AlarmController(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setTimerForOnetime(Activity targetActivity, int timerSeconds) {
        Log.d("ALARM", "One time AlarmController is set.");

        // alarm fire 됐을때 할일. completion handler 처리 필요
        Intent intent = new Intent(this.context, AlarmReceiver.class);
        intent.putExtra("Extra", "test String");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, 0);

        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + (timerSeconds * 100), pendingIntent);
    }

    public void setTimerForRepeat(Activity targetActivity, int timerSeconds, int intervalTime) {
        Log.d("ALARM", "Repeat AlarmController is set.");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, timerSeconds);

        // alarm fire 됐을때 할일. completion handler 처리 필요
        Intent intent = new Intent(this.context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, 0);

        this.alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), intervalTime * 100, pendingIntent);

    }

    public void removeAlarm(PendingIntent pendingIntent) {

        this.alarmManager.cancel(pendingIntent);
    }


}
