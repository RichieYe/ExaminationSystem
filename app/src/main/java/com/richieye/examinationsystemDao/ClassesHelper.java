package com.richieye.examinationsystemDao;

import android.content.Context;
import android.util.Log;

import com.richieye.examinationsystemJson.ClassesForJson;
import com.richieye.examinationsystemModel.TClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/15.
 */

public class ClassesHelper {
    DBHelper helper;

    public final static String CREATE_CLASSES_TABLE="Create Table IF NOT EXISTS tb_Classes(_id Integer Primary Key,ClassName Text);";

    public ClassesHelper(Context context)
    {
        helper=new DBHelper(context);
    }

    public void InsertForService(List<Map<String,String>> list)
    {
        List<Map<String,String>> params=new ArrayList<Map<String,String>>();

        if(list!=null&&!list.isEmpty())
        {
           params.addAll(list);
        }

        helper.Replace("tb_Classes",params);

        Log.e("ClassesHelper","插入成功！！");

        /*
        if(list!=null&&!list.isEmpty())
        {
            for(TClasses tc:list)
            {
                Map<String,String> map=new HashMap<String,String>();
                map.put("_id",tc.getID()+"");
                map.put("ClassName",tc.getClassName());

                params.add(map);
            }

            helper.Replace("tb_Classes",params);
            Log.e("ClassesHelper","插入成功！！");
        }
        */
    }

}
