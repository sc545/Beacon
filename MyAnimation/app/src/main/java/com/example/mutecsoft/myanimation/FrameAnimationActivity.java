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
    Button btnRun, btnJump, btnAttack, btnHidden;
    ImageView imageView;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        btnRun = (Button) findViewById(R.id.btnRun);
        btnJump = (Button) findViewById(R.id.btnJump);
        btnAttack = (Button) findViewById(R.id.btnAttack);

        imageView = (ImageView) findViewById(R.id.iv);

        animationDrawable = (AnimationDrawable) imageView.getDrawable();

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(animationDrawable.isRunning())
                    animationDrawable.stop();

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

                imageView.setImageResource(R.drawable.pink_attack);
                animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });

        animationDrawable.start();

    }
}
