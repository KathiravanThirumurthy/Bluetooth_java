package com.example.broadcast;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyBroadcastdiscover extends BroadcastReceiver {
    public ArrayList<BluetoothDevice> mBTDevices=new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
       if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Discovery has found a device. Get the BluetoothDevice
            // object and its info from the Intent.
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress(); // MAC address
            Toast.makeText(context,deviceName,Toast.LENGTH_LONG).show();
           Log.d("TAG",deviceHardwareAddress);


        }
        else {

            Toast.makeText(context,"NO DEVICE YET FOuND",Toast.LENGTH_LONG).show();
            Log.d("TAG","DEVICE NOT FOUND");
        }
      /*  if (action.equals(BluetoothDevice.ACTION_FOUND)){
            BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
            mBTDevices.add(device);
            Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
            Log.d(TAG,"onReceive_size of the array : "+mBTDevices.size());

        }else {
            Log.d(TAG, "onElse: NO DEVICE FOUND.");
        }*/


    }
}
