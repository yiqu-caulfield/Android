package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        //registerReceiver(n,i)注册接收器，n指代响应，i指代接受广播的值
        //想接收什么类型的广播，就需要在此处添加相应的action
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册接收器
        unregisterReceiver(networkChangeReceiver);
    }

    static class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                Toast.makeText(context, "network is connected",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is Not connected",
                        Toast.LENGTH_SHORT).show();
            }
            //声明networkInfo不为空
            //assert networkInfo != null;
            // is WIFI？or mobile data ？
            //boolean isWiFi = (networkInfo.getType() == ConnectivityManager.TYPE_WIFI);
        }
    }
}