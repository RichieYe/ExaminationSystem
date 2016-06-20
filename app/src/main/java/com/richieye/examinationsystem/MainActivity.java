package com.richieye.examinationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationAdapter.MyItemDecoration;
import com.richieye.examinationAdapter.RecyclerViewHeadAdapter;
import com.richieye.examinationAdapter.TestingAdapter;
import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.TestingOperator;
import com.richieye.examinationOperator.UserOperator;
import com.richieye.examinationsystemCustomControl.CustomRoundImageView;
import com.richieye.examinationsystemModel.TStudents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvClassName, tvNo, tvName, tvGender, tvPhone, tvAddress, tvManager;
    CustomRoundImageView myHeadImage;
    ListView lvTestingShow;

    RadioGroup rgTesting;
    RadioButton rbShowAll,rbShowUnFinish,rbShowExamin,rbShowFinished;

    LinearLayoutManager mHeadLayoutManager,mItemLayoutManager;

    TStudents tStudent;

    ClassesOperator cOperator;
    UserOperator uOperator;
    TestingOperator tOperator;

    List<Map<String,String>> lstShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cOperator=new ClassesOperator(this);
        uOperator=new UserOperator(this);
        tOperator=new TestingOperator(this);


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
        lvTestingShow= (ListView) findViewById(R.id.lvMainShow);
        rgTesting= (RadioGroup) findViewById(R.id.rgMainShowTesting);
        rgTesting.setOnCheckedChangeListener(Testing_OnCheckedChangeListener);
        rbShowAll= (RadioButton) findViewById(R.id.rbMainShowAll);
        rbShowUnFinish= (RadioButton) findViewById(R.id.rbMainUnFinish);
        rbShowExamin= (RadioButton) findViewById(R.id.rbMainExamin);
        rbShowFinished= (RadioButton) findViewById(R.id.rbMainFinished);
    }

    private RadioGroup.OnCheckedChangeListener Testing_OnCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            List<Map<String,String>> list=null;
            switch (checkedId)
            {
                case R.id.rbMainShowAll:
                    list=tOperator.getTestingByUID(tStudent.getID(),-1);
                    break;
                case R.id.rbMainUnFinish:
                    list=tOperator.getTestingByUID(tStudent.getID(),0);
                    break;
                case R.id.rbMainExamin:
                    list=tOperator.getTestingByUID(tStudent.getID(),1);
                    break;
                case R.id.rbMainFinished:
                    list=tOperator.getTestingByUID(tStudent.getID(),2);
                    break;
            }
            TestingAdapter myAdapter=new TestingAdapter(MainActivity.this,list);
            lvTestingShow.setAdapter(myAdapter);
        }
    };

    private void initDatas() {
        tvClassName.setText(cOperator.getClassNameByID(tStudent.getCID()));
        tvNo.setText(tStudent.getNo());
        tvName.setText(tStudent.getUserName());
        tvGender.setText(tStudent.getGender());
        tvPhone.setText(tStudent.getPhone());
        tvAddress.setText(tStudent.getAddress());
        lstShow=tOperator.getTestingByUID(tStudent.getID(),-1);
        if(lstShow!=null) {
            TestingAdapter myAdapter=new TestingAdapter(this,lstShow);
            /*
            SimpleAdapter myAdapter=new SimpleAdapter(this,lstShow,R.layout.testing_main_item,
                    new String[]{"_id","TestDate","Flag","StartTime","EndTime"},
                    new int[]{R.id.tvTestingMainItem_ID,R.id.tvTestingMainItem_Date,
                            R.id.tvTestingMainItem_Flag,R.id.tvTestingMainItem_StartTime,R.id.tvTestingMainItem_EndTime});
                            */
            lvTestingShow.setAdapter(myAdapter);
            Log.e("MainActivity1",lstShow.size()+"");
        }

        lvTestingShow.setOnItemClickListener(TestingShow_OnItemClickListener);
    }

    private AdapterView.OnItemClickListener TestingShow_OnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(MainActivity.this,ShowTestInfoActivity.class);
            intent.putExtra("SID",tStudent.getID()+"");
            TextView tvTID= (TextView) view.findViewById(R.id.tvTestingMainItem_ID);
            String sTID=tvTID.getText().toString();
            intent.putExtra("TID",sTID);
            Log.e("Mainactivity5",sTID);
            startActivity(intent);
        }
    };

}
