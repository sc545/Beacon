package com.example.mutecsoft.mybroadcastreceiverapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by mutecsoft on 2016-04-22.
 */
public class StaticReceiver extends BroadcastReceiver {
    public static final String MY_ACTION = "com.example.mutecsoft.MY_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!MY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "Not equals", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(context, "MY_ACTION", Toast.LENGTH_SHORT).show();
    }
}
