package com.superkorsuk.happybaby.util;

import android.content.Context;
import android.content.SharedPreferences;

public class BabyUtil {

    public static int getCurrentBabyId(SharedPreferences statusPref) {

        return statusPref.getInt("selected_baby_id", 0);
    }

    public static void setCurrentBabyid(Context context, int newId) {
        SharedPreferences pref = context.getSharedPreferences("status", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("selected_baby_id", newId);
        editor.commit();
    }

}
