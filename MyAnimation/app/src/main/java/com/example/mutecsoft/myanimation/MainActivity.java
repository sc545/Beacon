package com.example.mutecsoft.myanimation;

import android.animation.Animator;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    AnimationDrawable ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);

        ani = (AnimationDrawable) iv.getDrawable();


        ((Button)findViewById(R.id.btnRun)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ani.isRunning())
                    ani.stop();
                iv.setImageResource(R.drawable.pink_run);
                Animation animation = new AlphaAnimation(0f, 1f);
                animation.setDuration(500);
                iv.startAnimation(animation);
                ani = (AnimationDrawable) iv.getDrawable();
                ani.start();
            }
        });
        ((Button)findViewById(R.id.btnJump)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ani.isRunning())
                    ani.stop();
                iv.setImageResource(R.drawable.pink_jump);
                ani = (AnimationDrawable) iv.getDrawable();
                ani.start();

            }
        });

        ((Button)findViewById(R.id.btnAttack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ani.isRunning())
                    ani.stop();
                iv.setImageResource(R.drawable.pink_attack);
                ani = (AnimationDrawable) iv.getDrawable();
                ani.start();
            }
        });


        ani.start();
    }
}
