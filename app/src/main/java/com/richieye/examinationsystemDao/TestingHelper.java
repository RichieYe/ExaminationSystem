package com.richieye.examinationsystemDao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.richieye.examinationSystemIO.PreferencesOperator;
import com.richieye.examinationsystemJson.TestingForJson;
import com.richieye.examinationsystemNetwork.NetWorkOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class TestingHelper {
    DBHelper helper;
    private boolean isNetWork;
    private Context mContext;

    private TestingForJson tJson;

    public final static String CREATE_TESTING_TABLE="Create Table IF NOT EXISTS tb_Testing(" +
            "_id Integer Primary Key,SID Integer references tb_Students(_id),TestDate text,Flag Integer,StartTime text,EndTime text);";

    public TestingHelper(Context context)
    {
        this.mContext=context;
        helper=new DBHelper(mContext);
        tJson=new TestingForJson(mContext);
        isNetWork= PreferencesOperator.isNetworkState(mContext);
    }

    public List<Map<String,String>> getTestingByID(int UID,int Flag)
    {
        List<Map<String,String>>list=null;

        if(isNetWork&& NetWorkOperator.isNetworkAvailable(mContext))
        {
            List<Map<String,String>>temp=tJson.getAllTestByUID(UID);
            Log.e("TestingHelper2",temp.size()+"");
            InsertDBForService(temp);
        }
        List<Map<String,String>>params=new ArrayList<Map<String, String>>() ;
        Map<String,String> map=new HashMap<>();
        map.put("SID",UID+"");
        params.add(map);
        if(Flag!=-1)
        {
            map=new HashMap<>();
            map.put("Flag",Flag+"");
            params.add(map);
        }
        Cursor myCursor=helper.Select("tb_Testing",params);
        list=convertListForCursor(myCursor);
        return list;
    }

    public void InsertDBForService(final List<Map<String,String>> params)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                helper.Replace("tb_Testing",params);
                Log.e("TestingHelper","插入成功！！");
            }
        }).start();
    }

    public List<Map<String,String>> convertListForCursor(Cursor cursor)
    {
        List<Map<String,String>> list=null;
        if(cursor.moveToFirst())
        {
            list=new ArrayList<>();

            for(int i=0;i<cursor.getCount();i++)
            {
                Map<String,String> map=new HashMap<>();
                map.put("_id",cursor.getString(cursor.getColumnIndex("_id")));
                map.put("SID",cursor.getString(cursor.getColumnIndex("SID")));
                map.put("TestDate",cursor.getString(cursor.getColumnIndex("TestDate")));
                map.put("Flag",cursor.getString(cursor.getColumnIndex("Flag")));
                map.put("StartTime",cursor.getString(cursor.getColumnIndex("StartTime")));
                map.put("EndTime",cursor.getString(cursor.getColumnIndex("EndTime")));
                list.add(map);
                cursor.moveToNext();
            }
        }
        return list;
    }

}
