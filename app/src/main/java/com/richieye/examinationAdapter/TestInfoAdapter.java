package com.richieye.examinationAdapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richieye.examinationsystem.R;
import com.richieye.examinationsystemModel.TUserTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

/**
 * Created by RichieYe on 2016/6/29.
 */

public class TestInfoAdapter extends SectionedBaseAdapter {

    List<String> lsTestHeader;
    List<List<TUserTest>> lsTestItems;


    public TestInfoAdapter(List<String> lsTestHeader,List<List<TUserTest>> lsTestItems)
    {
        this.lsTestHeader=lsTestHeader;
        this.lsTestItems=lsTestItems;
        Log.e("TestInfoAdapter1",lsTestItems.size()+"");
    }

    @Override
    public TUserTest getItem(int section, int position) {
        return lsTestItems.get(section).get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getSectionCount() {
        Log.e("TestInfoAdapter2",lsTestHeader.size()+"");
        return lsTestHeader.size();
    }

    @Override
    public int getCountForSection(int section) {
        Log.e("TestInfoAdapter3",lsTestItems.get(section).size()+"");
        return lsTestItems.get(section).size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        LinearLayout layout=null;
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout= (LinearLayout) inflater.inflate(R.layout.testinfo_item_item,null);
        }else
        {
            layout=(LinearLayout)convertView;
        }

        TextView tvTestInfoID=(TextView)layout.findViewById(R.id.tvTestInfoID);
        TextView tvTestInfoMo= (TextView) layout.findViewById(R.id.tvTestInfoNo);
        TextView tvTestInfoUAnswer=(TextView)layout.findViewById(R.id.tvTestInfoUAnswer);
        TextView tvTestInfoFlag=(TextView)layout.findViewById(R.id.tvTestInfoFlag);
        TUserTest tUserTest=lsTestItems.get(section).get(position);

        Log.e("99999999","aaaaaaa"+position);

        tvTestInfoID.setText(tUserTest.getID()+"");
        tvTestInfoMo.setText((position+1)+"");
        tvTestInfoUAnswer.setText(tUserTest.getUAnswer());
        tvTestInfoFlag.setText("未设置");
        //tvTestInfoMo.setText(lsTestItems.get(section).get(position));
        //tvTestInfoTitle.setText(lsTestItems.get(section).get(position));
        return layout;
    }

    private String getFlagForUAnswer(String strUAnswer,String strTAnswer)
    {
        return "";
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout=null;
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout= (LinearLayout) inflater.inflate(R.layout.testinfo_item_header,null);
        }else
        {
            layout=(LinearLayout)convertView;
        }

        TextView tvTestInfoHeader= (TextView) layout.findViewById(R.id.tvTestInfoHeader);
        tvTestInfoHeader.setText(lsTestHeader.get(section));
        return layout;
    }

    /**
     * 返回指定列表头的位置
     * @param section   列表头的索引
     * @param position    列表的定位
     * @return    返回实际的位置
     */
    public int getSelectionForSection(int section,int position)
    {
        if(section>=lsTestHeader.size()||section<0)
        {
            return 0;
        }

        int temp=0;
        int i=0;
        for(;i<section;i++)
        {
            temp+=lsTestItems.get(i).size();
        }
        Log.e("TestInfoAdapter",temp+position+i+"");
        return temp+position+i;
    }
}
