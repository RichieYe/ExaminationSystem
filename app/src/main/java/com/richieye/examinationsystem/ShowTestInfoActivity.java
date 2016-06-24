package com.richieye.examinationsystem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.TestingOperator;
import com.richieye.examinationOperator.UserOperator;

public class ShowTestInfoActivity extends Activity {
    TextView tvShowClass,tvShowNo,tvShowName,tvShowGender,tvShowDate,tvShowFlag,tvShowStartTime,tvShowEndTime;
    Button btnStart;
    PullToRefreshListView lvShow;

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
    }

    private void inits_control()
    {
        /*
        tvShowClass= (TextView) findViewById(R.id.tvShowTestClass);
        tvShowNo= (TextView) findViewById(R.id.tvShowTestNo);
        tvShowName= (TextView) findViewById(R.id.tvShowTestName);
        tvShowGender= (TextView) findViewById(R.id.tvShowTestGender);
        tvShowDate= (TextView) findViewById(R.id.tvShowTestGender);
        tvShowFlag= (TextView) findViewById(R.id.tvShowTestFlag);
        */
        tvShowStartTime= (TextView) findViewById(R.id.tvShowTestStartTime);
        tvShowEndTime= (TextView) findViewById(R.id.tvShowTestEndTime);
        btnStart= (Button) findViewById(R.id.btnShowTestStart);
        lvShow= (PullToRefreshListView) findViewById(R.id.lvShowTestInfo);
        lvShow.setMode(PullToRefreshBase.Mode.BOTH);
    }
}
