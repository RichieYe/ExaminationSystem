package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class ProgramHelper {
    DBHelper helper;

    public final static String CREATE_PROGRAM_TABLE="Create Table IF NOT EXISTS tb_Program(" +
            "_id Integer Primary Key,Content text,Answer text);";

    public ProgramHelper(Context context)
    {
        helper=new DBHelper(context);
    }

}
