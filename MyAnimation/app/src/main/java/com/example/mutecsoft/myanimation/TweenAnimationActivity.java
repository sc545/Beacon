package com.example.mutecsoft.myanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class TweenAnimationActivity extends Activity {
    Button btnTweenTrans; // 애니메이션 효과를 줄 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tween_animation_layout);

        btnTweenTrans = (Button) findViewById(R.id.btnTweenTrans);

        btnTweenTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이동 애니메이션 객체 생성 (x1, x2, y1, y2) y좌표 0 -> 150 이동
                Animation animation = new TranslateAnimation(0f, 0f, 0f, 500f);

                // 지속되는 기간 설정 5000 millis
                animation.setDuration(5000);

                // xml 로 설정 할 경우
//                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_animation);

                // 애니메이션 시작
                btnTweenTrans.startAnimation(animation);


            }
        });
    }
}
