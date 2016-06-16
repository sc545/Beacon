package com.mutecsoft.storingmethodfordata;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.Map;

/**
 * Created by mutecsoft on 2016-06-14.
 */
public class PreferencesActivity extends Activity {
    private final int SPINNER_PREFS = 1;
    private final int SPINNER_KEY = 2;
    private final int SPINNER_ALL = 3;

    Spinner spinPrefs, spinKey, spinValueType;
    EditText etPrefs, etKey, etValue;
    Button btnAddPrefs, btnAddKey, btnSetValue, btnRemoveKey, btnClearAllKey;

    SharedPreferences selectedSP;
    SharedPreferences.Editor editor;

    int currentValueType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        spinPrefs = (Spinner) findViewById(R.id.spinPrefs);
        spinKey = (Spinner) findViewById(R.id.spinKey);
        spinValueType = (Spinner) findViewById(R.id.spinValueType);
        etPrefs = (EditText) findViewById(R.id.etPrefs);
        etKey = (EditText) findViewById(R.id.etKey);
        etValue = (EditText) findViewById(R.id.etValue);
        btnAddPrefs = (Button) findViewById(R.id.btnAddPrefs);
        btnAddKey = (Button) findViewById(R.id.btnAddKey);
        btnSetValue = (Button) findViewById(R.id.btnSetValue);
        btnRemoveKey = (Button) findViewById(R.id.btnRemoveKey);
        btnClearAllKey = (Button) findViewById(R.id.btnClearAllKey);

        invalidate(SPINNER_ALL);

        String[] valueTypeList = {"Boolean", "Int", "Float", "Long", "String"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, valueTypeList);
        spinValueType.setAdapter(adapter);

        spinPrefs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String prefItem = (String) spinPrefs.getSelectedItem();
                String prefFile = prefItem.substring(0, prefItem.length()-4);

                selectedSP = getSharedPreferences(prefFile, MODE_PRIVATE);

                invalidate(SPINNER_KEY);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinKey.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String keyItem = (String) spinKey.getSelectedItem();
                String value = null;
                boolean flag = false;
                if (!flag){
                    try {
                        value = "" + selectedSP.getBoolean(keyItem, false);
                        currentValueType = 0;
                        flag = true;
                    } catch (Exception e) {}
                }
                if (!flag){
                    try {
                        value = "" + selectedSP.getInt(keyItem, -1);
                        currentValueType = 1;
                        flag = true;
                    } catch (Exception e) {}
                }
                if (!flag){
                    try {
                        value = "" + selectedSP.getFloat(keyItem, -1);
                        currentValueType = 2;
                        flag = true;
                    } catch (Exception e) {}
                }
                if (!flag){
                    try {
                        value = "" + selectedSP.getLong(keyItem, -1);
                        currentValueType = 3;
                        flag = true;
                    } catch (Exception e) {}
                }
                if (!flag){
                    try {
                        value = "" + selectedSP.getString(keyItem, null);
                        currentValueType = 4;
                        flag = true;
                    } catch (Exception e) {}
                }
                etValue.setText(value);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinValueType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentValueType = spinValueType.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ButtonListener buttonListener = new ButtonListener();
        btnAddPrefs.setOnClickListener(buttonListener);
        btnAddKey.setOnClickListener(buttonListener);
        btnSetValue.setOnClickListener(buttonListener);
        btnRemoveKey.setOnClickListener(buttonListener);
        btnClearAllKey.setOnClickListener(buttonListener);

        etPrefs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = etPrefs.getText().toString();
                if (input == null || input.equals("") || input.contains(" "))
                    btnAddPrefs.setEnabled(false);
                else
                    btnAddPrefs.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input1 = etKey.getText().toString();
                String input2 = etValue.getText().toString();
                if (input1 == null || input1.equals("") || input1.contains(" "))
                    btnAddKey.setEnabled(false);
                else
                    if (input2 == null || input2.equals("") || input2.contains(" ")) {
                        btnAddKey.setEnabled(false);
                    } else{
                        btnAddKey.setEnabled(true);
                    }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input1 = etKey.getText().toString();
                String input2 = etValue.getText().toString();
                if (input2 == null || input2.equals("") || input2.contains(" ")) {
                    btnSetValue.setEnabled(false);
                    btnAddKey.setEnabled(false);
                }else {
                    btnSetValue.setEnabled(true);
                    if (input1 == null || input1.equals("") || input1.contains(" ")){
                        btnAddKey.setEnabled(false);
                    } else{
                        btnAddKey.setEnabled(true);
                    }
                }
                if (selectedSP != null)
                    if (selectedSP.getAll().size() == 0)
                        btnSetValue.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void putValue(Object o){
        String prefsName = null;
        if (o instanceof EditText){
            EditText etKey = (EditText) o;
            prefsName = etKey.getText().toString();
        }else{
            Spinner spinKey = (Spinner) o;
            prefsName = spinKey.getSelectedItem().toString();
        }
        editor = selectedSP.edit();
        currentValueType = spinValueType.getSelectedItemPosition();
        switch (currentValueType){
            case 0 :
                editor.putBoolean(prefsName, etValue.getText().toString().equalsIgnoreCase("true"));
                break;
            case 1 :
                editor.putInt(prefsName, Integer.parseInt(etValue.getText().toString()));
                break;
            case 2 :
                editor.putFloat(prefsName, Float.parseFloat(etValue.getText().toString()));
                break;
            case 3 :
                editor.putFloat(prefsName, Long.parseLong(etValue.getText().toString()));
                break;
            case 4 :
                editor.putString(prefsName, etValue.getText().toString());
                break;
        }
    }
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String str1 = null;
            String str2 = null;
            boolean b = false;
            switch (v.getId()){
                case R.id.btnAddPrefs:
                    selectedSP = getSharedPreferences(etPrefs.getText().toString(), MODE_PRIVATE);
                    editor = selectedSP.edit();
                    str1 =  "adding preference";
                    str2 = "add preference";
                    b = editor.commit();
                    invalidate(SPINNER_ALL);
                    break;
                case R.id.btnAddKey:
                    putValue(etKey);
                    str1 =  "adding key";
                    str2 = "add key";
                    b = editor.commit();
                    invalidate(SPINNER_KEY);
                    break;
                case R.id.btnSetValue:
                    putValue(spinKey);
                    str1 =  "setting key";
                    str2 = "set key";
                    b = editor.commit();
                    invalidate(SPINNER_KEY);
                    break;
                case R.id.btnRemoveKey:
                    editor.remove(spinKey.getSelectedItem().toString());
                    str1 =  "removing key";
                    str2 = "remove key";
                    b = editor.commit();
                    invalidate(SPINNER_KEY);
                    break;
                case R.id.btnClearAllKey:
                    editor.clear();
                    str1 =  "clearing key";
                    str2 = "clear key";
                    b = editor.commit();
                    invalidate(SPINNER_KEY);
                    break;
            }
            if (b)
                Toast.makeText(getApplicationContext(), "Succeeded in adding preference", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Failed to add preference", Toast.LENGTH_LONG).show();
        }
    }

    private void invalidate(int spinnerNum){
        etPrefs.setText("");
        etKey.setText("");
        etValue.setText("");

        if (spinnerNum == SPINNER_PREFS || spinnerNum == SPINNER_ALL) {
            File prefsDir = null;
            try {
                prefsDir = new File(getApplicationInfo().dataDir, "shared_prefs");
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            if (prefsDir.exists() && prefsDir.isDirectory()) {
                String[] prefsList = prefsDir.list();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, prefsList);
                spinPrefs.setAdapter(adapter);
            }
        } else if (spinnerNum == SPINNER_KEY || spinnerNum == SPINNER_ALL) {
            Map<String, ?> map = selectedSP.getAll();
            int index = 0;
            String[] keyList = new String[map.size()];
            for (Map.Entry<String, ?> entry : map.entrySet()){
                keyList[index++] = entry.getKey();
            }
            if (keyList != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, keyList);
                spinKey.setAdapter(adapter);
            }
        }

        if (selectedSP != null) {
            if (selectedSP.getAll().size() == 0) {
                btnSetValue.setEnabled(false);
                btnRemoveKey.setEnabled(false);
                btnClearAllKey.setEnabled(false);
            } else{
                btnSetValue.setEnabled(true);
                btnRemoveKey.setEnabled(true);
                btnClearAllKey.setEnabled(true);
            }
        }

    }
}
