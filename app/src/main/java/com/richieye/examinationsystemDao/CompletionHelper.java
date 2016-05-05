package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class CompletionHelper
{
    DBHelper helper;

    public final static String CREATE_COMPLETION_TABLE="Create Table IF NOT EXISTS tb_Completion(" +
            "_id Integer Primary Key,Content text,Count Integer,Answer text);";

    public CompletionHelper(Context context)
    {
        helper=new DBHelper(context);
    }
}
