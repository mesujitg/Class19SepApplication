package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    String mytoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent i = getIntent();
        mytoken = i.getStringExtra("token");
        Toast.makeText(this,
                mytoken, Toast.LENGTH_SHORT).show();
    }
}
