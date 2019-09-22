package com.cagataykolus.orderapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class contains configuration settings about remembering login state
 */
public class AppConfig {
    private static SharedPreferences sharedPreferences;

    private static final String IS_REMEMBER_ACTIVE = "IS_REMEMBER_ACTIVE";
    private static final boolean IS_REMEMBER_ACTIVE_DEFAULT = false;

    public static void init(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    // Default remember status is false
    public static boolean getRememberStatus() {
        return sharedPreferences.getBoolean(IS_REMEMBER_ACTIVE, IS_REMEMBER_ACTIVE_DEFAULT);
    }

    public static void setRememberStatus(boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(IS_REMEMBER_ACTIVE, value);
        prefsEditor.apply();
    }
}