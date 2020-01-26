package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.class19sepapplication.services.BroadCastExample;
import com.example.class19sepapplication.services.CreateChannel;
import com.example.class19sepapplication.services.MyService;

public class NotificationActivity extends AppCompatActivity {

    Button buttonN1,buttonN2,buttonS1,buttonS2;
    NotificationManagerCompat notificationManagerCompat;
    BroadCastExample broadCastExample = new BroadCastExample(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        buttonN1 = findViewById(R.id.btnNoti1);
        buttonN2 = findViewById(R.id.btnNoti2);
        buttonS1 = findViewById(R.id.btnServiceStart);
        buttonS2 = findViewById(R.id.btnServiceStop);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        buttonN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification1();
            }
        });

        buttonN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification2();
            }
        });

        buttonS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyService();
            }
        });

        buttonS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMyService();
            }
        });
    }

    public void displayNotification1(){
        Notification notification = new NotificationCompat
                .Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.my_icon_first)
                .setContentTitle("First Notification")
                .setContentText("This is first Notification")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }

    public void displayNotification2(){
        Notification notification = new NotificationCompat
                .Builder(this, CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.my_icon_first)
                .setContentTitle("Second Notification")
                .setContentText("This is Second Notification")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(2,notification);
    }

    public void startMyService(){
        startService(new Intent(this, MyService.class));
    }

    public void stopMyService(){
        stopService(new Intent(this, MyService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastExample,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastExample);
    }
}
