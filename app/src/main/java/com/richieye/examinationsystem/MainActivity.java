package com.richieye.examinationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.UserOperator;
import com.richieye.examinationsystemCustomControl.CustomRoundImageView;
import com.richieye.examinationsystemModel.TStudents;

public class MainActivity extends AppCompatActivity {
    TextView tvClassName, tvNo, tvName, tvGender, tvPhone, tvAddress, tvManager;
    CustomRoundImageView myHeadImage;
    RecyclerView rvHead, rvItem;

    TStudents tStudent;

    ClassesOperator cOperator;
    UserOperator uOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cOperator=new ClassesOperator(this);
        uOperator=new UserOperator(this);

        initViews();
        Intent intent=getIntent();
        if(intent!=null) {
            tStudent= (TStudents) intent.getSerializableExtra("Student");
            if(tStudent!=null) {
                initDatas();
            }
        }
    }

    private void initViews() {
        tvClassName = (TextView) findViewById(R.id.tvMainClass);
        tvNo=(TextView)findViewById(R.id.tvMainNo);
        tvName=(TextView)findViewById(R.id.tvMainName);
        tvGender=(TextView)findViewById(R.id.tvMainGender);
        tvPhone=(TextView)findViewById(R.id.tvMainPhone);
        tvAddress=(TextView)findViewById(R.id.tvMainAddress);
        tvManager=(TextView)findViewById(R.id.tvMainModify);
        tvManager.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"该功能尚未完成，敬请等待！",Toast.LENGTH_LONG).show();
            }
        });
        myHeadImage=(CustomRoundImageView)findViewById(R.id.ivMainHead);
        rvHead=(RecyclerView)findViewById(R.id.rvMainHeadList);
        rvItem=(RecyclerView)findViewById(R.id.rvMainItemList);
    }

    private void initDatas() {
        tvClassName.setText(cOperator.getClassNameByID(tStudent.getCID()));
        tvNo.setText(tStudent.getNo());
        tvName.setText(tStudent.getUserName());
        tvGender.setText(tStudent.getGender());
        tvPhone.setText(tStudent.getPhone());
        tvAddress.setText(tStudent.getAddress());
    }
}
