package com.example.mutecsoft.myanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mutecsoft on 2016-04-12.
 */
public class LayoutActivity extends Activity {
    Button btnWeightLayout, btnRuntimeLayout;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_layout);

        btnWeightLayout = (Button) findViewById(R.id.btnWeightLayout);
        btnRuntimeLayout = (Button) findViewById(R.id.btnRuntimeLayout);

        btnWeightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), WeightLayoutActivity.class);
                startActivity(i);
            }
        });

        btnRuntimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), RuntimeLayoutActivity.class);
                startActivity(i);
            }
        });
    }
}
