package com.superkorsuk.happybaby.util;

import android.content.SharedPreferences;

public class BabyUtil {

    public static int getCurrentBabyId(SharedPreferences statusPref) {

        return statusPref.getInt("selected_baby_id", 0);
    }
}
