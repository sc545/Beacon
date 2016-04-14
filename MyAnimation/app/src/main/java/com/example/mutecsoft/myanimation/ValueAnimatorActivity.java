package com.example.mutecsoft.myanimation;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mutecsoft on 2016-04-14.
 */
public class ValueAnimatorActivity extends Activity {
    Button btnSizeUp, btnSizeDown, btnRotate, btnValueTrans; // 버튼의 크기 키우기, 줄이기 회전할 버튼
    TextView tvValue; // 애니메이션 Value를 보여줄 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_animator_layout);

        btnSizeUp = (Button) findViewById(R.id.btnSizeUp);
        btnSizeDown = (Button) findViewById(R.id.btnSizeDown);
        btnRotate = (Button) findViewById(R.id.btnRotate);
        btnValueTrans = (Button) findViewById(R.id.btnValueTrans);
        tvValue = (TextView) findViewById(R.id.tvValue);


        btnSizeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // ValueAnimator 객체 생성
                ValueAnimator valueAnimator = new ValueAnimator();
                // ValueAnimator의 Value 값 설정
                // 애니메이션이 시작되면 (start, end) start 값 부터 end 값 까지 진행
                valueAnimator.setObjectValues(0, 450);
                // 애니메이션에 지속되는 기간 설정
                valueAnimator.setDuration(1000);

                // 애니메이션 업데이트 리스너 등록
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    // 애니메이션의 매 프레임마다 동작
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        // 애니메이션의 value 를 가져와서 텍스트뷰에 뿌려주고 Value 값 만큼 버튼의 크기를 조정
                        int value = (int) animation.getAnimatedValue();
                        tvValue.setText("Value : " + value);
                        btnValueTrans.setWidth(value);
                        btnValueTrans.setHeight(value);

                    }
                });

                // 애니메이션 시작
                valueAnimator.start();
            }
        });

        btnSizeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setObjectValues(btnValueTrans.getWidth(), 0);
                valueAnimator.setDuration(1000);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {


                        int value = (int) animation.getAnimatedValue();
                        tvValue.setText("Value : " + value);
                        btnValueTrans.setWidth(value);
                        btnValueTrans.setHeight(value);
                    }
                });
                valueAnimator.start();
            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setObjectValues(0, 720);
                valueAnimator.setDuration(10000);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        // 애니메이션의 value 를 가져와서 텍스트뷰에 뿌려주고 Value 값 만큼 버튼의 로테이션 조정
                        int value = (int) animation.getAnimatedValue();
                        tvValue.setText("Value : " + value);

                        // 360 까지는 X축으로 회전 그 이상 부터는 Y축으로 회전
                        if(value < 360)
                            btnValueTrans.setRotationX(value);
                        else
                            btnValueTrans.setRotationY(value);

//                        btnValueTrans.setRotationX(value);
//                        btnValueTrans.setRotationY(value);
//                        btnValueTrans.setRotation(value);

                    }
                });
                valueAnimator.start();
            }
        });



//        valueAnimator.setEvaluator(new TypeEvaluator<Integer>() {
//            @Override
//            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
//                return Math.round(startValue+(endValue - startValue)*fraction);
//            }
//        });



    }

}
