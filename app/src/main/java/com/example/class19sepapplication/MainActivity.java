package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLl,buttonGl,buttonRl,buttonCl,buttonTl,buttonFl;

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

        buttonLl.setOnClickListener(this);
        buttonGl.setOnClickListener(this);
        buttonRl.setOnClickListener(this);
        buttonCl.setOnClickListener(this);
        buttonTl.setOnClickListener(this);
        buttonFl.setOnClickListener(this);

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
    }
}
