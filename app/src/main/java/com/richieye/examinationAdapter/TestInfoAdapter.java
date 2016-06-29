package com.richieye.examinationAdapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richieye.examinationsystem.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

/**
 * Created by RichieYe on 2016/6/29.
 */

public class TestInfoAdapter extends SectionedBaseAdapter {

    List<String> lsTestHeader;
    List<List<String>> lsTestItems;


    public TestInfoAdapter(List<String> lsTestHeader,List<List<String>> lsTestItems)
    {
        this.lsTestHeader=lsTestHeader;
        this.lsTestItems=lsTestItems;
    }

    @Override
    public Object getItem(int section, int position) {
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getSectionCount() {
        return lsTestHeader.size();
    }

    @Override
    public int getCountForSection(int section) {
        int count=0;
        for(int i=0;i<lsTestItems.size();i++)
        {
            if(lsTestItems.get(i).size()>count)
            {
                count=lsTestItems.get(i).size();
            }
        }
        return count;
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

        TextView tvTestInfoMo= (TextView) layout.findViewById(R.id.tvTestInfoNo);
        TextView tvTestInfoTitle=(TextView)layout.findViewById(R.id.tvTestInfoTitle);
        tvTestInfoMo.setText(lsTestItems.get(section).get(position));
        tvTestInfoTitle.setText(lsTestItems.get(section).get(position));
        return layout;
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
}
