package com.richieye.examinationsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.richieye.examinationAdapter.TestInfoAdapter;
import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.TestingOperator;
import com.richieye.examinationOperator.UserOperator;
import com.richieye.examinationOperator.UserTestOperator;
import com.richieye.examinationsystemModel.TTestings;
import com.richieye.examinationsystemModel.TUserTest;

import java.util.ArrayList;
import java.util.List;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class ShowTestInfoActivity extends Activity {
    TextView tvShowDate,tvShowFlag,tvShowStartTime,tvShowEndTime;
    Button btnStart;
    PinnedHeaderListView phlvShow;
    Spinner spShow;

    UserOperator userOperator;
    TestingOperator testingOperator;
    ClassesOperator classesOperator;
    UserTestOperator userTestOperator;

    String[] strType={"一、填空题","二、选择题","三、多选题","四、判断题","五、编程题"};

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
        userTestOperator=new UserTestOperator(this);
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
        for(int i=0;i<strType.length;i++)
        {
            lstHeader.add(strType[i]);
        }
        List<List<TUserTest>> lstTests=userTestOperator.getTestByTID("1");

        myAdapter=new TestInfoAdapter(lstHeader,lstTests);
        phlvShow.setAdapter(myAdapter);
        phlvShow.setOnItemClickListener(phlvShow_OnItemClickListener);
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

    private AdapterView.OnItemClickListener phlvShow_OnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(ShowTestInfoActivity.this,StartTestingActivity.class);
            startActivity(intent);
        }
    };

}
