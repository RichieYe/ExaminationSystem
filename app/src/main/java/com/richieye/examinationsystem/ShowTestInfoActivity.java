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
    TextView tvShowDate,tvShowFlag,tvShowStartTime,tvShowEndTime;
    Button btnStart;

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
    }

    private void inits_data()
    {

    }
}
