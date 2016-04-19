package com.example.mutecsoft_pc.mysensorapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mutecsoft_pc on 2016-04-04.
 */
public class SensorListActivity extends ListActivity {
    final static String TAG = SensorListActivity.class.getSimpleName();
    SensorManager manager;
    List<Sensor> sensors;
    SensorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = manager.getSensorList(Sensor.TYPE_ALL);

        adapter = new SensorListAdapter(this, R.layout.listitem, sensors);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Sensor sensor = sensors.get(position);
        String sensorName = sensor.getName();
        Log.d(TAG, "선택된 센서 : "+sensorName);
        Intent i = new Intent(this, SensorDataActivity.class);
        i.putExtra(SensorDataActivity.SENSOR_INDEX, position);
        startActivity(i);
    }
}
