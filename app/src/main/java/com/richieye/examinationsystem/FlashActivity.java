package com.richieye.examinationsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.richieye.examinationsystemModel.TClasses;
import com.richieye.examinationsystemNetwork.NetWorkOperator;

import java.util.List;

public class FlashActivity extends AppCompatActivity {
    TextView txtVersion;
    ProgressBar pbStart;
    //List<TClasses> tcList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.hide();
        }
        setContentView(R.layout.activity_flash);
        initControl();

        if(!NetWorkOperator.isNetworkAvailable(this)) {
            Toast.makeText(this, "没有网络连接！应用程序将使用本地数据库！", Toast.LENGTH_LONG).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                SystemClock.sleep(3000);
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    protected void initControl()
    {
        txtVersion= (TextView) findViewById(R.id.txtVersion);
        pbStart= (ProgressBar) findViewById(R.id.pbStart);

    }

    protected Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SharedPreferences sp=getSharedPreferences("AppSets",MODE_APPEND);
            boolean isFirst=sp.getBoolean("isFirst",true);
            Intent intent=new Intent();
            switch (msg.what)
            {
                case 0:
                    break;
                case 1:
                    if(isFirst)
                    {
                        intent.setClass(FlashActivity.this,WelcomeActivity.class);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putBoolean("isFirst",false);
                        editor.commit();
                    }else
                    {
                        intent.setClass(FlashActivity.this,LoginActivity.class);
                    }
                    break;
            }
            startActivity(intent);
            FlashActivity.this.finish();
        }
    };
}
