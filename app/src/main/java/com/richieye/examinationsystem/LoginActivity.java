package com.richieye.examinationsystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationCommon.Common;
import com.richieye.examinationsystemJson.ClassesOperator;
import com.richieye.examinationsystemModel.TClasses;
import com.richieye.examinationsystemNetwork.MyThread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoginActivity extends AppCompatActivity {
    EditText etUserName,etUserPassword,etUserNo;
    Spinner spClasses;
    Button btnLogin;
    ImageView ivShowUserNameErr,ivShowUserNoErr,ivShowUserPasswordErr;
    ImageView ivShowHead;
    int iClassID=0;
    List<Map<String,String>> list;
    String strClassesJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent=getIntent();

        if(intent!=null)
        {
            strClassesJson=intent.getStringExtra("ClassName");
            if(!"".equals(strClassesJson.trim()))
            {
                ClassesOperator operator=new ClassesOperator(this,false);
                list=operator.getClasses(strClassesJson);
            }
        }
        init();
    }

    protected void init()
    {
        etUserName= (EditText) findViewById(R.id.etLoginUserName);
        etUserNo= (EditText) findViewById(R.id.etLoginUserNo);
        etUserPassword= (EditText) findViewById(R.id.etLoginUserPassword);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        ivShowUserNoErr= (ImageView) findViewById(R.id.ivshowUserNoErr);
        ivShowUserNameErr= (ImageView) findViewById(R.id.ivShowUserNameErr);
        ivShowUserPasswordErr= (ImageView) findViewById(R.id.ivShowUserPasswordErr);
        ivShowHead= (ImageView) findViewById(R.id.ivShowHead);
        spClasses= (Spinner) findViewById(R.id.spClassName);
        etUserNo.setFocusable(true);
        btnLogin.setOnClickListener(listener);
        etUserPassword.addTextChangedListener(watcher);
        init_Spinner();

        spClasses.setOnItemSelectedListener(spinner_listener);
    }

    private AdapterView.OnItemSelectedListener spinner_listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout layout=(LinearLayout)view;
            iClassID=Integer.parseInt(((TextView)view.findViewById(R.id.txtSpinnerItem_ID)).getText().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            iClassID=0;
        }
    };

    private void init_Spinner()
    {
        SimpleAdapter adapter=new SimpleAdapter(this, list,R.layout.spinner_item,new String[]{"ID","ClassName"},
                new int[]{R.id.txtSpinnerItem_ID,R.id.txtSpinnerItem_ClassName});
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spClasses.setAdapter(adapter);
    }

    private TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!(s.toString().trim().length()==0)) {
                ivShowUserPasswordErr.setVisibility(View.GONE);
            }
        }


    };

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnLogin:
                    GetToLogin();
                    break;
            }
        }
    };

    private void GetToLogin()
    {
        int iError=0;
        if("".equals(etUserNo.getText().toString().trim()))
        {
            ivShowUserNoErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if("".equals(etUserName.getText().toString().trim()))
        {
            ivShowUserNameErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if("".equals(etUserPassword.getText().toString().trim()))
        {
            ivShowUserPasswordErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if(iError>0)
        {
            Toast.makeText(LoginActivity.this,"输入的学号、用户名或密码不能为空！",Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void txtRegisterClick(View view)
    {
        Intent intent=new Intent(this,RegisterActivity.class);

        intent.putExtra("Classes",strClassesJson);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==0)
        {
            if(data!=null)
            {
                spClasses.setSelection(data.getIntExtra("Position",0));
                etUserNo.setText(data.getStringExtra("UserNo"));
                etUserName.setText(data.getStringExtra("UserName"));
                etUserPassword.setText(data.getStringExtra("UserPassword"));
                String strFile=data.getStringExtra("UserHead");
                btnLogin.performClick();
            }
        }
    }

    public Handler handler=new Handler()
    {

    };
}
