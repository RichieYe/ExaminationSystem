package com.richieye.examinationOperator;

import android.content.Context;
import android.util.Log;

import com.richieye.examinationsystemDao.StudentsHelper;
import com.richieye.examinationsystemJson.StudentesForJson;
import com.richieye.examinationsystemModel.TStudents;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/5/10.
 */
public class UserOperator {
    private Context context;
    private boolean isNetWork=true;

    StudentesForJson sJson;

    StudentsHelper helper;

    public UserOperator(Context context)
    {
        this.context=context;
        sJson=new StudentesForJson(context);
        helper=new StudentsHelper(context);
    }

    public boolean checkStudentNo(String strNo)
    {
        return  sJson.checkStudentNo(strNo);
    }

    public TStudents getStudentForID(Map<String,String> params)
    {
        String strMsg=sJson.getStudentForID(params);
        Log.e("UserOperator",strMsg);
        TStudents tStudents=null;
        try{

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        String CREATE_STUDENT_TABLE="Create Table IF NOT EXISTS tb_Students(" +
                "_id Integer Primary Key,No text,UserName text,CID Integer references tb_Classes(_id)," +
                "password text,gender text,phone text,address text,localpath text,servicepath text);";
        return tStudents;
    }

    public TStudents Login(Map<String,String> params)
    {
        String strMsg=sJson.Login(params);
        TStudents tStudents=null;
        Log.e("UserOperator",strMsg);
        try {
            JSONArray jsonArray = new JSONArray(strMsg);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            if("OK".equals(jsonObject.getString("Flag").trim()))
            {
                Map<String,String> map=new HashMap<String,String>();
                map.put("id",jsonObject.getString("ID").trim());
                tStudents=getStudentForID(map);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //Log.e("UserOperator",strMsg);
        return tStudents;
    }

    public boolean InsertStudent(Map<String,String> params)
    {
        String strMsg=sJson.InsertStudent(params);
        Log.e("UserOperator",strMsg);

        if("Error".equals(strMsg))
        {
            return false;
        }else
        {
            try {
                JSONArray jsonArray = new JSONArray(strMsg);
                JSONObject jsonObject=jsonArray.getJSONObject(0);
                if("OK".equals(jsonObject.getString("Flag")))
                {
                    params.put("_id",jsonObject.getString("ID"));
                    InsertStudentToDB(params);
                    return true;
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void InsertStudentToDB(final Map<String,String> params)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Map<String,String>> list=new ArrayList<Map<String, String>>();
                list.add(params);
                helper.InsertStudent(list);
            }
        }).start();
    }

    public TStudents getStudentForMap(Map<String,String>params)
    {
        return null;
    }

    public Map<String,String> getStudentForJSON(String strJson)
    {
        return null;
    }
}
