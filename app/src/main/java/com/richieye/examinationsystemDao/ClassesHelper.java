package com.richieye.examinationsystemDao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.richieye.examinationsystemJson.ClassesForJson;
import com.richieye.examinationsystemModel.TClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class ClassesHelper {
    DBHelper helper;

    public final static String CREATE_CLASSES_TABLE="Create Table IF NOT EXISTS tb_Classes(_id Integer Primary Key,ClassName Text);";

    public ClassesHelper(Context context)
    {
        helper=new DBHelper(context);
    }

    public void InsertForService(List<Map<String,String>> list)
    {
        helper.Replace("tb_Classes",list);
    }

    public List<Map<String,String>> getClasses()
    {
        List<Map<String,String>>list=new ArrayList<Map<String,String>>();

        Cursor myCursor=helper.Select("tb_Classes",0);

        if(myCursor!=null&&myCursor.getCount()>0)
        {
            myCursor.moveToFirst();

            for(int i=0;i<myCursor.getCount();i++)
            {
                myCursor.moveToPosition(i);
                Map<String,String> map=new HashMap<String,String>();
                map.put("ID",myCursor.getInt(myCursor.getColumnIndex("_id"))+"");
                map.put("ClassName",myCursor.getString(myCursor.getColumnIndex("ClassName")));

                list.add(map);
            }
        }
        return list;
    }

}
