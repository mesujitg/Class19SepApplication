package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLl,buttonGl,buttonRl,buttonCl,buttonTl,
            buttonFl,buttonRv,buttonFrag,buttonHm,buttonTool,buttonFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLl = findViewById(R.id.btnLl);
        buttonGl = findViewById(R.id.btnGl);
        buttonRl = findViewById(R.id.btnRl);
        buttonCl = findViewById(R.id.btnCl);
        buttonTl = findViewById(R.id.btnTl);
        buttonFl = findViewById(R.id.btnFl);
        buttonRv = findViewById(R.id.btnRv);
        buttonFrag = findViewById(R.id.btnFrag);
        buttonHm = findViewById(R.id.btnHm);
        buttonTool = findViewById(R.id.btnTool);
        buttonFile = findViewById(R.id.btnFile);

        buttonLl.setOnClickListener(this);
        buttonGl.setOnClickListener(this);
        buttonRl.setOnClickListener(this);
        buttonCl.setOnClickListener(this);
        buttonTl.setOnClickListener(this);
        buttonFl.setOnClickListener(this);
        buttonRv.setOnClickListener(this);
        buttonFrag.setOnClickListener(this);
        buttonHm.setOnClickListener(this);
        buttonTool.setOnClickListener(this);
        buttonFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnTl){
            Intent intent = new Intent(this,TlActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnFl){
            Intent intent = new Intent(this,FlActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnHm){
            Intent intent = new Intent(this,HashmapActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnRv){
            Intent intent = new Intent(this,RvActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnFrag){
            Intent intent = new Intent(this,FragmentActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnTool){
            Intent intent = new Intent(this,ToobarActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.btnFile){
            Intent intent = new Intent(this,FileActivity.class);
            startActivity(intent);
        }
    }
}
