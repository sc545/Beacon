package com.mutecsoft.storingmethodfordata;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Permission;

/**
 * Created by mutecsoft on 2016-06-15.
 */
public class FilesActivity extends Activity {

    private static final String FILE_NAME = "Memo.txt";

    Button btnRead, btnSave, btnDelete;
    EditText et;

    File file;

    String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        et = (EditText) findViewById(R.id.et);

        String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

        if(checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_DENIED){
            requestPermissions(permissions, 0);
        }

        // 파일 생성및 데이터 저장
        try {
            FileOutputStream fos = openFileOutput("file_name", MODE_PRIVATE);
            fos.write("strData".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        // 파일 데이터 읽기
        try {
            FileInputStream fis = openFileInput("file_name");
            byte[] bytesData = new byte[fis.available()];
            while (fis.read(bytesData) != -1) {}
            String strData = new String(bytesData);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/poiu.txt");
            if (file!=null && !file.exists()){
                try {
                    file.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            Toast.makeText(getApplicationContext(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
        }

        if (file!=null && !file.exists()){
            try {
                file.createNewFile();
                Toast.makeText(getApplicationContext(), "new", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("아니바그아고야".getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }



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
                        FileInputStream fis = openFileInput(FILE_NAME);

                        byte[] memoData = new byte[fis.available()];
                        while (fis.read(memoData) != -1){

                        }
                        et.setText(new String(memoData));
                        Toast.makeText(getApplicationContext(), "Completed read", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnSave :
                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                        fos.write(et.getText().toString().getBytes());
                        Toast.makeText(getApplicationContext(), "Completed save", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnDelete :
                    if (deleteFile(FILE_NAME)) {
                        Toast.makeText(getApplicationContext(), "Completed delete", Toast.LENGTH_LONG).show();
                        et.setText("");
                    }

            }
        }
    }
}
