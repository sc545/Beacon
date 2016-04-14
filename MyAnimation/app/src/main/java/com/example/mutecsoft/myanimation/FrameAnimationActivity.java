package com.example.mutecsoft.myanimation;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class FrameAnimationActivity extends Activity {
    Button btnRun, btnJump, btnAttack; // 달리기, 점프, 공격 버튼
    ImageView imageView; // 캐릭터 이미지 뷰
    AnimationDrawable animationDrawable; // 프레임 애니메이션을 만들 AnimationDrawable 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        btnRun = (Button) findViewById(R.id.btnRun);
        btnJump = (Button) findViewById(R.id.btnJump);
        btnAttack = (Button) findViewById(R.id.btnAttack);

        imageView = (ImageView) findViewById(R.id.iv);

        // 이미지 뷰에서 Drawable 객체를 가져옴.
        animationDrawable = (AnimationDrawable) imageView.getDrawable();

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 애니메이션이 실행 중 이라면 정지
                if(animationDrawable.isRunning())
                    animationDrawable.stop();

                // 이미지를 달리기로 변경 하고 Drawable 객체 가져옴
                imageView.setImageResource(R.drawable.pink_run);
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(animationDrawable.isRunning())
                    animationDrawable.stop();

                // 이미지를 점프로 변경 하고 Drawable 객체 가져옴
                imageView.setImageResource(R.drawable.pink_jump);
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(animationDrawable.isRunning())
                    animationDrawable.stop();

                // 이미지를 공격으로 변경 하고 Drawable 객체 가져옴
                imageView.setImageResource(R.drawable.pink_attack);
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });

        animationDrawable.start();

    }
}
