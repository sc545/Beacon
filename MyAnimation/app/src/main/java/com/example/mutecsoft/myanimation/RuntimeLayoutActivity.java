package com.example.mutecsoft.myanimation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by mutecsoft on 2016-04-14.
 */
public class RuntimeLayoutActivity extends Activity {
    Button btn1, btn2, btn3;

    // 화면 사이즈를 담을 변수
    int screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runtime_layout_layout);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        // sdk 버전 별로 다르게 단말별로 차이가없는 api
        //
        // 액티비티 생성시 화면 크기를 가져옴
        screenWidth = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

        // 화면 크기에 맞게 버튼 크기 조정
        btn1.setWidth(screenWidth);
        btn2.setWidth(screenWidth);
        btn3.setWidth(screenWidth);

        btn1.setHeight(screenHeight/6);
        btn2.setHeight(screenHeight/3);
        btn3.setHeight(screenHeight/2);

    }
}
