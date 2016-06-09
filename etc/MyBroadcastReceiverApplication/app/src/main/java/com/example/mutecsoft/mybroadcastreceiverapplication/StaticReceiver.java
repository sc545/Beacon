package com.example.mutecsoft.mybroadcastreceiverapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by mutecsoft on 2016-04-22.
 */
public class StaticReceiver extends BroadcastReceiver {
    public static final String MY_ACTION = "com.skt.prod.dialer.CHANGE_TPHONE_MODE_SETTING";
    private static final String EXTRA_LESS_THAN_14 = "EXTRA_LESS_THAN_14";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(!MY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "Not equals", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(context, "Less than 14 : "+intent.getBooleanExtra(EXTRA_LESS_THAN_14, false) , Toast.LENGTH_SHORT).show();
    }
}
