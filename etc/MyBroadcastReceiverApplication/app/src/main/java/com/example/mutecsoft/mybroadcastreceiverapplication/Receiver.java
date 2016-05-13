package com.example.mutecsoft.mybroadcastreceiverapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;

/**
 * Created by mutecsoft on 2016-04-22.
 */
public class Receiver extends BroadcastReceiver {
    private TextView tv;

    private int health;
    private int plugged;
    private boolean present;
    private int level;
    private int scale;
    private int status;
    private int temperature;
    private int voltage;

    public Receiver(TextView tv){
        this.tv = tv;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != Intent.ACTION_BATTERY_CHANGED){
            return;
        }

        health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
        plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
        level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
        temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
        voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

        final StringBuilder batteryInfoBuilder = new StringBuilder();
        batteryInfoBuilder.append("Health: "+getBatteryHealth(health));
        batteryInfoBuilder.append("\nPlugged: "+getBatteryPlugged(plugged));
        batteryInfoBuilder.append("\nPresent: "+getBatteryPresent(present));
        batteryInfoBuilder.append("\nLevel: "+level);
        batteryInfoBuilder.append("\nScale: "+scale);
        batteryInfoBuilder.append("\nStatus: "+getBatteryStatus(status));
        batteryInfoBuilder.append("\nTemperature: "+temperature);
        batteryInfoBuilder.append("\nVoltage: "+voltage);

        tv.setText(batteryInfoBuilder);
    }

    private String getBatteryHealth(int health){
        switch (health){
            case BatteryManager.BATTERY_HEALTH_GOOD:
                return "Good";
            case BatteryManager.BATTERY_HEALTH_DEAD:
                return "Dead";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "Over voltage";
            case BatteryManager.BATTERY_HEALTH_COLD:
                return "Cold";
        }

        return "Unknown";
    }

    private String getBatteryPlugged(int plugged) {
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "AC";
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "USB";
        }

        return "Not plugged";
    }

    private String getBatteryPresent(boolean present) {
        return (present) ? "Yes" : "No";
    }

    private String getBatteryStatus(int status) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "Charging";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "Discharging";
            case BatteryManager.BATTERY_STATUS_FULL:
                return "Full";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "Not charging";
        }

        return "Unknown";
    }
}