package com.richieye.examinationOperator;

import android.content.Context;
import android.util.Log;

import com.richieye.examinationsystemDao.StudentsHelper;
import com.richieye.examinationsystemJson.StudentesForJson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public boolean Login(Map<String,String> params)
    {
        String strMsg=sJson.Login(params);
        return false;
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
}
