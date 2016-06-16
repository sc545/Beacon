package com.mutecsoft.preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);

        SharedPreferences sp = getSharedPreferences("t", MODE_WORLD_READABLE);
        int value = sp.getInt("t", -1);
        tv.setText("value : "+value);
    }
}
