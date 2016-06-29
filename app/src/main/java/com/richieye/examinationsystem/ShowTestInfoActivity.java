package com.richieye.examinationsystem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.richieye.examinationAdapter.TestInfoAdapter;
import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.TestingOperator;
import com.richieye.examinationOperator.UserOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class ShowTestInfoActivity extends Activity {
    TextView tvShowDate,tvShowFlag,tvShowStartTime,tvShowEndTime;
    Button btnStart;
    PinnedHeaderListView phlvShow;

    UserOperator userOperator;
    TestingOperator testingOperator;
    ClassesOperator classesOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_test_info);
        userOperator=new UserOperator(this);
        testingOperator=new TestingOperator(this);
        classesOperator=new ClassesOperator(this);
        inits_control();
        inits_data();
    }

    private void inits_control()
    {
        tvShowDate= (TextView) findViewById(R.id.tvShowTestDate);
        tvShowFlag=(TextView)findViewById(R.id.tvShowTestFlag);
        tvShowStartTime= (TextView) findViewById(R.id.tvShowTestStartTime);
        tvShowEndTime= (TextView) findViewById(R.id.tvShowTestEndTime);
        btnStart= (Button) findViewById(R.id.btnShowTestStart);
        phlvShow= (PinnedHeaderListView) findViewById(R.id.lvShowTestInfo);
    }

    private void inits_data()
    {
        List<String> lstHeader=new ArrayList<>();
        List<List<String>> lstItems=new ArrayList<>();
        Random rd=new Random();
        for(int i=0;i<5;i++)
        {
            lstHeader.add("Header "+(i+1)+":");
            int max=rd.nextInt(20);
            for(int j=0;j<15;j++)
            {
                List<String> lstSubItem=new ArrayList<>();
                lstSubItem.add((j+1)+"");
                lstSubItem.add("Item:"+(j+1));
                lstItems.add(lstSubItem);

            }
        }

        TestInfoAdapter myAdapter=new TestInfoAdapter(lstHeader,lstItems);
        phlvShow.setAdapter(myAdapter);
    }
}
