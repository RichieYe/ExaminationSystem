package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class JudgmentHelper {
    DBHelper helper;

    public final static String CREATE_JUDGMENT_TABLE="Create Table IF NOT EXISTS tb_Judgment(" +
            "_id Integer Primary Key,Content text,Answer text);";

    public JudgmentHelper(Context context)
    {
        helper=new DBHelper(context);
    }

}
