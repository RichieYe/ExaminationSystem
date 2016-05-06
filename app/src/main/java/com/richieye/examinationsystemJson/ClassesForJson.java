package com.richieye.examinationsystemJson;

import android.content.Context;

import com.richieye.examinationsystemDao.ClassesHelper;
import com.richieye.examinationsystemModel.TClasses;
import com.richieye.examinationsystemNetwork.MyThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by RichieYe on 2016/4/14.
 */
public class ClassesForJson
{
    final static String CLASSES_WEB_SERVICE_NAME="Classes_Operator.asmx";

    public static String CLASSES_OPERATOR_GETALLCLASSES="GetAllClasses";

    boolean isNetwork=true;

    Context context;

    public ClassesForJson(Context context)
    {
        this.context=context;
        isNetwork=true;
    }

    public ClassesForJson(Context context,boolean isNetwork)
    {
        this.context=context;
        this.isNetwork=isNetwork;
    }

    public String GetJSonString(String strMethodName,Map<String,String> params)
    {
        String strJSon="";
        ExecutorService exs= Executors.newCachedThreadPool();
        Future<String> future=exs.submit(new MyThread(CLASSES_WEB_SERVICE_NAME,strMethodName,params));
        try {
           strJSon=future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       return strJSon;
    }

    public List<Map<String,String>> getClasses(String strJson)
    {
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();

        try {
            JSONArray array = new JSONArray(strJson);
            for(int i=0;i<array.length();i++)
            {
                JSONObject obj=array.getJSONObject(i);
                Map<String,String>map=new HashMap<String,String>();
                map.put("ID",obj.getString("Id"));
                map.put("ClassName",obj.getString("ClassName"));
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Map<String,String>> getClasses(String strMethodName,Map<String,String> params)
    {
        String strJSon=GetJSonString(strMethodName,params);
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();

        try {
            JSONArray array = new JSONArray(strJSon);
            for(int i=0;i<array.length();i++)
            {
                JSONObject obj=array.getJSONObject(i);
                Map<String,String>map=new HashMap<String,String>();
                map.put("_id",obj.getString("Id"));
                map.put("ClassName",obj.getString("ClassName"));
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       // InsertForService(list);
        return list;
    }


    private void InsertForService(final List<Map<String,String>> list)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TClasses> tcList=new ArrayList<TClasses>();
                for(Map<String,String>map:list)
                {
                    TClasses tc=new TClasses();
                    tc.setID(Integer.parseInt(map.get("ID")));
                    tc.setClassName(map.get("ClassName"));
                    tcList.add(tc);
                }
                //new ClassesHelper(context).InsertForService(tcList);
            }
        }).start();
    }
}
