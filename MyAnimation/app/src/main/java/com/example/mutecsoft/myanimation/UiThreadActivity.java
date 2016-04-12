package com.example.mutecsoft.myanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class UiThreadActivity extends Activity {
    Button btnOnUiThread, btnOnWorkerThread;
    ImageView ivThread;
    AnimationDrawable animationDrawable;

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_thread_layout);

        btnOnUiThread = (Button) findViewById(R.id.btnOnUiThread);
        btnOnWorkerThread = (Button) findViewById(R.id.btnOnWorkerThread);
        ivThread = (ImageView) findViewById(R.id.ivThread);

        animationDrawable = (AnimationDrawable) ivThread.getDrawable();
        animationDrawable.start();

        mHandler = new Handler();

        btnOnUiThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                longTask(mHandler);
            }
        });

        btnOnWorkerThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        longTask(mHandler);
                    }
                }).start();

            }
        });

    }

    private void longTask(Handler handler){

        try {
            Thread.sleep(5000);
            handler.post(new Runnable() {

                @Override
                public void run() {

                    Toast.makeText(getApplicationContext(), "longTask 완료", Toast.LENGTH_LONG).show();
                }
            });

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
