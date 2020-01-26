package com.example.class19sepapplication.services;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.class19sepapplication.R;

public class MyService extends Service {

    Context context = this;
    Handler handler = null;
    Runnable runnable = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    int count =0;
    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                count++;
                Toast.makeText(context, "Test : " + count  , Toast.LENGTH_LONG).show();
                if (count%5==0){
                    displayNotification1();
                }
                handler.postDelayed(runnable, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Toast.makeText(context, "Service Stopped",
                Toast.LENGTH_SHORT).show();
    }

    public void displayNotification1(){
        Notification notification = new NotificationCompat
                .Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.my_icon_first)
                .setContentTitle("My Notification")
                .setContentText("This is my Notification")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(this);

    notificationManagerCompat.notify(1,notification);
    }

    public static double getRandom(double min, double max){
        return Math.random()*((max-min)+1)+min;
    }
}
