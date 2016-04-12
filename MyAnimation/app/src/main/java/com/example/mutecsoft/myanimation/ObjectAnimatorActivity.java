package com.example.mutecsoft.myanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class ObjectAnimatorActivity extends Activity {
    Button btnObjectTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_animator_layout);

        btnObjectTrans = (Button) findViewById(R.id.btnObjectTrans);

        btnObjectTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(btnObjectTrans, "translationX", 0f, 100f);
                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(btnObjectTrans, "translationY", 0f, 100f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
                animatorSet.start();
            }
        });
    }
}
