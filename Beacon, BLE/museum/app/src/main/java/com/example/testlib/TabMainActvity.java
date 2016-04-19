package com.example.testlib;

import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.mutecsoft.sdk.Beacon;
import com.mutecsoft.sdk.BeaconManager;
import com.mutecsoft.sdk.Region;
import com.mutecsoft.sdk.Utils;
import com.mutecsoft.sdk.Utils.Proximity;
import com.mutecsoft.sdk.service.BeaconService;
import com.mutecsoft.sdk.service.BeaconService.LocalBinder;

public class TabMainActvity extends TabActivity {
	
	
	BeaconManager manager;
	Beacon mCurrent_beacon = null;
	private final int ADD_RANGING_NOTI = 0;
	private final int ADD_REGION_NOTI = 1;
	public static final String Home_URL = "file:///android_res/drawable/main2.jpg";
	private final String Beacon1_URL = "http://mutec-museum.appspot.com/relic1.jpg";
	private final String Beacon2_URL = "http://mutec-museum.appspot.com/relic2.jpg";
	boolean mflag_serviceconnection;
	boolean mflag_restart = false;;
	BeaconService mService;
	private TabHost host;
	private String Current_Url = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.tabview_layout);
		String action = getIntent().getAction();
		if(action != null && action.equals("com.mutecsoft.lib.reStartActvitiy")) {
			mflag_restart = true;
		}
		
		host = getTabHost();
		
		host.setup();
		LayoutInflater vi1 = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		String[] labels = {"Main", "Exhibit", "Serach", "Map", "Info"};
		Intent[] intents = {new Intent(this, MainActivity.class), new Intent(this, ContentActivity.class), new Intent(this, SearchActivity.class), new Intent(this, MapActivity.class),
				new Intent(this, InfoActivity.class)};
		int[] icons = {R.drawable.home, R.drawable.antique_vase, R.drawable.search2x, R.drawable.near_me2x, R.drawable.info};
		for(int index = 0; index < 5; index++) {
			View v = (View)vi1.inflate(R.layout.tab_item, null);
			TextView textview = (TextView) v.findViewById(R.id.tab_label);
			textview.setText(labels[index]);
			ImageView image = (ImageView) v.findViewById(R.id.tab_icon);
			image.setImageResource(icons[index]);
			Intent intent = intents[index];
			intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			host.addTab(host.newTabSpec(labels[index]).setIndicator(v).setContent(intent));
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		manager = BeaconService.getBeaconManager();
		if(manager == null) {
			Intent intent = new Intent(this, BeaconService.class);
			bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
			mflag_serviceconnection = true;
		}else {
			manager.setMoniteringListener(new MonitorListener());
			manager.setRangingListener(new RangeListener());
			manager.stopScan();
			manager.startScan();
			mflag_serviceconnection = false;
		}
	}
	
	public class MonitorListener implements BeaconManager.MonitoringListener {
		
		@Override
		public void onEnteredRegion(Region region) {
//			Toast.makeText(getApplicationContext(), "Region in " + region.getIdentifier(), Toast.LENGTH_SHORT).show();
			addNotification(new Intent(TabMainActvity.this, TabMainActvity.class), "�����߾� �ڹ���", "�����߾� �ڹ��� �湮�� ȯ���մϴ�.", ADD_REGION_NOTI);
			PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
			if(!manager.isScreenOn()) {
				Intent intent = new Intent(TabMainActvity.this, PopupToast.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				TabMainActvity.this.getApplicationContext().startActivity(intent);
			}else {
				addToastView("�ڹ����� �湮�� �ּż� �����մϴ�.");
			}
		}
	
		@Override
		public void onExitRegion(Region region) {
			Toast.makeText(getApplicationContext(), "Region out " + region.getIdentifier(), Toast.LENGTH_SHORT).show();
			addNotification(new Intent(TabMainActvity.this, TabMainActvity.class), "�����߾� �ڹ���", "�����߾� �ڹ����� �湮�Ͽ� �ּż� �����մϴ٤�.\r\n �ȳ��� ���ư��ʽÿ�.", ADD_REGION_NOTI);
			PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
			if(!manager.isScreenOn()) {
				Intent intent = new Intent(TabMainActvity.this, PopupToast.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				TabMainActvity.this.getApplicationContext().startActivity(intent);
			}else {
				addToastView("�ȳ��� ���ư��ʽÿ�.");
			}
		}
	}

	public class RangeListener implements BeaconManager.RangingListener {
	
		@Override
		public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
			if(!beacons.isEmpty() && host.getCurrentTab() == 1) {
				Beacon beacon = beacons.get(0);
				Proximity state = Utils.computeProximity(beacon);
				boolean flag_background = Utils.isAppOnForeground(TabMainActvity.this);
				String url = checkCurrentUrl();
				if(state.equals(Utils.Proximity.IMMEDIATE)) {
					if(mCurrent_beacon == null) {
						if(flag_background) {
							ContentActivity.reLoadContent(url);
							Current_Url = url;
						}
					}else {
						if(!mCurrent_beacon.getMacAddress().equals(beacon.getMacAddress())) {
							if(flag_background) {
								ContentActivity.reLoadContent(url);
								Current_Url = url;
							}
						}else {
							if(ContentActivity.content != null && ContentActivity.content.getUrl().equals(Home_URL)) {
								ContentActivity.reLoadContent(url);
								Current_Url = url;
							}
						}
					}
					mCurrent_beacon = beacon;
				}else {
					if(mCurrent_beacon != null && mCurrent_beacon.getMacAddress().equals(beacon.getMacAddress())){
						if(ContentActivity.content!= null && !ContentActivity.content.getUrl().equals(Home_URL)) {
							Utils.showToast(TabMainActvity.this, "���ܰ� �־������ϴ�. Ȩȭ������ ���ư��ϴ�.");
							ContentActivity.reLoadContent(Home_URL);
						}
					}
					mCurrent_beacon = beacon;
				}
			}
			
		}
	
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		mflag_pause = true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(manager != null) {
			manager.stopScan();
			manager.startScan();
		}
//		mCurrent_beacon = null;
	}
	
	@Override
	public void onDestroy() {
		if(mflag_serviceconnection){
			unbindService(mConnection);
		}
		super.onDestroy();
	}
	
	private void addToastView(String text) {
		Toast toast = new Toast(this);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.popuplayout, null);
		LinearLayout layout = (LinearLayout)v.findViewById(R.id.popup_layout);
		TextView textview = (TextView) v.findViewById(R.id.text_view2);
		textview.setText(text);
		toast.setView(layout);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}
	
	private void addNotification(Intent intent, String title, String text, int flag) {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(TabMainActvity.this)
		.setSmallIcon(R.drawable.icon)
		.setContentTitle(title)
		.setContentText(text);
		switch (flag) {
			case ADD_RANGING_NOTI: {
				getTabHost().setCurrentTab(1);
				ContentActivity.reLoadContent("http://museum.mutecsoft.com/relic2.jpg");
				break;
			}case ADD_REGION_NOTI: {
				
			}
		}
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		mBuilder.setContentIntent(resultPendingIntent);
		mBuilder.setAutoCancel(true);
		Notification noti = mBuilder.build();
		noti.defaults |= Notification.DEFAULT_VIBRATE;
		noti.defaults |= Notification.DEFAULT_SOUND;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, noti);
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            manager = mService.getBeaconManager();
            if(manager == null) {
            	mService.init(TabMainActvity.this);
            	Region region = new Region("Museaum", "FB882FE377C84EACBFE00FC3DC909B19", null, null);
            	manager = mService.getBeaconManager();
            	manager.startMonitoring(region);
            }
			BeaconService.setBeaconManage(manager);
			manager.setMoniteringListener(new MonitorListener());
			manager.setRangingListener(new RangeListener());
			manager.mService = mService;
			manager.startScan();
			if(mflag_restart) {
				finish();
			}
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
//            mBound = false;
        }
    };
    
    private String checkCurrentUrl() {
    	if(Current_Url == null) {
    		return Beacon1_URL;
    	}else if(Current_Url.equals(Beacon1_URL)) {
    		return Beacon2_URL;
    	}else {
    		return Beacon1_URL;
    	}
    }
	
}
