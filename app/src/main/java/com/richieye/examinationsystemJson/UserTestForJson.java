package com.richieye.examinationsystemJson;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.richieye.examinationSystemIO.PreferencesOperator;
import com.richieye.examinationsystemModel.TUserTest;
import com.richieye.examinationsystemModel.TUserTest1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/6/21.
 */

public class UserTestForJson {

    final static String USERTEST_WEB_SERVICE_NAME="UserTest_Operator.asmx";
    public final static String USERTEST_OPERATOR_GETUSERTEST="GetUserTestForTID";

    private Context mContext;

    public UserTestForJson(Context mContext)
    {
        this.mContext=mContext;
    }

    public List<List<TUserTest>> getTestByTID(String strTID)
    {
        Map<String,String> params=new HashMap<>();
        params.put("TID","1");
        String strJson=getJsonString(USERTEST_OPERATOR_GETUSERTEST,params);
        List<List<TUserTest>>list=new ArrayList<List<TUserTest>>();
        List<TUserTest> list1=getListForJSon(strJson);
        //Log.e("777777",strJson);
        List<TUserTest> lstCompletion=new ArrayList<>();
        List<TUserTest> lstChoice=new ArrayList<>();
        List<TUserTest> lstMultiChoice=new ArrayList<>();
        List<TUserTest> lstJudgment=new ArrayList<>();
        List<TUserTest> lstProgram=new ArrayList<>();

        //TUserTest tUserTest=new TUserTest();

        for(int i=0;i<list1.size();i++)
        {
            TUserTest tUserTest=list1.get(i);
            switch (tUserTest.getType())
            {
                case 0:
                    lstCompletion.add(tUserTest);
                    break;
                case 1:
                    lstChoice.add(tUserTest);
                    break;
                case 2:
                    lstMultiChoice.add(tUserTest);
                    break;
                case 3:
                    lstJudgment.add(tUserTest);
                    break;
                case 4:
                    lstProgram.add(tUserTest);
                    break;
                default:
                    new Throwable("有异常！");
            }
        }
        //Log.e("UserTestForJson",strJson);

        list.add(lstCompletion);
        list.add(lstChoice);
        list.add(lstMultiChoice);
        list.add(lstJudgment);
        list.add(lstProgram);

        return list;
    }

    public String getJsonString(String strMethodName,Map<String,String> params)
    {
        return JSonOperator.getJSonStringForNetWork(USERTEST_WEB_SERVICE_NAME,strMethodName,params);
    }

    public List<TUserTest> getListForJSon(String strJson)
    {

        /*
        Gson gson=new Gson();
        List<TUserTest1> list=gson.fromJson(strJson,new TypeToken<List<TUserTest1>>(){}.getType());
        Log.e("555555",list.size()+"");
        return list;
        */

        List<TUserTest> list=new ArrayList<>();
        try{
            JSONArray array=new JSONArray(strJson);
            for(int i=0;i<array.length();i++)
            {
                JSONObject object=array.getJSONObject(i);
                TUserTest tUserTest=new TUserTest();
                tUserTest.setID(object.getInt("ID"));
                tUserTest.setTID(object.getInt("TID"));
                tUserTest.setType(object.getInt("Type"));
                tUserTest.setQuestion(object.getInt("Question"));
                tUserTest.setUAnswer(object.getString("UAnswer"));
                tUserTest.setTAnswer(object.getInt("TAnswer"));
                list.add(tUserTest);

            }

        }catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
}
