package com.mutecsoft.storingmethodfordata;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by mutecsoft on 2016-06-15.
 */
public class FilesActivity extends Activity {

    private static final int PERMISSION_REQUEST_CODE = 999;
    private static final String FILE_NAME = "Memo.txt";

    String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    Button btnRead, btnSave, btnDelete;
    EditText et;
    Spinner spinPath;
    String selectedPath;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        if(checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED){
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }

        btnRead = (Button) findViewById(R.id.btnRead);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        et = (EditText) findViewById(R.id.et);
        spinPath = (Spinner) findViewById(R.id.spinPath);

        try {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                Toast.makeText(this, "SD Card does not exist", Toast.LENGTH_SHORT).show();
                finish();
            }
            String[] pathList = Environment.getExternalStorageDirectory().getAbsoluteFile().list().
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, );
            spinPath.setAdapter(adapter);
        } catch (Exception e){
            e.printStackTrace();
        }


        spinPath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String path = spinPath.getSelectedItem().toString();

                switch (path){
                    case "App" :
                        selectedPath = "App";
                        break;
                    case "Download" :
                        selectedPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/"+FILE_NAME;
                        file = new File(selectedPath);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        BtnListener btnListener = new BtnListener();
        btnRead.setOnClickListener(btnListener);
        btnSave.setOnClickListener(btnListener);
        btnDelete.setOnClickListener(btnListener);

    }

    class BtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btnRead :
                    try {
                        FileInputStream fis = null;
                        if (selectedPath.equals("App")){
                            fis = openFileInput(FILE_NAME);
                        }else{
                            fis = new FileInputStream(file);
                        }
                        byte[] memoData = new byte[fis.available()];
                        while (fis.read(memoData) != -1){}
                        et.setText(new String(memoData));
                        Toast.makeText(getApplicationContext(), "Completed read", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnSave :
                    try {
                        FileOutputStream fos = null;
                        if (selectedPath.equals("App")){
                            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                        }else{
                            fos = new FileOutputStream(file);
                        }
                        fos.write(et.getText().toString().getBytes());
                        Toast.makeText(getApplicationContext(), "Completed save", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnDelete :
                    try {
                        if (selectedPath.equals("App")){
                            if (deleteFile(FILE_NAME)) {
                                Toast.makeText(getApplicationContext(), "Completed delete", Toast.LENGTH_SHORT).show();
                                et.setText("");
                            }
                        }else{
                            if (file.delete()) {
                                Toast.makeText(getApplicationContext(), "Completed delete", Toast.LENGTH_SHORT).show();
                                et.setText("");
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE){
            if(checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED){
                Toast.makeText(getApplicationContext(), "PERMISSION_DENIED", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
