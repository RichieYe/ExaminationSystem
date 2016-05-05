package com.richieye.examinationsystemNetwork;

import android.os.Handler;
import android.os.Message;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by RichieYe on 2016/4/14.
 */
public class MyThread implements Callable<String> {
    private String strWebServiceName;
    private String strMethodName;
    private Map<String,String> params;


    public MyThread(String strWebServiceName,String strMethodName,Map<String,String> params)
    {
        this.strWebServiceName=strWebServiceName;
        this.strMethodName=strMethodName;
        this.params=params;
    }

    @Override
    public String call() throws Exception {
        String strMessage="";
        NetWorkOperator operator=new NetWorkOperator();
        strMessage=operator.CallWebServie(strWebServiceName,strMethodName,params);
        return strMessage;
    }
}
