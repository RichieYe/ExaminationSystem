package com.richieye.examinationOperator;

import android.content.Context;

import com.richieye.examinationsystemDao.UserTestHelper;
import com.richieye.examinationsystemJson.UserTestForJson;

/**
 * Created by RichieYe on 2016/6/21.
 */

public class UserTestOperator {
    private Context mContext;
    //private UserTestForJson userTestForJson;
    private UserTestHelper helper;

    public UserTestOperator(Context mContext)
    {
        this.mContext=mContext;
        //userTestForJson=new UserTestForJson(mContext);
        helper=new UserTestHelper(mContext);
    }
}
