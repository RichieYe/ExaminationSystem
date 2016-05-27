package com.richieye.examinationsystemDao;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class StudentsHelper
{
    DBHelper helper;

    public final static String CREATE_STUDENT_TABLE="Create Table IF NOT EXISTS tb_Students(" +
            "_id Integer Primary Key,No text,UserName text,CID Integer references tb_Classes(_id)," +
            "password text,gender text,phone text,address text,localpath text,servicepath text);";

    public StudentsHelper(Context context)
    {
        helper=new DBHelper(context);
    }

    public void InsertStudent(List<Map<String,String>> list)
    {
        helper.Replace("tb_Students",list);
        Log.e("StudentsHelper","11111111111");
    }
}
