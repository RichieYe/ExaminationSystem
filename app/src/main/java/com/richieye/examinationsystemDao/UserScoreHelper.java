package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class UserScoreHelper {
    DBHelper helper;

    public final static String CREATE_USERSCORE_TABLE="Create Table IF NOT EXISTS tb_UserScore(" +
            "_id Integer primary key,TID Integer references tb_Testing(_id),Score float Check(Score>=0));";

    public UserScoreHelper(Context context)
    {
        helper=new DBHelper(context);
    }

}
