package com.richieye.examinationSystemIO;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

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

    public static void savaUserInfo(Context context,int iCid,String strUserNo,String strUserName,String strPassword,boolean isSave)
    {
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("CID", iCid);
        editor.putString("UserNo", strUserNo);
        editor.putString("UserName", strUserName);
        editor.putString("Password", strPassword);
        editor.putBoolean("isSave", isSave);

        editor.commit();
    }

    public static Map<String,String> loadUserInfo(Context context)
    {
        SharedPreferences sp=context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND);
        Map<String,String> map=new HashMap<String, String>();
        if(sp.getBoolean("isSave",false))
        {
            map.put("CID",sp.getInt("CID",1)+"");
            map.put("UserNo",sp.getString("UserNo",""));
            map.put("UserName",sp.getString("UserName",""));
            map.put("Password",sp.getString("Password",""));

        }
        map.put("isSave",sp.getBoolean("isSave",false)+"");
        return map;
    }

}
