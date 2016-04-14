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

    // ui 화면의 상태를 보여줄 애니메이션 변수
    ImageView ivThread;
    AnimationDrawable animationDrawable;

    // 작업 스레드에서 ui 갱신을 할 수 있도록 핸들러 변수 선언
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

        // ui 스레드에서 longTask 수행
        btnOnUiThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                longTask(mHandler, "UI Thread");
            }
        });

        // 작업 스레드에서 longTask 수행
        btnOnWorkerThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        longTask(mHandler, "Worker Thread");
                    }
                }).start();

            }
        });

    }

    private void longTask(Handler handler, final String name){

        try {
            Thread.sleep(5000);
            // view.post() x
            handler.post(new Runnable() {

                @Override
                public void run() {

                    Toast.makeText(getApplicationContext(), name+" longTask 완료", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
