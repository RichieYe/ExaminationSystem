package com.richieye.examinationOperator;

import android.content.Context;
import android.util.Log;

import com.richieye.examinationsystemDao.UserTestHelper;
import com.richieye.examinationsystemJson.UserTestForJson;
import com.richieye.examinationsystemModel.TUserTest;
import com.richieye.examinationsystemModel.TUserTest1;

import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/6/21.
 */

public class UserTestOperator {
    private Context mContext;
    private UserTestForJson userTestForJson;
    private UserTestHelper helper;

    public UserTestOperator(Context mContext)
    {
        this.mContext=mContext;
        userTestForJson=new UserTestForJson(mContext);
        helper=new UserTestHelper(mContext);
    }

    public List<List<TUserTest>> getTestByTID(String strTID)
    {
        return userTestForJson.getTestByTID(strTID);
    }
}
