package com.richieye.examinationsystemNetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.richieye.examinationsystem.RegisterActivity;

/**
 * Created by RichieYe on 2016/5/26.
 */
public class NetworkRecever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        new RegisterActivity().handler.sendEmptyMessage(1);
    }
}
