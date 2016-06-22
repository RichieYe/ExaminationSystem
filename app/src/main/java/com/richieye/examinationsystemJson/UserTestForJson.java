package com.richieye.examinationsystemJson;

import android.content.Context;

import com.richieye.examinationSystemIO.PreferencesOperator;

import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/6/21.
 */

public class UserTestForJson {

    final static String CLASSES_WEB_SERVICE_NAME="Classes_Operator.asmx";

    private Context mContext;



    public UserTestForJson(Context mContext)
    {
        this.mContext=mContext;

    }

    public List<Map<String,String>> getTestByTID(int TID)
    {
        return null;
    }
}
