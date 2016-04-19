package com.example.mutecsoft.myanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.Random;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class ObjectAnimatorActivity extends Activity {
    Button btnObjectTrans; // 애니메이션 효과를 줄 버튼

    // 화면 사이즈를 담을 변수
    int screenWidth, screenHeight;
    // 버튼의 최근 좌표를 가지고 있을 변수
    int btnLastX, btnLastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_animator_layout);

        // 화면 사이즈 가져오기
        screenWidth = getApplicationContext().getResources().getDisplayMetrics().widthPixels/2;
        screenHeight = getApplicationContext().getResources().getDisplayMetrics().heightPixels/2;

        btnObjectTrans = (Button) findViewById(R.id.btnObjectTrans);

        btnObjectTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 화면 사이즈 내에서 랜덤한 정수 뽑기
                int x = (int) (Math.random()*(screenWidth+screenWidth)-screenWidth);
                int y = (int) (Math.random()*(screenHeight+screenHeight)-screenHeight);

                // ObjectAnimator 객체 생성 (애니메이션을 적용 할 객체, 애니메이션 종류, 변경할 값)
                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(btnObjectTrans, "translationX", btnLastX, x);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(btnObjectTrans, "translationY", btnLastY, y);

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnObjectTrans, "alpha", 0, 1);
                objectAnimator.setDuration(1000);
                btnLastX = x;
                btnLastY = y;

                // AnimatorSet 객체 생성
                AnimatorSet animatorSet = new AnimatorSet();
                // AnimtorSet 으로 사용할 애니메이션을 묶는다
                animatorSet.playTogether(objectAnimatorX, objectAnimatorY, objectAnimator);
                // 애니메이션 시작
                animatorSet.start();

            }
        });
    }
}
