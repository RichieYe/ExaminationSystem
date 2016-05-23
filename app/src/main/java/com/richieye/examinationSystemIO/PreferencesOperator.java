package com.richieye.examinationSystemIO;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RichieYe on 2016/5/23.
 */
public class PreferencesOperator {
    private final static String FILE_NAME="AppSets";

    public static void saveNetworkState(Context context,boolean isNetwork)
    {
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isNetWork",isNetwork);
        editor.commit();
    }

    public static boolean isNetworkState(Context context)
    {
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND);
        return sp.getBoolean("isNetWork",true);
    }

}
