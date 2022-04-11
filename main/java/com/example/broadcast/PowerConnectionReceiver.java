package com.example.broadcast;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

    private static final String TAG = "PowerConnectionReceiver";

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        //若收到广播“充电中”或“已充满”则isCharging = true
        boolean isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING )
                || (status == BatteryManager.BATTERY_STATUS_FULL);

        //判断是USB充电还是AC充电（AC指交流电)
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = (chargePlug == BatteryManager.BATTERY_PLUGGED_USB);
        boolean acCharge = (chargePlug == BatteryManager.BATTERY_PLUGGED_AC);

        //打印充电信息
        if(isCharging){
            Toast.makeText(context, "Charging",
                    Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Charging");
        }

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}