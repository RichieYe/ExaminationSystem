package com.richieye.examinationsystemDao;

import android.content.Context;

import com.richieye.examinationSystemIO.PreferencesOperator;
import com.richieye.examinationsystemJson.UserTestForJson;
import com.richieye.examinationsystemNetwork.NetWorkOperator;

import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class UserTestHelper {
    DBHelper helper;
    UserTestForJson userTestForJson;

    private Context mContext;

    private boolean isNetWork;

    public final static String CREATE_USERTEST_TABLE="Create Table IF NOT EXISTS tb_UserTest(" +
            "_id Integer primary key,TID Integer references tb_Testin(_id),Type Integer,Question Integer,UAnswer text,Flag Integer);";

    public UserTestHelper(Context context)
    {
        mContext=context;
        helper=new DBHelper(context);
        userTestForJson=new UserTestForJson(context);
        isNetWork= PreferencesOperator.isNetworkState(mContext);
    }

    public List<Map<String,String>> getTestByTID(String strTID)
    {
        List<Map<String,String>> list=null;

        if(isNetWork&& NetWorkOperator.isNetworkAvailable(mContext))
        {
            //return userTestForJson.getTestByTID(strTID);
        }

        return null;
    }
}
