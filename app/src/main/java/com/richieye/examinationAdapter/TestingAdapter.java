package com.richieye.examinationAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richieye.examinationsystem.R;

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

    TextView tvID,tvTestDate,tvTestFlag,tvStartTime,tvEndTime;

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

        tvID.setText(list.get(position).get("_id").toString());
        tvTestDate.setText(getTestDate(list.get(position).get("TestDate").toString()));
        tvTestFlag.setText(getFlag(list.get(position).get("Flag").toString()));
        tvStartTime.setText(getFormatTime(list.get(position).get("StartTime").toString()));
        tvEndTime.setText(getFormatTime(list.get(position).get("EndTime").toString()));
        return layout;
    }

    private String getTestDate(String strDate)
    {
        return "";
    }

    private String getFlag(String strFlag)
    {
        return "";
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
