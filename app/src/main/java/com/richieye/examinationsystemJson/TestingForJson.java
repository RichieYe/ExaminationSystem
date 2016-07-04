package com.richieye.examinationsystemJson;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/6/16.
 */

public class TestingForJson {

    final static String TESTING_WEB_SERVICE_NAME="Testing_Operator.asmx";
    public static String TESTING_OPERATOR_GETALLTESTFORSID="GetAllTestForSID";
    public static String TESTING_OPERATOR_GETTESTFORID="GetTestForID";

    private Context mContent;

    public TestingForJson(Context mContent)
    {
        this.mContent=mContent;
    }

    public List<Map<String,String>> getAllTestByUID(int UID)
    {
        Map<String,String> params=new HashMap<>();
        params.put("SID",UID+"");
        String strMsg=JSonOperator.getJSonStringForNetWork(TESTING_WEB_SERVICE_NAME,TESTING_OPERATOR_GETALLTESTFORSID,params);
        return convertListForString(strMsg);
    }

    public List<Map<String,String>> convertListForString(String data)
    {
        List<Map<String,String>>list=null;
        if(!"".equals(data.trim()))
        {
            try {
                JSONArray jsonArray = new JSONArray(data);
                list=new ArrayList<>();

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Map<String,String> map=new HashMap<>();
                    map.put("_id",jsonObject.getString("ID"));
                    map.put("SID",jsonObject.getString("SID"));
                    map.put("TestDate",jsonObject.getString("TestDate"));
                    map.put("Flag",jsonObject.getString("Flag"));
                    map.put("StartTime",jsonObject.getString("StartTime"));
                    map.put("EndTime",jsonObject.getString("EndTime"));
                    list.add(map);
                }
            }catch (JSONException ex)
            {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public List<Map<String,String>> getTestByTID(String strTID)
    {
        Map<String,String> params=new HashMap<>();
        params.put("ID  ",strTID);
        String strMsg=JSonOperator.getJSonStringForNetWork(TESTING_WEB_SERVICE_NAME,TESTING_OPERATOR_GETTESTFORID,params);
        return convertListForString(strMsg);
    }

}
