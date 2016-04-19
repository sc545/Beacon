package com.example.mutecsoft.myanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button btnLayout, btnAnimation, btnUiThread;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLayout = (Button) findViewById(R.id.btnLayout);
        btnAnimation = (Button) findViewById(R.id.btnAnimation);
        btnUiThread = (Button) findViewById(R.id.btnUiThread);

        btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i  = new Intent(getApplicationContext(), LayoutActivity.class);
                startActivity(i);
            }
        });

        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i  = new Intent(getApplicationContext(), AnimationActivity.class);
                startActivity(i);
            }
        });

        btnUiThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i  = new Intent(getApplicationContext(), UiThreadActivity.class);
                startActivity(i);
            }
        });

    }
}

