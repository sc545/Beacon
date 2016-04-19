package com.example.mutecsoft_pc.mysensorapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Mutecsoft_pc on 2016-04-04.
 */
public class ShakeActivity extends Activity implements SensorEventListener {
    private long lastTime; // 가장 최근 센서가 변경 되었을 때의 시간
    private float speed; // 가속도 값
    private float lastX; // 가장 최근 센서가 변경 되었을 때의 x 값
    private float lastY; // 가장 최근 센서가 변경 되었을 때의 y 값
    private float lastZ; // 가장 최근 센서가 변경 되었을 때의 z 값
    private float x, y, z; // 현재 센서의 x, y, z 값
    private int shakeCount; // 폰이 흔들렸을 때 카운트 할 변수
    private TextView tv; // shakeCount 를 표시해줄 TextView

    private static final int SHAKE_THRESHOLD = 800; // 속도가 얼마 이상일 때, 흔듬을 감지하겠다는 것을 설정해주는 변수
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_layout);
        // SensorManager 인스턴스를 가져옴
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 가속도 센서
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (accelerormeterSensor != null)
            // 가속도 센서 리스너 오브젝트를 등록
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            // 센서에서 이벤트 리스너 분리
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    // 센서의 값이 변경 되었을 때 수행
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[DATA_X];
                y = event.values[DATA_Y];
                z = event.values[DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    // 이벤트발생!!
                    shakeCount++;
                    tv.setText(String.format("%d", shakeCount));

                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }

        }

    }

}
