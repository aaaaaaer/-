package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    public static void putData(String key, String value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
        editor.apply();
    }

    public static void putData(String key,int value,Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
        editor.apply();
    }

    public static int getIntData(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config",0);
        return sharedPreferences.getInt(key,0);
    }

    public static String getStringData(String key ,Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config",0);
        return sharedPreferences.getString(key,"0");
    }
}
