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
public class AnimationActivity extends Activity {
    Button btnTweenAnim, btnFrameAnim, btnObjectAnim;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);

        btnTweenAnim = (Button) findViewById(R.id.btnTweenAnim);
        btnFrameAnim = (Button) findViewById(R.id.btnFrameAnim);
        btnObjectAnim = (Button) findViewById(R.id.btnObjectAnim);

        btnTweenAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), TweenAnimationActivity.class);
                startActivity(i);
            }
        });

        btnFrameAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), FrameAnimationActivity.class);
                startActivity(i);
            }
        });

        btnObjectAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), ObjectAnimatorActivity.class);
                startActivity(i);
            }
        });

    }
}

