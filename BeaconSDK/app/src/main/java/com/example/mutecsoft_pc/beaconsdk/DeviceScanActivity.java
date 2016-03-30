package com.example.mutecsoft_pc.beaconsdk;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.mutecsoft.sdk.Beacon;
import com.mutecsoft.sdk.BeaconManager;
import com.mutecsoft.sdk.Region;
import com.mutecsoft.sdk.Utils;
import com.mutecsoft.sdk.service.BeaconService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mutecsoft_pc on 2016-03-30.
 */
public class DeviceScanActivity extends ListActivity{
    private final static String TAG = DeviceScanActivity.class.getSimpleName();
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

    private LeDeviceListAdapter mLeDeviceListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
//            addNotification(new Intent(TabMainActvity.this, TabMainActvity.class), "�����߾� �ڹ���", "�����߾� �ڹ��� �湮�� ȯ���մϴ�.", ADD_REGION_NOTI);
//            PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//            if(!manager.isScreenOn()) {
//                Intent intent = new Intent(TabMainActvity.this, PopupToast.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                TabMainActvity.this.getApplicationContext().startActivity(intent);
//            }else {
//                addToastView("�ڹ����� �湮�� �ּż� �����մϴ�.");
//            }
        }

        @Override
        public void onExitRegion(Region region) {
//            Toast.makeText(getApplicationContext(), "Region out " + region.getIdentifier(), Toast.LENGTH_SHORT).show();
//            addNotification(new Intent(TabMainActvity.this, TabMainActvity.class), "�����߾� �ڹ���", "�����߾� �ڹ����� �湮�Ͽ� �ּż� �����մϴ٤�.\r\n �ȳ��� ���ư��ʽÿ�.", ADD_REGION_NOTI);
//            PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//            if(!manager.isScreenOn()) {
//                Intent intent = new Intent(TabMainActvity.this, PopupToast.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                TabMainActvity.this.getApplicationContext().startActivity(intent);
//            }else {
//                addToastView("�ȳ��� ���ư��ʽÿ�.");
//            }
        }
    }



    public class RangeListener implements BeaconManager.RangingListener {

        @Override
        public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
            if(!beacons.isEmpty() && host.getCurrentTab() == 1) {
                Beacon beacon = beacons.get(0);
                Utils.Proximity state = Utils.computeProximity(beacon);
                boolean flag_background = Utils.isAppOnForeground(DeviceScanActivity.this);
                String url = checkCurrentUrl();
                Log.d(TAG, "beacon : "+beacons.get(0).getName());
                mLeDeviceListAdapter.addDevice(beacon);
                mLeDeviceListAdapter.notifyDataSetChanged();
                invalidateOptionsMenu();
//                if(state.equals(Utils.Proximity.IMMEDIATE)) {
//                    if(mCurrent_beacon == null) {
//                        if(flag_background) {
//                            ContentActivity.reLoadContent(url);
//                            Current_Url = url;
//                        }
//                    }else {
//                        if(!mCurrent_beacon.getMacAddress().equals(beacon.getMacAddress())) {
//                            if(flag_background) {
//                                ContentActivity.reLoadContent(url);
//                                Current_Url = url;
//                            }
//                        }else {
//                            if(ContentActivity.content != null && ContentActivity.content.getUrl().equals(Home_URL)) {
//                                ContentActivity.reLoadContent(url);
//                                Current_Url = url;
//                            }
//                        }
//                    }
//                    mCurrent_beacon = beacon;
//                }else {
//                    if(mCurrent_beacon != null && mCurrent_beacon.getMacAddress().equals(beacon.getMacAddress())){
//                        if(ContentActivity.content!= null && !ContentActivity.content.getUrl().equals(Home_URL)) {
//                            Utils.showToast(TabMainActvity.this, "���ܰ� �־������ϴ�. Ȩȭ������ ���ư��ϴ�.");
//                            ContentActivity.reLoadContent(Home_URL);
//                        }
//                    }
//                    mCurrent_beacon = beacon;
//                }
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
//        Toast toast = new Toast(this);
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.popuplayout, null);
//        LinearLayout layout = (LinearLayout)v.findViewById(R.id.popup_layout);
//        TextView textview = (TextView) v.findViewById(R.id.text_view2);
//        textview.setText(text);
//        toast.setView(layout);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.show();
    }

    private void addNotification(Intent intent, String title, String text, int flag) {
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(TabMainActvity.this)
//                .setSmallIcon(R.drawable.icon)
//                .setContentTitle(title)
//                .setContentText(text);
//        switch (flag) {
//            case ADD_RANGING_NOTI: {
//                getTabHost().setCurrentTab(1);
//                ContentActivity.reLoadContent("http://museum.mutecsoft.com/relic2.jpg");
//                break;
//            }case ADD_REGION_NOTI: {
//
//            }
//        }
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        mBuilder.setContentIntent(resultPendingIntent);
//        mBuilder.setAutoCancel(true);
//        Notification noti = mBuilder.build();
//        noti.defaults |= Notification.DEFAULT_VIBRATE;
//        noti.defaults |= Notification.DEFAULT_SOUND;
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(0, noti);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            BeaconService.LocalBinder binder = (BeaconService.LocalBinder) service;
            mService = binder.getService();
            manager = mService.getBeaconManager();
            if(manager == null) {
                mService.init(DeviceScanActivity.this);
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



    // Adapter for holding devices found through scanning.
    private class LeDeviceListAdapter extends BaseAdapter {
        private ArrayList<Beacon> mLeDevices;
        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<Beacon>();
            mInflator = DeviceScanActivity.this.getLayoutInflater();
        }

        public void addDevice(Beacon device) {
            if(!mLeDevices.contains(device)) {
                mLeDevices.add(device);
            }
        }

        public Beacon getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.listitem_device, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            Beacon device = mLeDevices.get(i);
            final String deviceName = device.getName();
            if (deviceName != null && deviceName.length() > 0)
                viewHolder.deviceName.setText(deviceName);
            else
                viewHolder.deviceName.setText(R.string.unknown_device);
            viewHolder.deviceAddress.setText(device.getMacAddress());

            return view;
        }
    }

    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
}
