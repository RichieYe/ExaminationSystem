package com.richieye.examinationsystem;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.richieye.examinationAdapter.TestInfoAdapter;
import com.richieye.examinationAdapter.TestInfoTypeAdapter;
import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.TestingOperator;
import com.richieye.examinationOperator.UserOperator;
import com.richieye.examinationsystemModel.TTestings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class ShowTestInfoActivity extends Activity {
    TextView tvShowDate,tvShowFlag,tvShowStartTime,tvShowEndTime;
    Button btnStart;
    PinnedHeaderListView phlvShow;
    Spinner spShow;

    UserOperator userOperator;
    TestingOperator testingOperator;
    ClassesOperator classesOperator;

    String[] strType={"填空题","选择题","多选题","判断题","编程题"};

    TestInfoAdapter myAdapter;
    boolean isSpinnerSelect=true;
    String strTID="";
    TTestings tTestings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_test_info);
        userOperator=new UserOperator(this);
        testingOperator=new TestingOperator(this);
        classesOperator=new ClassesOperator(this);
        Intent intent=getIntent();
        inits_control();
        if(intent!=null) {
            strTID=intent.getStringExtra("TID");
            if(!"".equals(strTID.trim()))
            {
                inits_data();
            }
        }
    }

    private void inits_control()
    {
        tvShowDate= (TextView) findViewById(R.id.tvShowTestDate);
        tvShowFlag=(TextView)findViewById(R.id.tvShowTestFlag);
        tvShowStartTime= (TextView) findViewById(R.id.tvShowTestStartTime);
        tvShowEndTime= (TextView) findViewById(R.id.tvShowTestEndTime);
        btnStart= (Button) findViewById(R.id.btnShowTestStar);
        phlvShow= (PinnedHeaderListView) findViewById(R.id.lvShowTestInfo);
        spShow= (Spinner) findViewById(R.id.spTestInfo_Type);
    }

    private void inits_data()
    {
        tTestings=testingOperator.getTestByTID(strTID);
        if (tTestings!= null) {
            tvShowDate.setText(tTestings.getTestDate());
            tvShowFlag.setText(tTestings.getFlag());
            tvShowStartTime.setText(tTestings.getStartTime());
            tvShowEndTime.setText(tTestings.getEndTime());
        }
        init_Spinner();
        init_PinnedHeaderListView();
    }

    private void init_Spinner() {
        ArrayAdapter typeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,strType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spShow.setAdapter(typeAdapter);
        spShow.setOnItemSelectedListener(spShow_Listener);
    }

    private void init_PinnedHeaderListView() {
        List<String> lstHeader=new ArrayList<>();
        List<List<String>> lstItems=new ArrayList<>();
        Random rd=new Random();
        for(int i=0;i<5;i++)
        {
            lstHeader.add("Header "+(i+1)+":");
            int max=rd.nextInt(20);
            List<String> lstSubItem=new ArrayList<>();
            for(int j=0;j<max;j++)
            {

                lstSubItem.add((j+1)+"");
                lstSubItem.add("Item:"+(j+1));

            }
            lstItems.add(lstSubItem);
        }
        myAdapter=new TestInfoAdapter(lstHeader,lstItems);
        phlvShow.setAdapter(myAdapter);
    }

    private AdapterView.OnItemSelectedListener spShow_Listener=new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            phlvShow.setSelection(myAdapter.getSelectionForSection(position, 0));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
