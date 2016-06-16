package com.mutecsoft.storingmethodfordata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPreferences, btnFiles, btnDatabase;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPreferences = (Button) findViewById(R.id.btnPreferences);
        btnFiles = (Button) findViewById(R.id.btnFiles);
        btnDatabase = (Button) findViewById(R.id.btnDatabase);

        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), PreferencesActivity.class);
                startActivity(i);
            }
        });

        btnFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), FilesActivity.class);
                startActivity(i);
            }
        });

    }
}
