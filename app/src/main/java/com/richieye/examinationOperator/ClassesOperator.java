package com.richieye.examinationOperator;

import android.content.Context;

import com.richieye.examinationsystemDao.ClassesHelper;
import com.richieye.examinationsystemJson.ClassesForJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/5/6.
 */
public class ClassesOperator {
    ClassesForJson cJson;
    ClassesHelper helper;

    public ClassesOperator(Context context)
    {
        cJson=new ClassesForJson(context);
        helper=new ClassesHelper(context);
    }

    public void getDataForServer()
    {
        List<Map<String,String>> list=cJson.getClasses(ClassesForJson.CLASSES_OPERATOR_GETALLCLASSES,null);

        helper.InsertForService(list);
    }

    public String getClassNameByID(int CID)
    {
        return helper.getClassNameForID(CID);
    }

    public List<Map<String,String>> getClasses()
    {
        return helper.getClasses(0);
    }
}
