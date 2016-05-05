package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class TestingHelper {
    DBHelper helper;
    public final static String CREATE_TESTING_TABLE="Create Table IF NOT EXISTS tb_Testing(" +
            "_id Integer Primary Key,SID Integer references tb_Students(_id),TestDate text,Flag Integer,StartTime text,EndTime text);";

    public TestingHelper(Context context)
    {
        helper=new DBHelper(context);
    }

}
