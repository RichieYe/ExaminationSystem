package com.richieye.examinationsystemNetwork;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/13.
 */
public class NetWorkOperator {

    //服务器IP
    final String WEB_SERVICE_URL = "http://192.168.0.155:8081/";
    final String NAMESPACE = "http://www.ExaminationSystem.com/";//命名空间


    public static boolean isNetworkAvailable(Context context)
    {
        //Context context=activity.getApplicationContext();

        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(manager==null)
        {
            return  false;
        }else
        {
            NetworkInfo infos=manager.getActiveNetworkInfo();

            if(infos!=null&&infos.isAvailable())
            {
                return  true;
            }
        }
        return  false;
    }

    public String CallWebServie(String strWebServiceName,String strMethodName,Map<String,String> params)
    {
        SoapObject request=new SoapObject(NAMESPACE,strMethodName);

        if(params!=null)
        {
            Iterator iterator=params.entrySet().iterator();
            while (iterator.hasNext())
            {
                Map.Entry entry=(Map.Entry)iterator.next();
                request.addProperty((String) entry.getKey(),
                        entry.getValue());
            }
        }

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.bodyOut=request;
        envelope.dotNet=true;       //Net编写的WebService必须加上这句

        String strUrl=WEB_SERVICE_URL+strWebServiceName;
        HttpTransportSE ht=new HttpTransportSE(strUrl);

        String strJSon="";
        // 使用call方法调用WebService方法
        try {
            ht.call(null, envelope);
        } catch (HttpResponseException e) {
            e.printStackTrace();
            strJSon=e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            strJSon=e.getMessage();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            strJSon=e.getMessage();
        }
        try {
            final SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            if (result != null) {
                Log.d("----收到的回复----", result.toString());
                strJSon=result.toString();
            }

        } catch (SoapFault e) {
            Log.e("----发生错误---", e.getMessage());
            e.printStackTrace();
            strJSon=e.getMessage();
        }
        return strJSon;
    }
}
