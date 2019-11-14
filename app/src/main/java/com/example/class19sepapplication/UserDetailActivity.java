package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvN,tvG,tvD,tvC,tvP,tvE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        imageView = findViewById(R.id.ivUserImg);
        tvN = findViewById(R.id.tvUsrName);
        tvG = findViewById(R.id.tvUsrGender);
        tvD = findViewById(R.id.tvUsrDob);
        tvC = findViewById(R.id.tvUsrCountry);
        tvP = findViewById(R.id.tvUsrPhone);
        tvE = findViewById(R.id.tvUsrEmail);

        Intent intent = getIntent();
        String n = intent.getStringExtra("Name");
        String g = intent.getStringExtra("Gender");
        String d = intent.getStringExtra("DoB");
        String c = intent.getStringExtra("Country");
        String p = intent.getStringExtra("Phone");
        String e = intent.getStringExtra("Email");

        imageView.setImageResource(R.drawable.imgi);
        tvN.setText("Name : "+ n);
        tvG.setText("Gender : "+ g);
        tvD.setText("DoB : "+ d);
        tvC.setText("Country : "+ c);
        tvP.setText("Phone : "+ p);
        tvE.setText("Email : "+ e);

    }
}
