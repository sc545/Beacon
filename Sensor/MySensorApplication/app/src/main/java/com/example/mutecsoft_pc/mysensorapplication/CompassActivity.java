package com.example.mutecsoft_pc.mysensorapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mutecsoft_pc on 2016-04-04.
 */
public class CompassActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor orientationSensor;

    ImageView iv;
    TextView tvCompass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_layout);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        tvCompass = (TextView) findViewById(R.id.tvCompass);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(orientationSensor!=null)
            sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(sensorManager!=null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
//            iv.setRotation(event.values[SensorManager.DATA_X]);
            float azimuth = event.values[SensorManager.DATA_X];
            if(azimuth > 337.5 || azimuth <=22.5)
                tvCompass.setText("북");
            else if(azimuth > 22.5 && azimuth <=67.5)
                tvCompass.setText("북동");
            else if(azimuth > 67.5 && azimuth <=112.5)
                tvCompass.setText("동");
            else if(azimuth > 112.5 && azimuth <=157.5)
                tvCompass.setText("남동");
            else if(azimuth > 157.5 && azimuth <=202.5)
                tvCompass.setText("남");
            else if(azimuth > 202.5 && azimuth <=247.5)
                tvCompass.setText("남서");
            else if(azimuth > 247.5 && azimuth <=292.5)
                tvCompass.setText("서");
            else if(azimuth > 292.5 && azimuth <=337.5)
                tvCompass.setText("북서");

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
