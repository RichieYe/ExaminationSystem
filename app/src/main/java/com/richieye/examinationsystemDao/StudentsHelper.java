package com.richieye.examinationsystemDao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.richieye.examinationsystemModel.TStudents;

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
    }

    public Map<String,String> getStudentForID(String ID)
    {
        return null;
    }

    public TStudents Login(List<Map<String,String>> params)
    {
        Cursor myCursor=helper.Select("tb_Students",params);
        TStudents tStudent=null;

        if(myCursor.moveToFirst())
        {
            tStudent=new TStudents();
            tStudent.setID(myCursor.getInt(myCursor.getColumnIndex("_id")));
            tStudent.setNo(myCursor.getString(myCursor.getColumnIndex("No")));
            tStudent.setUserName(myCursor.getString(myCursor.getColumnIndex("UserName")));
            tStudent.setCID(myCursor.getInt(myCursor.getColumnIndex("CID")));
            tStudent.setPassword(myCursor.getString(myCursor.getColumnIndex("password")));
            tStudent.setGender(myCursor.getString(myCursor.getColumnIndex("gender")));
            tStudent.setPhone(myCursor.getString(myCursor.getColumnIndex("phone")));
            tStudent.setAddress(myCursor.getString(myCursor.getColumnIndex("address")));
            tStudent.setLocalPath(myCursor.getString(myCursor.getColumnIndex("localpath")));
            tStudent.setServicePath(myCursor.getString(myCursor.getColumnIndex("servicepath")));
        }
        return tStudent;
    }
}
