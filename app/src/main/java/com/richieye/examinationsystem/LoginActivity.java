package com.richieye.examinationsystem;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationCommon.Common;
import com.richieye.examinationOperator.UserOperator;
import com.richieye.examinationSystemIO.PreferencesOperator;
import com.richieye.examinationsystemModel.TClasses;
import com.richieye.examinationsystemModel.TStudents;
import com.richieye.examinationsystemNetwork.MyThread;
import com.richieye.examinationOperator.ClassesOperator;

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
    CheckBox cbxRemember;
    int iClassID=0;
    RadioButton rbNetWork;
    List<Map<String,String>> list;

    UserOperator uOperator;

    TStudents tStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uOperator=new UserOperator(this);
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
        etUserNo.addTextChangedListener(watcher);
        etUserName.addTextChangedListener(watcher);
        etUserPassword.addTextChangedListener(watcher);
        cbxRemember= (CheckBox) findViewById(R.id.cbxRemember);
        rbNetWork= (RadioButton) findViewById(R.id.rbNetwork);
        init_Spinner();
        Log.e("LoginActivity","1111111111111111111");
        spClasses.setOnItemSelectedListener(spinner_listener);
        LoadUserInfo();
    }

    private AdapterView.OnItemSelectedListener spinner_listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout layout=(LinearLayout)view;
            iClassID=Integer.parseInt(((TextView)layout.findViewById(R.id.txtSpinnerItem_ID)).getText().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            iClassID=0;
        }
    };

    private void init_Spinner()
    {
        ClassesOperator operator=new ClassesOperator(this);
        list=operator.getClasses();
        if(list!=null) {
            SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.spinner_item, new String[]{"ID", "ClassName"},
                    new int[]{R.id.txtSpinnerItem_ID, R.id.txtSpinnerItem_ClassName});
            adapter.setDropDownViewResource(R.layout.spinner_item);
            spClasses.setAdapter(adapter);
        }
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
            if(!etUserNo.getText().toString().trim().equals(""))
            {
                ivShowUserNoErr.setVisibility(View.GONE);
            }

            if(!etUserName.getText().toString().trim().equals(""))
            {
                ivShowUserNameErr.setVisibility(View.GONE);
            }

            if(!etUserPassword.getText().toString().trim().equals(""))
            {
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
        }else
        {
            Login();
        }
    }

    private void Login()
    {
        Map<String,String>map=new HashMap<String,String>();
        map.put("No",etUserNo.getText().toString().trim());
        map.put("UserName",etUserName.getText().toString().trim());
        map.put("CId",iClassID+"");
        map.put("Password",etUserPassword.getText().toString().trim());

        Log.e("LoginActivity",map.toString());
        tStudents=uOperator.Login(map,rbNetWork.isChecked()?true:false);
        if(tStudents!=null)
        {
            Intent intent=new Intent(this,MainActivity.class);
            SavaSharedPreferences();
            intent.putExtra("Student",tStudents);
            startActivity(intent);
            finish();
        }else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("输入的信息有误！");
            builder.setIcon(R.mipmap.ic_error);
            builder.setMessage("输入的用户名或密码有误，请重新输入或与管理员联系！");
            builder.setPositiveButton("确定",null);
            builder.create().show();
        }
    }

    private String getClassID()
    {
        LinearLayout layout=(LinearLayout) spClasses.getSelectedView();
        TextView tvUserClassNo= (TextView) layout.findViewById(R.id.txtSpinnerItem_ID);
        return tvUserClassNo.getText().toString();
    }

    public void txtRegisterClick(View view)
    {
        Intent intent=new Intent(this,RegisterActivity.class);

        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    private void LoadUserInfo()
    {
        Map<String,String> map= PreferencesOperator.loadUserInfo(this);
        boolean isSave=Boolean.parseBoolean(map.get("isSave"));
        if(isSave)
        {
            cbxRemember.setChecked(true);
            //int iChoose=Integer.parseInt(map.get("CID"));
            spClasses.setSelection(Integer.parseInt(map.get("CID")));
            etUserNo.setText(map.get("UserNo"));
            etUserName.setText(map.get("UserName"));
            etUserPassword.setText(map.get("Password"));
        }else
        {
            cbxRemember.setChecked(false);
        }
    }

    protected void SavaSharedPreferences()
    {
        String strUserNo="",strUserName="",strPassword="";
        int iCID=1;
        boolean isSave=cbxRemember.isChecked();
        if(isSave)
        {
            iCID=spClasses.getSelectedItemPosition();
            strUserNo=etUserNo.getText().toString().trim();
            strUserName=etUserName.getText().toString().trim();
            strPassword=etUserPassword.getText().toString().trim();
        }

        PreferencesOperator.savaUserInfo(this,iCID,strUserNo,strUserName,strPassword,isSave);
    }
}
