package com.richieye.examinationAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richieye.examinationsystem.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/6/16.
 */

public class TestingAdapter extends BaseAdapter {
    List<Map<String,String>> list;
    Context mContent;

    TextView tvID,tvTestDate,tvTestFlag,tvStartTime,tvEndTime,tvItem;

    public TestingAdapter(Context mContent,List<Map<String,String>>params)
    {
        this.mContent=mContent;
        list=params;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout layout= (LinearLayout) LayoutInflater.from(mContent).inflate(R.layout.testing_main_item,null);
        tvID= (TextView) layout.findViewById(R.id.tvTestingMainItem_ID);
        tvTestDate= (TextView) layout.findViewById(R.id.tvTestingMainItem_Date);
        tvTestFlag= (TextView) layout.findViewById(R.id.tvTestingMainItem_Flag);
        tvStartTime= (TextView) layout.findViewById(R.id.tvTestingMainItem_StartTime);
        tvEndTime= (TextView) layout.findViewById(R.id.tvTestingMainItem_EndTime);
        tvItem= (TextView) layout.findViewById(R.id.tvTestingMainItem_Item);

        tvID.setText(list.get(position).get("_id").toString());
        tvTestDate.setText(getTestDate(list.get(position).get("TestDate").toString()));
        tvTestFlag.setText(getFlag(list.get(position).get("Flag").toString()));
        tvStartTime.setText(getFormatTime(list.get(position).get("StartTime").toString()));
        tvEndTime.setText(getFormatTime(list.get(position).get("EndTime").toString()));
        tvItem.setText(position+"");
        return layout;
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
