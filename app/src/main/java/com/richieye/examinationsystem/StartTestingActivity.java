package com.richieye.examinationsystem;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.iwgang.countdownview.CountdownView;

public class StartTestingActivity extends AppCompatActivity {

    CountdownView countdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_testing);


        ActionBar actionBar=this.getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            LinearLayout v= (LinearLayout) getLayoutInflater().inflate(R.layout.start_testing_header_bar,null);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v,layout);
            Log.e("66666666","666666666666");
            /*
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
            myAdapter.add("一、填空题");
            myAdapter.add("二、选择题");
            myAdapter.add("三、多选题");
            myAdapter.add("四、判断题");
            myAdapter.add("五、编程题");
            actionBar.setListNavigationCallbacks(myAdapter, new ActionBar.OnNavigationListener() {
                @Override
                public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                    Toast.makeText(StartTestingActivity.this,"试试吧-------"+itemPosition,Toast.LENGTH_LONG).show();
                    return false;
                }
            });
            */
        }
    }
}
