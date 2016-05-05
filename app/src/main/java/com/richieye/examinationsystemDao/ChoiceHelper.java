package com.richieye.examinationsystemDao;

import android.content.Context;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class ChoiceHelper {
    DBHelper helper;

    public final static String CREATE_CHOICE_TABLE="Create Table IF NOT EXISTS tb_Choice(" +
            "_id Integer Primary Key,Content text,AnswerA text,AnswerB text,AnswerC text,AnswerD text,Answer text);";

    public ChoiceHelper(Context context)
    {
        helper=new DBHelper(context);
    }

}
