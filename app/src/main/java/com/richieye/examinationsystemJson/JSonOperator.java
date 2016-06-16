package com.richieye.examinationsystemJson;

import com.richieye.examinationsystemNetwork.MyThread;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by RichieYe on 2016/6/16.
 */

public class JSonOperator {

    public static String getJSonStringForNetWork(String strWebServiceName,String strMethodName,Map<String,String> params)
    {
        String strMsg="";

        ExecutorService exs=Executors.newCachedThreadPool();
        Future<String> future=exs.submit(new MyThread(strWebServiceName,strMethodName,params));
        try{
            strMsg=future.get();
        }catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }catch (ExecutionException ex)
        {
            ex.printStackTrace();
        }
        return strMsg;
    }
}
