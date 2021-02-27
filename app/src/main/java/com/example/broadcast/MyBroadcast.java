package com.example.broadcast;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Broadcast Receiver Trigger",Toast.LENGTH_LONG).show();
        final String action = intent.getAction();

        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                   // setButtonText("Bluetooth off");
                    Toast.makeText(context,"Bluetooth off",Toast.LENGTH_LONG).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                   // setButtonText("Turning Bluetooth off...");
                    Toast.makeText(context,"Turning Bluetooth off...",Toast.LENGTH_LONG).show();
                    break;
                case BluetoothAdapter.STATE_ON:
                   // setButtonText("Bluetooth on");
                    Toast.makeText(context,"Bluetooth on...",Toast.LENGTH_LONG).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                   // setButtonText("Turning Bluetooth on...");
                    Toast.makeText(context,"Turning Bluetooth on...",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

}
