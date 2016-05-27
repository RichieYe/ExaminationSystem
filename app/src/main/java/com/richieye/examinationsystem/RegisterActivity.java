package com.richieye.examinationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationOperator.UserOperator;
import com.richieye.examinationsystemCustomControl.CustomRoundImageView;
import com.richieye.examinationsystemNetwork.NetWorkOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements TextWatcher {

    EditText etUserNo,etUserName,etUserPassword,etUserPasswordAgain,etUserIphone,etUserAddress;
    RadioButton rbnGender;
    ImageView ivUserNoErr,ivUserNameErr,ivUserPasswordErr,ivUserPasswordAgainErr;
    CustomRoundImageView ivHead;
    TextView txtFilePath;
    Button btnRegOK;
    Spinner spClass;

    ClassesOperator cOperator;
    UserOperator uOperator;

    boolean isExistsNO=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cOperator=new ClassesOperator(this);
        uOperator=new UserOperator(this);
        init();
    }

    private void init()
    {
        etUserNo=(EditText)findViewById(R.id.etRegUserNo);
        etUserNo.addTextChangedListener(this);
        etUserNo.setOnFocusChangeListener(etUserNoFocusChangeListener);
        etUserName=(EditText)findViewById(R.id.etRegUserName);
        etUserName.addTextChangedListener(this);
        etUserPassword=(EditText)findViewById(R.id.etRegUserPassword);
        etUserPassword.addTextChangedListener(this);
        etUserPasswordAgain=(EditText)findViewById(R.id.etRegUserPasswordAgain);
        etUserPasswordAgain.addTextChangedListener(this);
        etUserIphone= (EditText) findViewById(R.id.etRegUserPhone);
        etUserAddress= (EditText) findViewById(R.id.etRegUserAddress);
        rbnGender=(RadioButton)findViewById(R.id.rbRegMale);
        ivUserNoErr=(ImageView)findViewById(R.id.ivRegUserNoErr);
        ivUserNameErr=(ImageView)findViewById(R.id.ivRegUserNameErr);
        ivUserPasswordErr=(ImageView)findViewById(R.id.ivRegUserPassword);
        ivUserPasswordAgainErr=(ImageView)findViewById(R.id.ivRegUserPasswordAgain);
        ivHead=(CustomRoundImageView)findViewById(R.id.ivRegHead);
        ivHead.setOnLongClickListener(ivOnLongClickListener);
        btnRegOK=(Button)findViewById(R.id.btnRegOK);
        btnRegOK.setOnClickListener(listener);
        txtFilePath=(TextView)findViewById(R.id.txtRegHeadImagePath);
        spClass=(Spinner)findViewById(R.id.spRegClassName);

        init_spinner();
    }

    private void init_spinner()
    {
        List<Map<String,String>> list=cOperator.getClasses();
        if(list!=null) {
            SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.spinner_item, new String[]{"ID", "ClassName"},
                    new int[]{R.id.txtSpinnerItem_ID, R.id.txtSpinnerItem_ClassName});
            adapter.setDropDownViewResource(R.layout.spinner_item);
            spClass.setAdapter(adapter);
        }
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getClassID();
           if(CheckControl()||isExistsNO)
           {
               return;
           }
            if (checkNetWork()) {
                handler.sendEmptyMessage(1);
            }
        }
    };

    private View.OnFocusChangeListener etUserNoFocusChangeListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus)
            {
                if(!checkNetWork())
                {
                    isExistsNO=true;
                    return;
                }

                if(uOperator.checkStudentNo(etUserNo.getText().toString().trim()))
                {
                    Log.e("RegisterActivity",uOperator.checkStudentNo(etUserNo.getText().toString().trim())+"");
                    Toast.makeText(RegisterActivity.this,"学号："+etUserNo.getText()
                            +",已经被注册了！请检查一下你的学号！或与管理员联系！谢谢！！",Toast.LENGTH_LONG).show();
                    ivUserNoErr.setVisibility(View.VISIBLE);
                    isExistsNO=true;
                }else
                {
                    isExistsNO=false;
                }
                Log.e("RegisterActivity",uOperator.checkStudentNo(etUserNo.getText().toString().trim())+"");
            }
        }
    };

    private boolean CheckControl()
    {
        int iError=0;
        if(etUserNo.getText().toString().trim().equals(""))
        {
            ivUserNoErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if(etUserName.getText().toString().trim().equals(""))
        {
            ivUserNameErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if(etUserPassword.getText().toString().trim().equals(""))
        {
            ivUserPasswordErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if(etUserPasswordAgain.getText().toString().trim().equals(""))
        {
            ivUserPasswordAgainErr.setVisibility(View.VISIBLE);
            iError++;
        }

        if(!etUserPassword.getText().toString().trim().equals(etUserPasswordAgain.getText().toString().trim()))
        {
            ivUserPasswordErr.setVisibility(View.VISIBLE);
            ivUserPasswordAgainErr.setVisibility(View.VISIBLE);
            Log.e("RegisterActivity",etUserPassword.getText()+"        "+etUserPasswordAgain.getText());
            iError++;
        }

        return iError > 0;
    }

    private View.OnLongClickListener ivOnLongClickListener=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(RegisterActivity.this,"该功能尚未实现！",Toast.LENGTH_LONG).show();
            return false;
        }
    };

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
            ivUserNoErr.setVisibility(View.GONE);
        }

        if(!etUserName.getText().toString().trim().equals(""))
        {
            ivUserNameErr.setVisibility(View.GONE);
        }

        if(!etUserPassword.getText().toString().trim().equals(""))
        {
            ivUserPasswordErr.setVisibility(View.GONE);
        }

        if(!etUserPasswordAgain.getText().toString().trim().equals(""))
        {
            ivUserPasswordAgainErr.setVisibility(View.GONE);
        }

        if(etUserPassword.getText().toString().trim().equals(etUserPasswordAgain.getText().toString().trim()))
        {
            ivUserPasswordErr.setVisibility(View.GONE);
            ivUserPasswordAgainErr.setVisibility(View.GONE);
        }else
        {
            ivUserPasswordAgainErr.setVisibility(View.VISIBLE);
        }
    }

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Log.e("RegisterActivity","55555555555");
            switch (msg.what)
            {
                case 1:
                    RegisterUser();
                    break;
            }
        }
    };

    private void RegisterUser()
    {
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        Map<String,String> map=new HashMap<String,String>();
        map.put("UserName",etUserName.getText().toString().trim());
        map.put("No",etUserNo.getText().toString().trim());
        map.put("CId",getClassID());
        map.put("Password",etUserPassword.getText().toString().trim());
        map.put("Gender",rbnGender.isChecked()?"男":"女");
        map.put("Phone",etUserIphone.getText().toString().trim());
        map.put("Address",etUserAddress.getText().toString().trim());

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("注册新用户");

        if(uOperator.InsertStudent(map))
        {


            builder.setIcon(R.mipmap.ic_ok);
            builder.setMessage("注册用户成功，点击<确定>按钮后直接登录！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent();
                    intent.putExtra("Position",spClass.getSelectedItemPosition());
                    intent.putExtra("UserNo", etUserNo.getText().toString());
                    intent.putExtra("UserName",etUserName.getText().toString());
                    intent.putExtra("UserPassword",etUserPassword.getText().toString());
                    intent.putExtra("UserHead","");
                    RegisterActivity.this.setResult(0,intent);
                    RegisterActivity.this.finish();
                }
            });
            builder.create().show();
        }else
        {
            builder.setIcon(R.mipmap.ic_error);
            builder.setMessage("注册用户失败！请稍候再次尝试或与管理员联系！");
            builder.setPositiveButton("确定",null);
            builder.create().show();
        }
    }

    private String getClassID()
    {
        LinearLayout layout=(LinearLayout) spClass.getSelectedView();
        TextView tvUserClassNo= (TextView) layout.findViewById(R.id.txtSpinnerItem_ID);
        return tvUserClassNo.getText().toString();
    }

    private boolean checkNetWork()
    {
        if(!NetWorkOperator.isNetworkAvailable(RegisterActivity.this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("确认是否开启网络？");
            builder.setMessage("网络不可用！注册用户需要开启网络，请问是否打开网络？");
            builder.setIcon(R.mipmap.ic_error);
            builder.setPositiveButton("开启", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                    startActivity(wifiSettingsIntent);
                }
            });
            builder.setNegativeButton("取消",null);
            builder.create().show();
        }else
        {
            return true;
        }
        return false;
    }
}
