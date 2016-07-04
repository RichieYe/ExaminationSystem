package com.richieye.examinationOperator;

import android.content.Context;
import android.util.Log;

import com.richieye.examinationSystemIO.PreferencesOperator;
import com.richieye.examinationsystemDao.TestingHelper;
import com.richieye.examinationsystemJson.TestingForJson;
import com.richieye.examinationsystemModel.TTestings;

import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/6/15.
 */

public class TestingOperator {
    private Context mContext;
    private TestingHelper helper;

    //private TestingForJson tJson;

    public TestingOperator(Context mContext)
    {
        this.mContext=mContext;
        helper=new TestingHelper(mContext);
        //tJson=new TestingForJson(mContext);
    }

    public List<Map<String,String>> getTestingByUID(int UID,int Flag)
    {
        return helper.getTestingByID(UID,Flag);
    }

    public TTestings getTestByTID(String strTID)
    {
        Map<String,String> map=helper.getTestByTID(strTID);

        TTestings tTestings=new TTestings();
        tTestings.setID(map.get("_id"));
        tTestings.setSID(map.get("SID"));
        tTestings.setTestDate(map.get("TestDate"));
        tTestings.setFlag(Integer.parseInt(map.get("Flag")));
        tTestings.setStartTime(map.get("StartTime"));
        tTestings.setEndTime(map.get("EndTime"));

        return tTestings;

    }
}
