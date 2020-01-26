package com.example.class19sepapplication.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

public class BroadCastExample extends BroadcastReceiver {
    NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadCastExample(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noCon;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noCon = intent.getBooleanExtra(ConnectivityManager
                    .EXTRA_NO_CONNECTIVITY, false);
            if (noCon){
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
