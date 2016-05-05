package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class StudentsHelper
{
    DBHelper helper;

    public final static String CREATE_STUDENT_TABLE="Create Table IF NOT EXISTS tb_Students(" +
            "_id Integer Primary Key,No text,Name text,CID Integer references tb_Classes(_id)," +
            "password text,gender text,phone text,address text,localpath text,servicepath text);";

    public StudentsHelper(Context context)
    {
        helper=new DBHelper(context);
    }
}
