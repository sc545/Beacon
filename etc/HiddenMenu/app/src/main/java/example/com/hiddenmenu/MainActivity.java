package example.com.hiddenmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMenu[] = new Button[5];
    int menuId[] = {R.id.btnMenu1, R.id.btnMenu2, R.id.btnMenu3, R.id.btnMenu4, R.id.btnMenu5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListener listener = new MyListener();
        for(int i=0; i<5; i++) {
            btnMenu[i] = (Button) findViewById(menuId[i]);
            btnMenu[i].setOnClickListener(listener);
        }
    }

    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_MAIN);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            switch (v.getId()){
                case R.id.btnMenu1 : i.setClassName("com.hidden.netmode", "com.hidden.netmode.NetworkMode"); break;
                case R.id.btnMenu2 : i.setClassName("com.android.simlock", "com.android.simlock.BipActivation"); break;
                case R.id.btnMenu3 : i.setClassName("com.hidden.engmenu", "com.hidden.engmenu.EngineeringMenu"); break;
                case R.id.btnMenu4 : i.setClassName("com.hidden.debugscreen", "com.hidden.debugscreen.DebugScreen"); break;
                case R.id.btnMenu5 : i.setClassName("com.hidden.gpsscreen", "com.hidden.gpsscreen.GpsScreen"); break;
            }
            startActivity(i);
        }
    }
}
