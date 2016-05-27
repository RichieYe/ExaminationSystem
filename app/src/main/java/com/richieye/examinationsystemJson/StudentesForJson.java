package com.richieye.examinationsystemJson;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.richieye.examinationsystemNetwork.MyThread;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by RichieYe on 2016/5/23.
 */
public class StudentesForJson {
    final static String STUDENTS_WEB_SERVICE_NAME="User_Operator.asmx";
    public final static String STUDENT_OPERATOR_CHECKSTUDENTNO="CheckStudentNoForNo";
    public final static String STUDENT_OPERATOR_INSERT="Insert";

    private Context context;


    public StudentesForJson(Context context)
    {
        this.context=context;
    }

    public boolean checkStudentNo(String strNO)
    {
        boolean isExists=false;
        String strJson="";
        Map<String,String> params=new HashMap<String,String>();
        params.put("strNo",strNO);
        ExecutorService exs= Executors.newCachedThreadPool();
        Future<String> future=exs.submit(new MyThread(STUDENTS_WEB_SERVICE_NAME,STUDENT_OPERATOR_CHECKSTUDENTNO,params));
        try
        {
            strJson=future.get();
            JSONArray jsonArray=new JSONArray(strJson);
            Log.e("StudentsForJson",strJson);
            JSONObject jsonObject=jsonArray.getJSONObject(0);

            if("Yes".equals(jsonObject.get("Flag").toString().trim()))
            {
                isExists=true;
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
            Log.e("StudentsForJson",ex.getMessage());
            isExists=true;
        }
        return isExists;
    }

    public String InsertStudent(Map<String,String> params)
    {
        return OperatorNetWork(STUDENT_OPERATOR_INSERT,params);
    }

    public String OperatorNetWork(String strMethod,Map<String,String> params)
    {
        String strMsg="";

        ExecutorService exs= Executors.newCachedThreadPool();
        Future<String> future=exs.submit(new MyThread(STUDENTS_WEB_SERVICE_NAME,strMethod,params));
        try {
            strMsg = future.get();
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return "Error";
        }
        return strMsg;
    }

}
