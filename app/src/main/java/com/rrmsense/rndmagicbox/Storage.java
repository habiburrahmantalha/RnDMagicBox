package com.rrmsense.rndmagicbox;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Talha on 3/25/2017.
 */

public class Storage {

    public static void setDevice(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("keherman", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value).apply();
    }
    public static String getDevice(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("keherman", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

}
