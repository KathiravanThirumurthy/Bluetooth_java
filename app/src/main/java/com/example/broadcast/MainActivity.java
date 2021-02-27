package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_ENABLE_BT = 2;
    private Button scanButton;
    private Button discoverButton;
    private MyBroadcast broadcast;
    private MyBroadcastdiscover broadcastdiscover;
    public BluetoothAdapter bluetoothAdapter;
    public Set<BluetoothDevice> pairedDevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcast=new MyBroadcast();
        broadcastdiscover=new MyBroadcastdiscover();
         bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
         scanButton=(Button) findViewById(R.id.scan_btn);
         discoverButton=(Button) findViewById(R.id.discover_btn);
         scanButton.setOnClickListener(this);
        discoverButton.setOnClickListener(this);
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(getApplicationContext(),"Device doesn't support bluetooth", Toast.LENGTH_LONG).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(broadcast, filter);
        /*Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);*/


    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcast);
        unregisterReceiver(broadcastdiscover);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(broadcast);
        unregisterReceiver(broadcastdiscover);
    }

   /* @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcast);
        unregisterReceiver(broadcastdiscover);
    }*/



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.scan_btn:
            {
                pairedDevices = bluetoothAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address
                        ArrayList list = new ArrayList();
                        list.add(device.getName());
                        Toast.makeText(getApplicationContext(),deviceName,Toast.LENGTH_LONG).show();

                    }
                break;
                }
            }

            case R.id.discover_btn:
            {
                Toast.makeText(getApplicationContext(),"SCAN",Toast.LENGTH_LONG).show();
                Intent discoverableIntent =
                        new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(discoverableIntent);
                if (bluetoothAdapter.isDiscovering()) {
                    bluetoothAdapter.cancelDiscovery();
                }else
                {
                    bluetoothAdapter.startDiscovery();
                }
                registerReceiver(broadcastdiscover,new IntentFilter(bluetoothAdapter.ACTION_DISCOVERY_STARTED));
                registerReceiver(broadcastdiscover,new IntentFilter(bluetoothAdapter.ACTION_DISCOVERY_FINISHED));
               /* IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(broadcastdiscover, filter);*/

                break;
            }
        }


    }
}