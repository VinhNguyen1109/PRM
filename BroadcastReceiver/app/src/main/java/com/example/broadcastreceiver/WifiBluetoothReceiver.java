package com.example.broadcastreceiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiBluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiManager.WIFI_STATE_ENABLED) {
                Toast.makeText(context, "Wifi đã bật", Toast.LENGTH_SHORT).show();
            } else if (state == WifiManager.WIFI_STATE_DISABLED) {
                Toast.makeText(context, "Wifi đã tắt", Toast.LENGTH_SHORT).show();
            }
        } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            if (state == BluetoothAdapter.STATE_ON) {
                Toast.makeText(context, "Bluetooth đã bật", Toast.LENGTH_SHORT).show();
            } else if (state == BluetoothAdapter.STATE_OFF) {
                Toast.makeText(context, "Bluetooth đã tắt", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
