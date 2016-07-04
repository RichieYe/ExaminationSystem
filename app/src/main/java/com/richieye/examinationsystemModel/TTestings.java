package com.richieye.examinationsystemModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RichieYe on 2016/7/1.
 */

public class TTestings {

    private String _iD;

    public String getID() {
        return _iD;
    }

    public void setID(String strID) {
        this._iD = strID;
    }

    private String _sID;

    public String getSID() {
        return this._sID;
    }

    public void setSID(String strSID) {
        this._sID = strSID;
    }

    private String _testDate;

    public String getTestDate() {
        return this._testDate;
    }

    public void setTestDate(String strTestDate)
    {
        this._testDate=getTestDate(strTestDate);
    }

    private int _flag;

    public String getFlag()
    {
        return getFlag(_flag+"");
    }

    public void setFlag(int iFlag)
    {
        this._flag=iFlag;
    }

    private String _startTime;

    public String getStartTime()
    {
        if("".equals(this._startTime.trim()))
        {
            return "未指定";
        }

        return getFormatTime(this._startTime);
    }

    public void setStartTime(String strStartTime)
    {
        this._startTime=strStartTime;
    }

    private String _endTime;

    public String getEndTime()
    {
        if ("".equals(this._endTime.trim())) {
            return "未指定";
        }

        return getFormatTime(this._endTime);
    }

    public void setEndTime(String strEndTime)
    {
        this._endTime=strEndTime;
    }

    private String getTestDate(String strDate)
    {

        String strMsg="";
        try {
            Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(strDate);
            strMsg=new SimpleDateFormat("yyyy年MM月dd日").format(date);
        }catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        return strMsg;
    }

    private String getFlag(String strFlag)
    {
        String strMsg="未考";
        if("0".equals(strFlag.trim()))
        {
            strMsg="未考";
        }else if("1".equals(strFlag.trim()))
        {
            strMsg="正在考试";
        }else if("2".equals(strFlag.trim()))
        {
            strMsg="已考";
        }else
        {
            strMsg="有误！";
        }
        return strMsg;
    }

    private String getFormatTime(String strTime)
    {
        String strMsg="未指定";
        if(!"".equals(strTime.trim())) {

            return strTime;
        }

        return strMsg;
    }

}
