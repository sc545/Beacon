package com.example.testlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BeaconReceiver extends BroadcastReceiver {
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
			Log.e("BeaconReceiver", "received intent");
			Intent i = new Intent(context, TabMainActvity.class);
			i.setAction("com.mutecsoft.lib.reStartActvitiy");
//			i.addCategory(Intent.CATEGORY_LAUNCHER);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			i.setComponent(new ComponentName("com.example.testlib", "TabMainActvity"));
			context.startActivity(i);
	}
	
	
}
