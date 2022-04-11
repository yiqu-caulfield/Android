package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class BatteryLevelReceiver extends BroadcastReceiver {

    private static final String TAG = "BatteryLevelReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float)scale;
        System.out.println("the battery level approaches "+batteryPct+"%");
        Toast.makeText(context, "the battery level is "+batteryPct+"%",
                Toast.LENGTH_SHORT).show();
        Log.e(TAG, "the battery level is "+batteryPct+"%");

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}