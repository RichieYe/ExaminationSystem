package com.richieye.examinationsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationOperator.ClassesOperator;
import com.richieye.examinationsystemCustomControl.CustomRoundImageView;

import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText etUserNo,etUserName,etUserPassword,etUserPasswordAgain;
    ImageView ivUserNoErr,ivUserNameErr,ivUserPasswordErr,ivUserPasswordAgainErr;
    CustomRoundImageView ivHead;
    TextView txtFilePath;
    Button btnRegOK;
    Spinner spClass;

    ClassesOperator cOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cOperator=new ClassesOperator(this);

        init();
    }

    private void init()
    {
        etUserNo=(EditText)findViewById(R.id.etRegUserNo);
        etUserName=(EditText)findViewById(R.id.etRegUserName);
        etUserPassword=(EditText)findViewById(R.id.etRegUserPassword);
        etUserPasswordAgain=(EditText)findViewById(R.id.etRegUserPasswordAgain);
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
           if(CheckControl())
           {
               return;
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

        if(etUserPassword.getText().toString().trim()!=etUserPasswordAgain.getText().toString().trim())
        {
            ivUserPasswordErr.setVisibility(View.VISIBLE);
            ivUserPasswordAgainErr.setVisibility(View.VISIBLE);
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
}
