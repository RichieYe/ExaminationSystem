package com.richieye.examinationOperator;

import android.content.Context;

import com.richieye.examinationsystemJson.StudentesForJson;

/**
 * Created by RichieYe on 2016/5/10.
 */
public class UserOperator {
    private Context context;
    private boolean isNetWork=true;

    StudentesForJson sJson;

    public UserOperator(Context context)
    {
        this.context=context;
        sJson=new StudentesForJson(context);
    }

    public boolean checkStudentNo(String strNo)
    {
        return  sJson.checkStudentNo(strNo);
    }

}
