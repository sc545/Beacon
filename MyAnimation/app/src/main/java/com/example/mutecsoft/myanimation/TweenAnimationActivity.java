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
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class TweenAnimationActivity extends Activity {
    Button btnTweenTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tween_animation_layout);

        btnTweenTrans = (Button) findViewById(R.id.btnTweenTrans);

        btnTweenTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation = new TranslateAnimation(0f, 100f, 0f, 100f);
                animation.setDuration(300);
                btnTweenTrans.startAnimation(animation);

            }
        });
    }
}
