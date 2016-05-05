package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class UserTestHelper {
    DBHelper helper;

    public final static String CREATE_USERTEST_TABLE="Create Table IF NOT EXISTS tb_UserTest(" +
            "_id Integer primary key,TID Integer references tb_Testin(_id),Type Integer,Question Integer,UAnswer text,Flag Integer);";

    public UserTestHelper(Context context)
    {
        helper=new DBHelper(context);
    }
}
