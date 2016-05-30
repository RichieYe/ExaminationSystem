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

        Map<String,String> map=getStudentForJSON(strMsg);

        InsertStudentToDB(map);

        return getStudentForMap(map);
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
        Log.e("UserOperator","1111111");
        TStudents tStudents=null;
        if(params!=null)
        {
            tStudents=new TStudents();
            tStudents.setID(Integer.parseInt(params.get("_id")));
            tStudents.setNo(params.get("No"));
            tStudents.setUserName(params.get("UserName"));
            tStudents.setCID(Integer.parseInt(params.get("CID")));
            tStudents.setPassword(params.get("password"));
            tStudents.setGender(params.get("gender"));
            tStudents.setPhone(params.get("phone"));
            tStudents.setAddress(params.get("address"));
            tStudents.setLocalPath(params.get("localpath"));
            tStudents.setServicePath(params.get("servicepath"));
        }

        return tStudents;
    }

    public Map<String,String> getStudentForJSON(String strJson)
    {
        Map<String,String> map=null;
        try
        {
            JSONArray jsonArray=new JSONArray(strJson);
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            map=new HashMap<String, String>();
            map.put("_id",jsonObject.getString("ID"));
            map.put("No",jsonObject.getString("NO"));
            map.put("UserName",jsonObject.getString("UserName"));
            map.put("CID",jsonObject.getString("CID"));
            map.put("password",jsonObject.getString("Password"));
            map.put("gender",jsonObject.getString("Gender"));
            map.put("phone",jsonObject.getString("Phone"));
            map.put("address",jsonObject.getString("Address"));
            map.put("localpath","");
            map.put("servicepath","");
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return map;
    }
}
