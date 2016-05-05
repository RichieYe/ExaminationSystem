package com.richieye.examinationsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.richieye.examinationsystemCustomControl.CustomRoundImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText etUserNo,etUserName,etUserPassword,etUserPasswordAgain;
    ImageView ivUserNoErr,ivUserNameErr,ivUserPasswordErr,ivUserPasswordAgainErr;
    CustomRoundImageView ivHead;
    TextView txtFilePath;
    Button btnRegOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
}
